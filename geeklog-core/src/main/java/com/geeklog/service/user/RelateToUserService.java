package com.geeklog.service.user;

import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;

public interface RelateToUserService {

    Page<ArticleDto> getWroteArticles(int userId);

    Page<ArticleDto> getStarredArticles(int userId);

    Page<ArticleDto> getCollectedArticles(int userId);

    Page<ArticleDto> getCommentedArticles(int userId);


}
