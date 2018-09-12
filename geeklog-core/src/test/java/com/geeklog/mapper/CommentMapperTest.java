package com.geeklog.mapper;

import com.geeklog.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTest {
    @Autowired
    CommentMapper mapper;
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
    public void queryNumOfComments() throws Exception {
        Comment comment = new Comment();
        comment.setArticleId(1);
        int num = mapper.queryNumOfComments(comment);
        assertEquals(4, num);
    }

    @Test
    public void queryPaging() throws Exception {
        Comment comment = new Comment();
        comment.setArticleId(2);
        List<Comment> comments = mapper.queryPaging(comment, 0, 15);
        assertEquals(4, comments.size());
    }

    @Test
    public void queryPagingRoot() throws Exception {
        Comment comment = new Comment();
        comment.setArticleId(1);
        List<Comment> comments = mapper.queryPagingRoot(comment, 0, 15);
        assertEquals(2, comments.size());
    }

    @Test
    public void queryPagingReply() throws Exception {
        Comment comment = new Comment();
        comment.setRootId(1);
        List<Comment> comments = mapper.queryPagingReply(comment, 0, 15);
        assertEquals(3, comments.size());
    }

}