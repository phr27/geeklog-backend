package com.geeklog.dto;

import com.geeklog.domain.Article;

public class ArticleDto extends Article{

    // 点赞数
    private int starCount;
    // 收藏数
    private int collectCount;
    // 评论数
    private int commentCount;
    // 综合值 为点赞数+收藏数+评论数
    private int count;
    // 用户账号
    private String username;
    // 用户昵称
    private String nickname;
    // 类别名称
    private String categoryName;

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
