package com.geeklog.mapper;

import com.geeklog.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
/**
 * 作者：朱远飞
 * 创建时间：2018年9月10日14:25:29
 * 说明：CategoryMapper 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    CategoryMapper mapper;
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
    public void queryByCategoryName() throws Exception {
        Category category = mapper.queryByCategoryName("前端开发");
        System.out.println(category.getDescription());
    }

}