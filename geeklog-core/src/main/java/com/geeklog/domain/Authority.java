package com.geeklog.domain;

public class Authority {
    private Integer autnorityId;

    private String name;

    public Integer getAutnorityId() {
        return autnorityId;
    }

    public void setAutnorityId(Integer autnorityId) {
        this.autnorityId = autnorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}