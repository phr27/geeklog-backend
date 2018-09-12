package com.geeklog.common.util;

import com.geeklog.domain.User;

/**
 * @author 潘浩然
 * 创建时间 2018/09/11
 * 功能：会话上下文，封装当前会话的用户
 */
public class SessionContext {

    private static final ThreadLocal<User> sessionUser = ThreadLocal.withInitial(() -> null);

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：获取当前会话的用户
     */
    public static User getCurrentUser() {
        return sessionUser.get();
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：设置当前会话的用户
     */
    public static void setCurrentUser(User user) {
        sessionUser.set(user);
    }
}
