package com.geeklog.mapper;

import com.geeklog.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 作者：朱远飞
 * 创建时间：2018年9月9日15:14:59
 * 说明：UserMapper 测试类
 */
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

    @Test
    public void queryNumOfUsers() throws Exception {
        User user = new User();
        user.setIsAdmin(false);
        System.out.println(mapper.queryNumOfUsers(user));
        int count = mapper.queryNumOfUsers(user);
        System.out.println("用户数量为：" + count);
        List<User> userList = mapper.queryPaging(user, 5, 4);
        for (User user1 : userList) {
            System.out.println(user1.getUserId() + " " + user1.getUsername());
        }
    }

    @Test
    public void login() throws Exception {
        User login = mapper.login("a123456", "1234561");
        if(login != null){
            System.out.println(login.getUserId());
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

    }

    @Test
    public void queryUsername() throws Exception {
        User user = mapper.queryUsername("a1234561");
        if(user != null){
            System.out.println(user.getUserId());
            System.out.println("已存在该账号");
        }else{
            System.out.println("不存在");
        }

    }
}