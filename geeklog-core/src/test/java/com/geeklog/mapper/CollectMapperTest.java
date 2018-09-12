package com.geeklog.mapper;

import com.geeklog.domain.Collect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
/**
 * 作者：朱远飞
 * 创建时间：2018年9月12日14:07:35
 * 说明：CollectMappe 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectMapperTest {
    @Autowired
    CollectMapper mapper;
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
        List<Collect> collects = mapper.queryByUserId(1);
        assertEquals(1,collects.size());
    }

    @Test
        public void queryByUserIdAndArticleId() throws Exception {

        Collect collect = mapper.queryByUserIdAndArticleId(8, 5);
        if(collect != null){
            System.out.println(collect.getArticleId());
        }else{
            System.out.println("不存在");
        }
    }

}