package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.entity.User;
import com.ndergunov.dvdexchange.model.hibernate.HibernateStrategy;
import com.ndergunov.dvdexchange.security.jwt.JwtFilter;
import com.ndergunov.dvdexchange.security.jwt.JwtProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTests {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    HibernateStrategy hibernateStrategy;
    User testuser;
    @Before
    public void setTestUser(){
        testuser = new User(1000,"jwt_test_user","12345");
        hibernateStrategy.addUser(testuser);
    }
    @Test
    public void jwtProviderTest(){
        String login = "testlogin";
        String token = jwtProvider.generateToken(login);
        Assert.assertTrue(jwtProvider.validateToken(token));
        Assert.assertEquals(jwtProvider.getLoginFromToken(token),login);
    }
    @Test
    public void jwtFilterTest(){
       // jwtFilter.
    }
}
