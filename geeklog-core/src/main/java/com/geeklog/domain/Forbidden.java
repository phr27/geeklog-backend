package com.geeklog.domain;

public class Forbidden {
    private Integer forbiddenId;

    private Integer userId;

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