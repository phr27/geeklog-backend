package com.geeklog.dto;

/**
 * @author 潘浩然
 * 创建时间 2018/09/14
 * 功能：用户注册 dto
 */
public class UserRegistry {

    private String username;

    private String password;

    private String nickname;

    private String bio;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
