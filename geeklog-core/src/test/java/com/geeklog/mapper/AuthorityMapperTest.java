package com.geeklog.mapper;

import com.geeklog.domain.Authority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
/**
 * 作者：朱远飞
 * 创建时间：2018年9月10日14:25:29
 * 说明：ArticleMapper 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityMapperTest {
    @Autowired
    AuthorityMapper mapper;
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
        List<Authority> authorities = mapper.selectAll();
        for (Authority authority : authorities) {
            System.out.println(authority.getAuthorityId());
            System.out.println(authority.getName());
        }
        assertEquals(2, authorities.size());
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

}