package com.geeklog.common.exception;

public class RoleException extends CommonException {

    private RoleException(int code, String message) {
        super(code, message);
    }

    public static final RoleException NOT_ADMIN = new RoleException(640, "不是管理员");

    public static final RoleException USER_IS_ADMIN = new RoleException(641, "该用户为管理员");

    public static final RoleException USER_NOT_EXIST = new RoleException(642, "该用户不存在");

    public static final RoleException USER_ALREADY_EXIST = new RoleException(643, "该用户已存在");

    public static final RoleException UPDATE_OTHER_USER = new RoleException(644, "不能更新其他用户的信息");

    public static final RoleException UPDATE_OTHER_PWD = new RoleException(645, "不能更改其他用户的密码");

    public static final RoleException OTHER_USER_STAR = new RoleException(646, "不能替其他用户点赞或取消点赞");

}
