package com.geeklog.common.util;

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
     * 创建时间 2018/09/09
     * 功能：断言 @param object 不为 null，断言失败就抛出异常
     */
    public static void notNull(Object object, ValidatorException validatorException) {
        if (object == null) {
            throw validatorException;
        }
    }

    public static void notBlank(String str, ValidatorException validatorException) {
        if (StringUtils.isBlank(str)) {
            throw validatorException;
        }
    }
}
