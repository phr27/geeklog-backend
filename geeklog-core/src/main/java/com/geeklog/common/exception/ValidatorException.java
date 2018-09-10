package com.geeklog.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：数据校验异常
 */
public class ValidatorException extends CommonException {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：是否是未知的参数校验错误
     */
    private boolean innerError;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：异常日志，给后端看的，继承的 message 字段是异常信息，是发送给前端的
     */
    private String log;

    private ValidatorException(int code, String message) {
        super(code, message);
    }

    private ValidatorException(int code, String message, String log, boolean innerError) {
        super(code, message);
        this.log = log;
        this.innerError = innerError;
    }

    public boolean isInnerError() {
        return innerError;
    }

    public String getLog() {
        return log;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：创建一个未知的参数校验错误
     * @param log 异常日志
     */
    public static ValidatorException unexpected(String log) {
        return new ValidatorException(500, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), log, true);
    }

    public static final ValidatorException USERNAME_INVALID = new ValidatorException(601, "用户名最少 6 个字符，由字母数字下划线组成");

    public static final ValidatorException PWD_INVALID = new ValidatorException(602,
            "密码最少 6 个字符，包括字母数字下划线，可使用的特殊字符有 ~`!@#$%^&*?,:;()-.+={}[]");

    public static final ValidatorException NO_AUTH_TOKEN = new ValidatorException(603, "需要用户名密码");

    public static final ValidatorException USERNAME_OR_PWD_ERROR = new ValidatorException(604, "用户名或密码错误");

}
