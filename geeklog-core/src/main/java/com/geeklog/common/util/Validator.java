package com.geeklog.common.util;

import java.util.Objects;
import java.util.regex.Pattern;

import com.geeklog.common.enumeration.Permission;
import com.geeklog.common.exception.CommonException;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.StarCollectBaseException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.domain.User;
import com.geeklog.dto.StarCollectRequestBody;
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

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：用户名格式
     */
    private static final String USERNAME_PATTERN = "\\w{6,20}";

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：断言 @param object 不为 null，断言失败就抛出异常
     */
    public static void notNull(Object object, CommonException commonException) {
        if (object == null) {
            throw commonException;
        }
    }

    /**
     * @author 午康俊
     * 创建时间 2018/09/13
     * 功能：断言 @param object 为 null，断言失败就抛出异常
     */
    public static void isNull(Object object, CommonException commonException) {
        if (object != null) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：断言 @param str 不为 null 和空字符串，断言失败就抛出异常
     */
    public static void notBlank(String str, CommonException commonException) {
        if (StringUtils.isBlank(str)) {
            throw commonException;
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
    public static void isTrue(Boolean bool, CommonException commonException) {
        if (!bool) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：断言 @param str 有前缀 prefix
     */
    public static void startsWith(String str, String prefix, CommonException commonException) {
        if (str == null || !str.startsWith(prefix)) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：断言 @param value 大于等于 min
     */
    public static void min(int value, int min, CommonException commonException) {
        if (value < min) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：断言 @param value 小于等于 max
     */
    public static void max(int value, int max, CommonException commonException) {
        if (value > max) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：断言两个对象相等
     */
    public static void equals(Object object, Object other, CommonException commonException) {
        if (!Objects.equals(object, other)) {
            throw commonException;
        }
    }

    /**
     * @author 午康俊
     * 创建时间 2018/09/12
     * 功能：断言 @param authority 在规定范围内
     * 修改时间：2018/09/12
     * 修改人 潘浩然
     */
    public static void isLegal(int value, CommonException commonException) {
        if (Permission.getPermission(value) == null) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：断言 userId 和当前会话的用户 id 一致，若当前会话的用户为 null，则断言成功
     */
    public static void isCurrentUser(int userId, CommonException commonException) {
        User currentUser = SessionContext.getCurrentUser();
        if (currentUser != null && currentUser.getUserId() != userId) {
            throw commonException;
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：校验点赞（收藏）或取消点赞（收藏）的请求体
     */
    public static void validStarCollectRequestBody(StarCollectRequestBody starCollectRequestBody,
                                                   StarCollectBaseException starCollectBaseException) {
        Validator.notNull(starCollectRequestBody, starCollectBaseException.getValidatorException());
        Validator.notNull(starCollectRequestBody.getUserId(), starCollectBaseException.getValidatorException());
        Validator.notNull(starCollectRequestBody.getArticleId(), starCollectBaseException.getValidatorException());
        Validator.isCurrentUser(starCollectRequestBody.getUserId(), starCollectBaseException.getRoleException());
    }
}
