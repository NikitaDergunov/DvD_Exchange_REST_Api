package com.ndergunov.dvdexchange.model;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;

import java.util.List;

public interface DAOStrategy {
    public List<Disk> findFreeDisks();
    public List<Disk> findUserDisks(int userID);
    public List<TakenItem> findTakenDisks(int userID);
    public List<TakenItem> findGivenDisks(int userID);
    public Disk setTakeble(int userID, int diskID) throws DvdExchangeException;
    public TakenItem takeDisk(int userID,int diskID) throws DvdExchangeException;
    public User getUserByID(int userID);
}
