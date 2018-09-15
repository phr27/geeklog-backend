package com.geeklog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 潘浩然
 * 创建时间 2018/09/15
 * 功能：点赞或取消点赞时的请求体
 */
public class StarRequestBody {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("article_id")
    private Integer articleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
