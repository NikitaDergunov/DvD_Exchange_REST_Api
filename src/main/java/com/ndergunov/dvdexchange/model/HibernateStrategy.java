package com.ndergunov.dvdexchange.model;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class HibernateStrategy implements DAOStrategy{
    @Autowired
    private SessionFactory sessionFactory;
//TO DO: Add exceptions
    public List<Disk> findFreeDisks(){
        //get free disks
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery("from Disk as Disk where Disk.takeable=true");
        return q.list();
    }
    public List<Disk> findUserDisks(int userID){
        List<TakenItem> takenItems = findTakenDisks(userID);

        return takenItems.stream().map(TakenItem::getDisk).collect(Collectors.toList());
    }
    public List<TakenItem> findTakenDisks(int userID){
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery("from TakenItem as TakenItem where TakenItem.current_user.id =:ownerid");
        q.setParameter("ownerid",userID);
        return q.list();
    }
    public List<TakenItem> findGivenDisks(int userID){
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery("from TakenItem as TakenItem where TakenItem.previous_user.id =:ownerid");
        q.setParameter("ownerid",userID);

        return q.list();
    }

    public Disk setTakeble(int userID, int diskID) throws DvdExchangeException {
        Session session = this.sessionFactory.getCurrentSession();
        //throws no result exception if this disk is not owned by user
        //try{
        TakenItem takenItem = (TakenItem) session.createQuery("from TakenItem as TakenItem where current_user.id=:userID and disk.id=:diskID")
                .setParameter("userID", userID)
                .setParameter("diskID", diskID)
                .getSingleResult();
       // if(takenItem.getDisk().getId()==diskID)
        Disk disk = takenItem.getDisk();
        disk.setTakeable(true);
        session.update(disk);
        return disk;
        //}catch (NoResultException e){
        //    throw new DvdExchangeException(e.getMessage());
       // }


    }

    public TakenItem takeDisk(int userID,int diskID) throws DvdExchangeException {
        Session session = this.sessionFactory.getCurrentSession();
        Disk disk = session.load(Disk.class,diskID);

        if(disk.isTakeable()){
           TakenItem takenItem = (TakenItem) session.createQuery("from TakenItem as TakenItem where disk.id=:diskID")
                   .setParameter("diskID",diskID)
                   .getSingleResult();

           User prev = takenItem.getCurrent_user();
           User curr = getUserByID(userID);
           takenItem.setCurrent_user(curr);
           takenItem.setPrevious_user(prev);
           session.saveOrUpdate(takenItem);
           disk.setTakeable(false);
           session.saveOrUpdate(disk);
           return takenItem;
        }else throw new DvdExchangeException("disk is not takeable");

    }
    public User getUserByID(int userID){
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(User.class,userID);
    }
    public void addUser(User user){
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }
    public void addDisk(Disk disk){
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(disk);
    }
    public void addTakenItem(TakenItem takenItem){
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(takenItem);
    }
    public void deleteUser(User user){
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }
    public void deleteDisk(Disk disk){
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(disk);
    }
    public void deleteTakenItem(TakenItem takenItem){
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(takenItem);
    }
}
