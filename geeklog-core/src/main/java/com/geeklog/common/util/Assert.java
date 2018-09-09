package com.geeklog.common.util;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：参数校验工具类
 */
public class Assert {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：断言 @param object 不为 null，断言失败就抛出异常
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }
}
