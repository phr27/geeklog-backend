package com.geeklog.dto;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：封装 jwt token 字符串，JavaBean
 */
public class JwtToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public JwtToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
