package com.geeklog.common.exception;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：Jwt 解析异常
 */
public class JwtParseException extends RuntimeException {

    public JwtParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtParseException() {
    }

    public JwtParseException(String message) {
        super(message);
    }
}
