package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.entity.TakenItem;
import com.ndergunov.dvdexchange.model.HibernateStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrTest {
    @Autowired
    HibernateStrategy hibernateStrategy;
    @Test
    public void TakenItemTest(){
       // TakenItem ti = hibernateStrategy.getTaken();
       // System.out.println(ti.toString());
       // Assertions.assertNotNull(ti);
    }
}
