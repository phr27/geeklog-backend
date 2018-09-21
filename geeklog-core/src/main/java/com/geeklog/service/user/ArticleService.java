package com.geeklog.service.user;

import com.geeklog.domain.Article;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;

import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/17
 * 功能 文章服务接口
 */

public interface ArticleService {
    List<ArticleDto> hotArticles(int count);

    Page<ArticleDto> categoryArticles(int page, int size, Integer categoryId);

    ArticleDto article(int articleId);

    Article insertArticle(String title, String content, int userId, int categoryId, String tags);

    Article updateArticle(int articleId, String title, String content, int categoryId, String tags);

    Article deleteArticle(int articleId);
}
