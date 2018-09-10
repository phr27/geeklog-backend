package com.geeklog.domain;

/**
 * @author 朱远飞
 * @create_time 2018年9月10日10:21:26
 * @describe JavaBean 点赞表
 */
public class Star {
    // id主键
    private Integer starId;
    // 外键->用户表主键
    private Integer userId;
    // 外键->文章表主键
    private Integer articleId;

    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
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
}