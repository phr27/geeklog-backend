package com.geeklog.mapper;

import com.geeklog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper mapper;
    @Test
    public void queryUser() throws Exception {
        List<User> users = mapper.selectAll();
        for(User user : users){
            System.out.println(user.getUsername());
        }
        assertEquals(10, users.size());
    }

    @Test
    public void queryAreaById() throws Exception {

    }

    @Test
    public void insertArea() throws Exception {

    }

    @Test
    public void updateArea() throws Exception {

    }

    @Test
    public void deleteArea() throws Exception {

    }

}