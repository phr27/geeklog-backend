package com.geeklog.dto;

public class UserWithPermission {

    private Integer userId;

    private String username;

    private String nickname;

    private String avatar;

    private Boolean isAdmin;

    private Boolean canComment = true;

    private Boolean canWriteArticle = true;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getCanComment() {
        return canComment;
    }

    public void setCanComment(Boolean canComment) {
        this.canComment = canComment;
    }

    public Boolean getCanWriteArticle() {
        return canWriteArticle;
    }

    public void setCanWriteArticle(Boolean canWriteArticle) {
        this.canWriteArticle = canWriteArticle;
    }
}
