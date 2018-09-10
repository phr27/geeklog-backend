package com.geeklog.domain;

/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe JavaBean 用户表
 */
public class User {
    // 用户的ID主键
    private Integer userId;
    // 用户账号
    private String username;
    // 用户密码
    private String password;
    // 用户昵称
    private String nickname;
    // 用户头像（存路径）
    private String avatar;
    // 用户简介
    private String bio;
    // 用户是否为管理员
    private Boolean isAdmin;

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
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}