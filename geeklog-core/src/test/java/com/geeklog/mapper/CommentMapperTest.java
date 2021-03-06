package com.geeklog.mapper;

import com.geeklog.domain.Comment;
import com.geeklog.dto.CommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
/**
 * 作者：朱远飞
 * 创建时间：2018年9月12日14:07:55
 * 说明：CommentMapper 测试类
 */
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
        List<CommentDto> comments = mapper.queryPagingDESC(comment, 0, 15);
        for (CommentDto commentDto : comments) {
            System.out.println(commentDto.getArticleTitle());
            System.out.println(commentDto.getFromUserNickname());
            System.out.println(commentDto.getFromUserAvatar());
            System.out.println(commentDto.getToUserNickname());
            System.out.println(commentDto.getToUserAvatar());
            System.out.println(commentDto.getCreatedAt());
            System.out.println("----------");
        }
    }

    @Test
    public void queryPagingRoot() throws Exception {
        Comment comment = new Comment();
        comment.setArticleId(1);
        List<CommentDto> comments = mapper.queryPagingRoot(comment, 0, 15);
        for (CommentDto commentDto : comments) {
            System.out.println(commentDto.getArticleTitle());
            System.out.println(commentDto.getFromUserNickname());
            System.out.println(commentDto.getFromUserAvatar());
            System.out.println(commentDto.getToUserNickname());
            System.out.println(commentDto.getToUserAvatar());

        }
    }

    @Test
    public void queryPagingReply() throws Exception {
        Comment comment = new Comment();
        comment.setRootId(1);
        comment.setArticleId(1);
        List<CommentDto> comments = mapper.queryPagingReply(comment, 0, 15);
        for (CommentDto commentDto : comments) {
            System.out.println(commentDto.getArticleTitle());
            System.out.println(commentDto.getFromUserNickname());
            System.out.println(commentDto.getFromUserAvatar());
            System.out.println(commentDto.getToUserNickname());
            System.out.println(commentDto.getToUserAvatar());

        }
    }

}