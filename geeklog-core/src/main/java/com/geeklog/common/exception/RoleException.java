package com.geeklog.common.exception;

public class RoleException extends CommonException {

    private RoleException(int code, String message) {
        super(code, message);
    }

    public static final RoleException NOT_ADMIN = new RoleException(640, "不是管理员");
}
