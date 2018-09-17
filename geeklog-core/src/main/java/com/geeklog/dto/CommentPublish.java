package com.geeklog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentPublish {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("article_id")
    private Integer articleId;

    private String content;

    @JsonProperty("parent_id")
    private Integer parentId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
