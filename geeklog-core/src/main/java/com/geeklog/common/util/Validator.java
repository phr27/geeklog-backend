package com.geeklog.common.util;

import java.util.Objects;
import java.util.regex.Pattern;

import com.geeklog.common.exception.ValidatorException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：参数校验工具类
 */
public class Validator {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：密码格式
     */
    private static final String PASSWORD_PATTERN = "(?:\\w|[~`!@#$%^&*?,:;()\\-.+={}\\[\\]]){6,}";

    private static final String USERNAME_PATTERN = "\\w{6,20}";

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：断言 @param object 不为 null，断言失败就抛出异常
     */
    public static void notNull(Object object, ValidatorException validatorException) {
        if (object == null) {
            throw validatorException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param str 不为 null 和空字符串，断言失败就抛出异常
     */
    public static void notBlank(String str, ValidatorException validatorException) {
        if (StringUtils.isBlank(str)) {
            throw validatorException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param password 符合格式，断言失败就抛出异常
     */
    public static void password(String password) {
        if (StringUtils.isBlank(password) || !Pattern.matches(PASSWORD_PATTERN, password)) {
            throw ValidatorException.PWD_INVALID;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param password 符合格式，断言失败就引发错误
     */
    public static void password(String password, String log) {
        if (StringUtils.isBlank(password) || !Pattern.matches(PASSWORD_PATTERN, password)) {
            throw ValidatorException.unexpected(log);
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param username 符合格式，断言失败就抛出异常
     */
    public static void username(String username) {
        if (StringUtils.isBlank(username) || !Pattern.matches(USERNAME_PATTERN, username)) {
            throw ValidatorException.USERNAME_INVALID;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param username 符合格式，断言失败就引发错误
     */
    public static void username(String username, String log) {
        if (StringUtils.isBlank(username) || !Pattern.matches(USERNAME_PATTERN, username)) {
            throw ValidatorException.unexpected(log);
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param bool 为 true，断言失败就抛出异常
     */
    public static void isTrue(Boolean bool, ValidatorException validatorException) {
        if (!bool) {
            throw validatorException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：断言 @param str 有前缀 prefix
     */
    public static void startsWith(String str, String prefix, ValidatorException validatorException) {
        if (str == null || !str.startsWith(prefix)) {
            throw validatorException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：断言 @param value 大于等于 min
     */
    public static void min(int value, int min, ValidatorException validatorException) {
        if (value < min) {
            throw validatorException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：断言 @param value 小于等于 max
     */
    public static void max(int value, int max, ValidatorException validatorException) {
        if (value > max) {
            throw validatorException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：断言两个对象相等
     */
    public static void equals(Object object, Object other, ValidatorException validatorException) {
        if (!Objects.equals(object, other)) {
            throw validatorException;
        }
    }
}
