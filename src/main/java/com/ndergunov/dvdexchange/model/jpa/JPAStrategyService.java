package com.ndergunov.dvdexchange.model.jpa;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DAOStrategy;
import com.ndergunov.dvdexchange.model.DvdExchangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JPAStrategyService implements DAOStrategy {
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
        return takenItemRepository.findAllByCurrent_user(user);
    }

    @Override
    public List<TakenItem> findGivenDisks(int userID) {
        User user = userRepository.findById(userID).get();
        return takenItemRepository.findAllByPrevious_user(user);
    }

    @Override
    public Disk setTakeble(int userID, int diskID) throws DvdExchangeException {
        return null;
    }

    @Override
    public TakenItem takeDisk(int userID, int diskID) throws DvdExchangeException {
        return null;
    }

    @Override
    public User getUserByID(int userID) {
        return null;
    }
}
