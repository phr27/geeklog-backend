package com.geeklog.dto;


/**
 * @author 午康俊
 * 创建时间 2018/9/12
 * 功能 封装用户id，权限id
 */
public class BeForbidden {
    private int userId;

    private int authorityId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }
}
