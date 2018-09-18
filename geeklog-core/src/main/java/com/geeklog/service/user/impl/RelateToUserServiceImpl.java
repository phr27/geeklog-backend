package com.geeklog.service.user.impl;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.User;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;
import com.geeklog.mapper.ArticleMapper;
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


    public Page<ArticleDto> getWroteArticles(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Validator.notNull(user, RoleException.USER_NOT_EXIST);

        Article article = new Article();
        article.setUserId(userId);

        int total = articleMapper.queryNumOfArticles(article);

        List<ArticleDto> articleList = articleMapper.queryPagingOrder(article, 0, total, true);
        ArticleDto[] articleDtos = new ArticleDto[articleList.size()];
        articleList.toArray(articleDtos);


        return new Page<>(total,articleDtos);

    }

    public Page<ArticleDto> getStarredArticles(int userId) {
        return null;
    }

    public Page<ArticleDto> getCollectedArticles(int userId) {
        return null;
    }

    public Page<ArticleDto> getCommentedArticles(int userId) {
        return null;
    }
}
