package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.entity.Disk;
import com.ndergunov.dvdexchange.model.jpa.JPAStrategyService;
import net.minidev.json.JSONUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATests {
    @Autowired
    JPAStrategyService jpaStrategyService;
    @Test
    public void freeDisksTest(){
        List<Disk> disks = jpaStrategyService.findFreeDisks();
        disks.forEach(System.out::println);
        Assert.assertTrue(disks.stream().allMatch(Disk::isTakeable));
    }
}
