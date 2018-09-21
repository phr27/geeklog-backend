package com.geeklog.service.user;

import com.geeklog.domain.Comment;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;

/**
 * @author 午康俊
 * 创建时间 2018/9/18
 * 功能 用户相关级联查询服务接口
 */

public interface RelateToUserService {

    Page<ArticleDto> getWroteArticles(int page, int size, int userId);

    Page<ArticleDto> getStarredArticles(int page, int size, int userId);

    Page<ArticleDto> getCollectedArticles(int page, int size, int userId);

    Page<Comment> getComments(int page, int size, int userId);

    Boolean isStarred(int userId, int articleId);

    Boolean isCollected(int usrId, int articleId);


}
