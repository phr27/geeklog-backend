package com.geeklog.domain;
/**
 * @author 朱远飞
 * @create_time 2018年9月10日10:21:26
 * @describe JavaBean 禁止权限表
 */
public class Forbidden {
    // 主键
    private Integer forbiddenId;
    // 外键->用户表主键
    private Integer userId;
    // 外键->文章表主键
    private Integer authorityId;

    public Integer getForbiddenId() {
        return forbiddenId;
    }

    public void setForbiddenId(Integer forbiddenId) {
        this.forbiddenId = forbiddenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
}