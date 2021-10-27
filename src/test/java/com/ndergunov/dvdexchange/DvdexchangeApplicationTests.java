package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.entity.Disk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
class DvdexchangeApplicationTests {


	@Test
	void contextLoads() {
	}
	@Test
	void diskCreates(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");
		Disk disk = (Disk) context.getBean("Disk");
		Assertions.assertNotNull(disk);
	}

}
