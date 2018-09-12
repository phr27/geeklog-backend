package com.geeklog.mapper;

import com.geeklog.domain.Forbidden;
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
 * 说明：ForbiddenMapper 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForbiddenMapperTest {
    @Autowired
    ForbiddenMapper mapper;
    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
    }

    @Test
    public void selectAll() throws Exception {
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

    @Test
    public void queryByUserId() throws Exception {
        List<Forbidden> forbiddens = mapper.queryByUserId(4);
        assertEquals(1, forbiddens.size());
        for (Forbidden forbidden : forbiddens) {
            System.out.println(forbidden.getForbiddenId());
        }

    }
    @Test
    public void queryByUserIdAndAuthorityId() throws Exception {
        Forbidden forbidden = mapper.queryByUserIdAndAuthorityId(4, 2);
        if(forbidden == null)
            System.out.println("不存在");
        else{
            System.out.println(forbidden.getForbiddenId());
            System.out.println("存在");
        }
    }
}