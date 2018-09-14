package com.geeklog.dto;

/**
 * @author 潘浩然
 * 创建时间 2018/09/14
 * 功能：用户信息更新 dto
 */
public class UserInfoUpdate {

    protected String nickname;

    protected String bio;

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
