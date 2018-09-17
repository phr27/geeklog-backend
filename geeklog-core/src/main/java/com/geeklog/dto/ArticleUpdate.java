package com.geeklog.dto;

/**
 * @author 午康俊
 * 创建时间 2018/9/17
 * 功能 更新文章，接收参数
 */

public class ArticleUpdate {
    private String title;
    private String content;
    private int categoryId;
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
