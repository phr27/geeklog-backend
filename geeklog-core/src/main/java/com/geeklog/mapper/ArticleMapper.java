package com.geeklog.mapper;

import com.geeklog.domain.Article;
import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    String selectByPrimaryKey(Integer articleId);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);
}