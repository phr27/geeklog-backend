package com.geeklog.dto;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：封装用户名密码，用户请求登录时用该类的对象接收参数，JavaBean
 */
public class AuthToken {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：用户名
     */
    private String username;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：明文密码
     */
    private String password;

    public AuthToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthToken() {
    }

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
