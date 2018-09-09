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
    public void queryUserById() throws Exception {
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void insertUser() throws Exception {
        User user = mapper.selectByPrimaryKey(1);
        user.setUsername("测试2");
        mapper.insert(user);
        System.out.println(user.getUserId());
    }

    @Test
    public void updateUser() throws Exception {
        User user = mapper.selectByPrimaryKey(2);
        user.setAvatar("测试路径222");
        user.setPassword("123123");
        user.setBio("测试Bio");
        user.setIsAdmin(true);
        user.setNickname("测试Nickname");
        mapper.updateByPrimaryKey(user);
    }

    @Test
    public void deleteUser() throws Exception {
        User user = mapper.selectByPrimaryKey(1);
        user.setUsername("测试2");
        mapper.insert(user);
        System.out.println(user.getUserId());
        mapper.deleteByPrimaryKey(user.getUserId());
    }

}