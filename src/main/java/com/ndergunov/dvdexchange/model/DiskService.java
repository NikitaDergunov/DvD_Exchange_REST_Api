package com.ndergunov.dvdexchange.model;

import com.ndergunov.dvdexchange.entity.Disk;

import com.ndergunov.dvdexchange.model.jpa.JPAStrategyService;
import com.ndergunov.dvdexchange.template.TakenItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiskService {
    @Autowired
    JPAStrategyService diskStrategy;



    public List<Disk> findFreeDisks() {
        return diskStrategy.findFreeDisks();
    }


    public List<Disk> findUserDisks(int userID) {
        return diskStrategy.findUserDisks(userID);
    }


    public List<TakenItemTemplate> findTakenDisks(int userID) {
        List<TakenItemTemplate> tit = diskStrategy.findTakenDisks(userID).stream().map(TakenItemTemplate::new).collect(Collectors.toList());
        return tit;
    }


    public List<TakenItemTemplate> findGivenDisks(int userID) {
        return diskStrategy.findGivenDisks(userID).stream().map(TakenItemTemplate::new).collect(Collectors.toList());
    }


    public Disk setTakeble(int userID, int diskID) throws DvdExchangeException {
        return diskStrategy.setTakeble(userID, diskID);
    }


    public TakenItemTemplate takeDisk(int userID, int diskID) throws DvdExchangeException {
        return new TakenItemTemplate(diskStrategy.takeDisk(userID, diskID));
    }



}
