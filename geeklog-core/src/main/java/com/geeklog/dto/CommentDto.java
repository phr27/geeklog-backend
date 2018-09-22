package com.geeklog.dto;

import com.geeklog.domain.Comment;

public class CommentDto extends Comment{

    // 发送者昵称
    private String fromUserNickname;

    // 接受者昵称
    private String toUserNickname;

    // 文章标题
    private String articleTitle;

    // 发送者头像
    private String fromUserAvatar;

    // 接受者头像
    private String toUserAvatar;

    public String getFromUserNickname() {
        return fromUserNickname;
    }

    public void setFromUserNickname(String fromUserNickname) {
        this.fromUserNickname = fromUserNickname;
    }

    public String getToUserNickname() {
        return toUserNickname;
    }

    public void setToUserNickname(String toUserNickname) {
        this.toUserNickname = toUserNickname;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    public String getToUserAvatar() {
        return toUserAvatar;
    }

    public void setToUserAvatar(String toUserAvatar) {
        this.toUserAvatar = toUserAvatar;
    }
}
