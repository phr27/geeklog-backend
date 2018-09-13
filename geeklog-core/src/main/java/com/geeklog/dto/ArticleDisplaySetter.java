package com.geeklog.dto;

/**
 * @author 潘浩然
 * 创建时间 2018/09/13
 * 功能：管理员设置文章是否显示时，接收 display 参数
 */
public class ArticleDisplaySetter {

    private boolean display;

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
