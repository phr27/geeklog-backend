package com.geeklog.mapper;

import com.geeklog.domain.Article;
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
public class ArticleMapperTest {
    @Autowired
    ArticleMapper mapper;
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
        List<Article> articles = mapper.selectAll();
        for (Article article : articles) {
            System.out.println(article.getTitle());
            System.out.println(article.getCreatedAt());
            System.out.println(article.getModifiedAt());
            System.out.println();
        }
        assertEquals(10, articles.size());

    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

}