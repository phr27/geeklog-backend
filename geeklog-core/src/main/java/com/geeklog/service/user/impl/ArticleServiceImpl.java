package com.geeklog.service.user.impl;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.PageUtil;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Category;
import com.geeklog.domain.User;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.CategoryMapper;
import com.geeklog.mapper.UserMapper;
import com.geeklog.service.user.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/17
 * 功能 文章相关服务实现
 */


@Service("user.ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;


    public List<ArticleDto> hotArticles(int count) {

        Validator.min(count, 1, ValidatorException.SIZE_OUT_OF_RANGE);
        return articleMapper.queryPagingOrder(new Article(), 0, count, false);
    }

    public Page<ArticleDto> categoryArticles(int page, int size, int categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Validator.notNull(category, ValidatorException.CATEGORY_NOT_EXIST);

        Article article = new Article();
        article.setCategoryId(categoryId);

        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        int total = articleMapper.queryNumOfArticles(article);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);

        List<ArticleDto> articleList = articleMapper.queryPagingOrder(article, (page - 1) * size, size, false);
        ArticleDto[] articleDtos = new ArticleDto[articleList.size()];
        articleList.toArray(articleDtos);


        return new Page<>(total,articleDtos);


    }

    public ArticleDto article(int articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);

        return articleMapper.queryOneArticle(articleId);
    }


    @Transactional
    public Article insertArticle(String title, String content, int userId, int categoryId, String tags) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Validator.notNull(category, ValidatorException.CATEGORY_NOT_EXIST);

        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Validator.isCurrentUser(userId, RoleException.OTHER_USER_ARTICLE);

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(userId);
        article.setCategoryId(categoryId);
        article.setTags(tags);
        article.setCreatedAt(new Date());
        article.setModifiedAt(new Date());
        article.setDisplay(true);
        articleMapper.insert(article);

        return article;
    }

    @Transactional
    public Article updateArticle(int articleId, String title, String content, int categoryId, String tags) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Validator.notNull(category, ValidatorException.CATEGORY_NOT_EXIST);

        Article article = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);

        Validator.isCurrentUser(article.getUserId(), RoleException.OTHER_USER_ARTICLE);
        article.setTitle(title);
        article.setContent(content);
        article.setCategoryId(categoryId);
        article.setTags(tags);
        article.setModifiedAt(new Date());
        articleMapper.updateByPrimaryKey(article);
        return article;
    }

    @Transactional
    public Article deleteArticle(int articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);
        Validator.isCurrentUser(article.getUserId(), RoleException.OTHER_USER_ARTICLE);

        articleMapper.deleteByPrimaryKey(articleId);
        return article;
    }
}
