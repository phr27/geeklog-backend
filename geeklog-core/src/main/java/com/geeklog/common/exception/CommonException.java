package com.geeklog.common.exception;

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：异常基类
 */
public abstract class CommonException extends RuntimeException {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：错误代码
     */
    private int code;

    public int getCode() {
        return code;
    }

    protected CommonException(int code, String message) {
        super(message);
        this.code = code;
    }
}
