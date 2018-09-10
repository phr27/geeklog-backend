package com.geeklog.common.annotation;

import java.lang.annotation.*;

import com.geeklog.common.enumeration.Role;

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：被标记的类和方法需要具有指定的角色才能访问
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireRole {

    Role value();

}
