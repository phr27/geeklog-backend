package com.geeklog.domain;
/**
 *  @author 朱远飞
 *  @create_time 2018年9月9日15:11:30
 *  @describe JavaBean 类别表
 */
public class Category {
    // 主键
    private Integer categoryId;
    // 类别名称
    private String name;
    // 类别描述
    private String description;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}