package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DvdExchangeException;
import com.ndergunov.dvdexchange.model.hibernate.HibernateStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoTests {
    @Autowired
    HibernateStrategy hibernateStrategy;
    User testuser;
    Disk testdisk;
    TakenItem testTakenitem;

    @Before
    public  void setUp(){
               testuser = new User(1000,"testuser_junit",null);
        User testuser1 = new User(1001,"testuser2_junit",null);
         testdisk = new Disk(1000,"testdisk_junit",true);
         testTakenitem = new TakenItem(1000,testuser,testuser1,testdisk);
       hibernateStrategy.addUser(testuser);
       hibernateStrategy.addDisk(testdisk);
        hibernateStrategy.addTakenItem(testTakenitem);

    }
    @Test
    @Order(1)
    public void AfreedisksTest(){
        List<Disk> diskList = hibernateStrategy.findFreeDisks();
        Assertions.assertTrue(diskList.stream().allMatch(x->x.isTakeable()));
    }
    @Test
    @Order(2)
    public void BfindUserDisksTest(){
        List<Disk> disks = hibernateStrategy.findUserDisks(1000);

        Assertions.assertTrue(disks.contains(testdisk));
    }
    @Test
    @Order(3)
    public void CtakeDiskTest() throws DvdExchangeException {
        TakenItem ti = hibernateStrategy.takeDisk(1000,1000);
        Assertions.assertTrue(ti.getCurrent_user().getUserID()==1000);
        Assertions.assertTrue(!ti.getDisk().isTakeable());
    }
    @Test
    @Order(4)
    public void DtakenDisksTest(){
        List<TakenItem> ti = hibernateStrategy.findTakenDisks(1000);
        Assertions.assertTrue(ti.stream().anyMatch(x->x.getCurrent_user().getUserID()==1000));
    }
    @Test

    public void EgivenDisksTest(){
        List<TakenItem> ti = hibernateStrategy.findGivenDisks(1001);
        Assertions.assertTrue(ti.size()>0);
        Assertions.assertTrue(ti.stream().anyMatch(x->x.getPrevious_user().getUserID()==1001));
    }
    @Test

    public void FsetTakeableTest() throws DvdExchangeException {
        Assertions.assertTrue(hibernateStrategy.setTakeble(1000,1000).isTakeable());

    }

    @After
    public void cleanUp(){
        hibernateStrategy.deleteTakenItem(testTakenitem);
      hibernateStrategy.deleteDisk(testdisk);
        hibernateStrategy.deleteUser(testuser);

    }

}
