package com.geeklog.service.user.impl;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.PageUtil;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.*;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.CommentDto;
import com.geeklog.dto.Page;
import com.geeklog.mapper.*;
import com.geeklog.service.user.RelateToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/18
 * 功能 用户相关级联查询服务实现
 */


@Service("user.RelateToUserService")
public class RelateToUserServiceImpl implements RelateToUserService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private StarMapper starMapper;

    @Autowired
    private CollectMapper collectMapper;


    public Page<ArticleDto> getWroteArticles(int page, int size, int userId) {


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


        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Star star = new Star();
        star.setUserId(userId);

        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        int total = starMapper.queryNumOfStars(star);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);


        List<ArticleDto> articleList = articleMapper.queryPagingByUser(userId, (page - 1) * size, size, "star");
        ArticleDto[] articleDtos = new ArticleDto[articleList.size()];
        articleList.toArray(articleDtos);

        return new Page<>(total,articleDtos);
    }

    public Page<ArticleDto> getCollectedArticles(int page, int size, int userId) {


        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Collect collect = new Collect();
        collect.setUserId(userId);

        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        int total = collectMapper.queryNumOfCollects(collect);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);


        List<ArticleDto> articleList = articleMapper.queryPagingByUser(userId, (page - 1) * size, size, "collect");
        ArticleDto[] articleDtos = new ArticleDto[articleList.size()];
        articleList.toArray(articleDtos);

        return new Page<>(total,articleDtos);
    }

    public Page<Comment> getComments(int page, int size, int userId) {


        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Comment comment = new Comment();
        comment.setUserId(userId);


        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        int total = commentMapper.queryNumOfComments(comment);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);

        List<CommentDto> commentList = commentMapper.queryPagingDESC(comment, (page - 1) * size, size);
        Comment[] comments = new Comment[commentList.size()];
        commentList.toArray(comments);


        return new Page<>(total,comments);


    }

    public Boolean isStarred(int userId, int articleId) {
        Star star = starMapper.queryByUserIdAndArticleId(userId, articleId);
        if(null == star){
            return false;
        }
        return true;
    }

    public Boolean isCollected(int usrId, int articleId) {
        Collect collect = collectMapper.queryByUserIdAndArticleId(usrId, articleId);
        if(null == collect){
            return false;
        }
        return true;
    }
}
