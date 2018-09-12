package com.geeklog.domain;

import java.util.Date;

/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe JavaBean 收藏表
 */
public class Collect {
    // 主键
    private Integer collectId;
    // 外键->用户表主表
    private Integer userId;
    // 外键->文章表主键
    private Integer articleId;
    // 创建时间
    private Date createdAt;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

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

    public long getCreatedAt() {
        return createdAt.getTime();
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = new Date(createdAt);
    }
}