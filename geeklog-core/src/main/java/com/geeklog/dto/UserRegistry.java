package com.geeklog.dto;

/**
 * @author 潘浩然
 * 创建时间 2018/09/14
 * 功能：用户注册 dto
 */
public class UserRegistry extends UserInfoUpdate {

    private String username;

    private String password;

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

}
