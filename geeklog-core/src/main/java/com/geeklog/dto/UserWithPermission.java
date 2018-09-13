package com.geeklog.dto;

import java.util.List;

import com.geeklog.common.enumeration.Permission;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.domain.Forbidden;

public class UserWithPermission {

    protected Integer userId;

    protected String username;

    protected String nickname;

    protected String avatar;

    protected Boolean isAdmin;

    protected Boolean canComment = true;

    protected Boolean canWriteArticle = true;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getCanComment() {
        return canComment;
    }

    public Boolean getCanWriteArticle() {
        return canWriteArticle;
    }

    public void setPermissions(List<Forbidden> forbiddens) {
        if (forbiddens == null || forbiddens.size() == 0) {
            return;
        }
        for (int i = 0; i < forbiddens.size(); i++) {
            switch (Permission.getPermission(forbiddens.get(i).getAuthorityId())) {
                case CAN_COMMENT:
                    canComment = false;
                    break;
                case CAN_WRITE_ARTICLE:
                    canWriteArticle = false;
                    break;
                default:
                    ValidatorException.unexpected("Unkown authority id in database table `geeklog.forbidden`");
            }
        }
    }

}
