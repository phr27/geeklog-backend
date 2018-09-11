package com.geeklog.common.enumeration;

public enum Permission {
    CAN_WRITE_ARTICLE(1),
    CAN_COMMENT(2);

    private int code;

    Permission(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Permission getPermission(int code) {
        for (Permission permission : values()) {
            if (permission.getCode() == code) {
                return permission;
            }
        }

        return null;
    }
}
