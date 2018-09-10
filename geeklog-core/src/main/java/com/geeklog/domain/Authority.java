package com.geeklog.domain;
/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe JavaBean 权限表
 */
public class Authority {
    // 主键
    private Integer authorityId;
    // 权限名称
    private String name;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}