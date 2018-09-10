package com.geeklog.common.exception;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：会话异常
 */
public class SessionException extends CommonException {

    private SessionException(int code, String message, Throwable cause) {
        super(code, message);
        super.initCause(cause);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：会话失效异常
     * @throws SessionException
     */
    public static SessionException invalid(Throwable cause) {
        return new SessionException(630, "会话无效，请重新登录", cause);
    }

}
