package com.geeklog.mapper;

import com.geeklog.domain.Star;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
/**
 * 作者：朱远飞
 * 创建时间：2018年9月10日14:25:29
 * 说明：StarMapper 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StarMapperTest {

    @Autowired
    StarMapper mapper;
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
    public void queryByUserIdAndArticleId() throws Exception {
        Star star = mapper.queryByUserIdAndArticleId(1, 1);
        System.out.println(star.getStarId());
    }

}