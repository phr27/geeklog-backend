package com.geeklog.service.user;

import com.geeklog.domain.Comment;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;

public interface RelateToUserService {

    Page<ArticleDto> getWroteArticles(int page, int size, int userId);

    Page<ArticleDto> getStarredArticles(int page, int size, int userId);

    Page<ArticleDto> getCollectedArticles(int page, int size, int userId);

    Page<Comment> getComments(int page, int size, int userId);


}
