package com.geeklog.domain;

import java.util.Date;
/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe JavaBean 文章表
 */
public class Article {
    // 主键
    private Integer articleId;
    // 文章标题
    private String title;
    // 创建时间
    private Date createdAt;
    // 最新更改时间
    private Date modifiedAt;
    // 外键->用户表主键
    private Integer userId;
    // 外键->类别表主键
    private Integer categoryId;
    // 标签
    private String tags;
    // 是否显示
    private Boolean display;
    // 文章内容
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}