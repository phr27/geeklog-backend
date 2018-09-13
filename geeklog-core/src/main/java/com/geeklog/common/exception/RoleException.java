package com.geeklog.common.exception;

public class RoleException extends CommonException {

    private RoleException(int code, String message) {
        super(code, message);
    }

    public static final RoleException NOT_ADMIN = new RoleException(640, "不是管理员");

    public static final RoleException USER_IS_ADMIN = new RoleException(641, "该用户为管理员");

    public static final RoleException USER_NOT_EXIST = new RoleException(642, "该用户不存在");
}
