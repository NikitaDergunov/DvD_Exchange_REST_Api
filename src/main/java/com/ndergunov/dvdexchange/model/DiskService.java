package com.ndergunov.dvdexchange.model;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.template.TakenItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiskService {
    @Autowired
    HibernateStrategy hibernateStrategy;


    public List<Disk> findFreeDisks() {
        return hibernateStrategy.findFreeDisks();
    }


    public List<Disk> findUserDisks(int userID) {
        return hibernateStrategy.findUserDisks(userID);
    }


    public List<TakenItemTemplate> findTakenDisks(int userID) {
        return hibernateStrategy.findTakenDisks(userID).stream().map(TakenItemTemplate::new).collect(Collectors.toList());
    }


    public List<TakenItemTemplate> findGivenDisks(int userID) {
        return hibernateStrategy.findGivenDisks(userID).stream().map(TakenItemTemplate::new).collect(Collectors.toList());
    }


    public Disk setTakeble(int userID, int diskID) throws DvdExchangeException {
        return hibernateStrategy.setTakeble(userID, diskID);
    }


    public TakenItemTemplate takeDisk(int userID, int diskID) throws DvdExchangeException {
        return new TakenItemTemplate(hibernateStrategy.takeDisk(userID, diskID));
    }



}
