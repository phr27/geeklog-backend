package com.geeklog.common.exception;

public class PermissionException extends CommonException {
    protected PermissionException(int code, String message) {
        super(code, message);
    }

    public static final PermissionException NO_WRITE_ARTICLE_PERMISSION = new PermissionException(670, "没有发布文章权限");

    public static final PermissionException NO_COMMENT_PERMISSION = new PermissionException(671, "没有发布评论权限");

}
