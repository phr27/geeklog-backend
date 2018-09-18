package com.geeklog.service.admin;

import com.geeklog.domain.Article;
import com.geeklog.dto.Page;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的文章管理服务接口
 */
public interface ArticleService {

    Page<Article> listArticle(Integer categoryId, int page, int size);

    Article deleteArticle(int articleId);

    Article updateArticleDisplay(int articleId, boolean display);


    Article article(int articleId);
}
