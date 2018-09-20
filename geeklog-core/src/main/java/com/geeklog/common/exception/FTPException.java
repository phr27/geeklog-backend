package com.geeklog.common.exception;

/**
 * @author 潘浩然
 * 创建时间 2018/09/19
 * 功能：ftp图片服务器异常
 */
public class FTPException extends CommonException {

    private FTPException(int code, String message) {
        super(code, message);
    }

    private FTPException(int code, String message, Throwable cause) {
        super(code, message);
        super.initCause(cause);
    }

    public static final FTPException FILE_SIZE_LIMIT = new FTPException(691, "文件过大，最大允许 2 MB");

    public static FTPException unexpected(Throwable cause) {
        return new FTPException(690, "头像服务异常，请联系管理员", cause);
    }

}
