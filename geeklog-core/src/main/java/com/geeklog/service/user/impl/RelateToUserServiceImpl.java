package com.geeklog.service.user.impl;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.PageUtil;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Comment;
import com.geeklog.domain.User;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.CommentMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.user.RelateToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("user.RelateToUserService")
public class RelateToUserServiceImpl implements RelateToUserService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;


    public Page<ArticleDto> getWroteArticles(int page, int size, int userId) {

        Validator.isCurrentUser(userId, RoleException.OTHER_USER_ARTICLE);

        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Article article = new Article();
        article.setUserId(userId);

        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        int total = articleMapper.queryNumOfArticles(article);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);


        List<ArticleDto> articleList = articleMapper.queryPagingOrder(article, (page - 1) * size, size, true);
        ArticleDto[] articleDtos = new ArticleDto[articleList.size()];
        articleList.toArray(articleDtos);


        return new Page<>(total,articleDtos);

    }

    public Page<ArticleDto> getStarredArticles(int page, int size, int userId) {
        return null;
    }

    public Page<ArticleDto> getCollectedArticles(int page, int size, int userId) {
        return null;
    }

    public Page<Comment> getComments(int page, int size, int userId) {
        Validator.isCurrentUser(userId, RoleException.OTHER_USER_ARTICLE);

        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Comment comment = new Comment();
        comment.setUserId(userId);


        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        int total = commentMapper.queryNumOfComments(comment);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);

        List<Comment> commentList = commentMapper.queryPagingDESC(comment, (page - 1) * size, size);
        Comment[] comments = new Comment[commentList.size()];
        commentList.toArray(comments);


        return new Page<>(total,comments);


    }
}
