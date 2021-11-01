package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.DvdExchangeException;

import com.ndergunov.dvdexchange.model.jpa.DiskRepository;
import com.ndergunov.dvdexchange.model.jpa.JPAStrategyService;
import com.ndergunov.dvdexchange.model.jpa.TakenItemRepository;
import com.ndergunov.dvdexchange.model.jpa.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
public class JPAServiceTests {
    @Autowired
    JPAStrategyService jpaStrategyService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DiskRepository diskRepository;
    @Autowired
    TakenItemRepository takenItemRepository;
    User testuser;
    Disk testdisk;
    TakenItem testTakenitem;

    @Before
    public  void setUp(){
        testuser = new User(1000,"testuser_junit",null);
        User testuser1 = new User(1001,"testuser2_junit",null);
        testdisk = new Disk(1000,"testdisk_junit",true);
        testTakenitem = new TakenItem(1000,testuser,testuser1,testdisk);
        userRepository.save(testuser);
        diskRepository.save(testdisk);
        takenItemRepository.save(testTakenitem);

    }
    @Test
    @Order(1)
    public void AfreedisksTest(){
        List<Disk> diskList = jpaStrategyService.findFreeDisks();
        Assertions.assertTrue(diskList.stream().allMatch(x->x.isTakeable()));
    }
    @Test
    @Order(2)
    public void BfindUserDisksTest(){
        List<Disk> disks = jpaStrategyService.findUserDisks(1000);

        Assertions.assertTrue(disks.contains(testdisk));
    }
    @Test
    @Order(3)
    public void CtakeDiskTest() throws DvdExchangeException {
        TakenItem ti = jpaStrategyService.takeDisk(1000,1000);
        Assertions.assertTrue(ti.getCurrent().getUserID()==1000);
        Assertions.assertTrue(!ti.getDisk().isTakeable());
    }
    @Test
    @Order(4)
    public void DtakenDisksTest(){
        List<TakenItem> ti = jpaStrategyService.findTakenDisks(1000);
        Assertions.assertTrue(ti.stream().anyMatch(x->x.getCurrent().getUserID()==1000));
    }
    @Test

    public void EgivenDisksTest(){
        List<TakenItem> ti = jpaStrategyService.findGivenDisks(1001);
        Assertions.assertTrue(ti.size()>0);
        Assertions.assertTrue(ti.stream().anyMatch(x->x.getPrevious().getUserID()==1001));
    }
    @Test

    public void FsetTakeableTest() throws DvdExchangeException {
        Assertions.assertTrue(jpaStrategyService.setTakeble(1000,1000).isTakeable());

    }

    @After
    public void cleanUp(){
       takenItemRepository.delete(testTakenitem);
       diskRepository.delete(testdisk);
       userRepository.delete(testuser);

    }

}
