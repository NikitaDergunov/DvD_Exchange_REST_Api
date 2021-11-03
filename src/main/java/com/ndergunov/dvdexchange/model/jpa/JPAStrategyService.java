package com.ndergunov.dvdexchange.model.jpa;

import com.ndergunov.dvdexchange.controller.UserDiskController;
import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DAOStrategy;
import com.ndergunov.dvdexchange.model.DvdExchangeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JPAStrategyService implements DAOStrategy {
    Logger logger = LoggerFactory.getLogger(JPAStrategyService.class);
    @Autowired
    TakenItemRepository takenItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DiskRepository diskRepository;
    @Override
    public List<Disk> findFreeDisks() {
        return diskRepository.findAllByTakeableTrue();
    }

    @Override
    public List<Disk> findUserDisks(int userID) {

        return findTakenDisks(userID).stream().map(x->x.getDisk()).collect(Collectors.toList());
    }

    @Override
    public List<TakenItem> findTakenDisks(int userID) {
        User user = userRepository.findById(userID).get();
        return takenItemRepository.findAllByCurrent(user);
    }

    @Override
    public List<TakenItem> findGivenDisks(int userID) {
        User user = userRepository.findById(userID).get();
        return takenItemRepository.findAllByPrevious(user);
    }

    @Override
    public Disk setTakeble(int userID, int diskID) throws DvdExchangeException {
        User user = userRepository.findById(userID).get();
        Disk disk = diskRepository.findById(diskID).get();

        if(takenItemRepository.existsByCurrentAndDisk(user,disk)){
            disk.setTakeable(true);
            diskRepository.save(disk);
            return disk;
        }
        logger.warn("service encountered conflict: user doesnt own disk");
        throw new DvdExchangeException("Disk doesn't belong to user");
    }

    @Override
    public TakenItem takeDisk(int userID, int diskID) throws DvdExchangeException {
        User user = userRepository.findById(userID).get();
        Disk disk = diskRepository.findById(diskID).get();
        if(disk.isTakeable()){
            TakenItem ti = takenItemRepository.findByDisk(disk);
            User prev = ti.getCurrent();
            ti.setCurrent(user);
            ti.setPrevious(prev);
            disk.setTakeable(false);
            ti.setDisk(disk);
            takenItemRepository.save(ti);
            diskRepository.save(disk);
            return ti;
        }
        logger.warn("disk is not takeable");
        throw new DvdExchangeException("disk is not takeable");
    }

    @Override
    public User getUserByID(int userID) {
        return userRepository.findById(userID).get();
    }
}
