package com.ndergunov.dvdexchange;

import com.ndergunov.dvdexchange.controller.UserDiskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserDiskControllerTest {
    @Autowired
    UserDiskController userDiskController;


    @Test
    public void contextLoads() throws Exception {
        assertThat(userDiskController).isNotNull();
    }

}
