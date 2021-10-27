package com.ndergunov.dvdexchange.model;

import com.ndergunov.dvdexchange.entity.Disk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DiskExchangeDAO {
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
        Session session = this.sessionFactory.getCurrentSession();
        Query q = session.createQuery("from Disk as Disk where Disk.owner =:ownerid");
        q.setParameter("ownerid",userID);
        return q.list();
    }

    public void setTakeble(int userID, int diskID){
        Session session = this.sessionFactory.getCurrentSession();
       // Transaction tx = session.beginTransaction();
        String hql = "update Disk d set takeable=true where owner=:ownerID and id=:diskID";
        int updated = session.createQuery(hql)
                .setParameter("ownerID",userID)
                .setParameter("diskID",diskID)
                .executeUpdate();
        if(updated!=1); // add exception
        //tx.commit();

    }


}
