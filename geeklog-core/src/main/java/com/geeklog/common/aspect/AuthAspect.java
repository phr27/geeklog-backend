package com.geeklog.common.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import javax.servlet.http.HttpServletRequest;

import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.JwtUtil;
import com.geeklog.common.util.SessionContext;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：角色检查和权限检查切面的父类
 * @param <T> 标记切点的注解的类型
 */
public abstract class AuthAspect<T extends Annotation> {

    public static final String AUTH_PREFIX = "Bearer ";

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：切点
     */
    protected abstract void pointcut();

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：注解找不到时输出的异常信息
     */
    protected abstract String errorLogWhenAnnotationNotFound();

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：执行校验
     */
    protected abstract void doAuth(User jwtUser, T annotation);

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：根据请求头中的 jwt token 解析出用户信息
     */
    private User getJwtUser(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authStr = request.getHeader("Authorization");
        Validator.notBlank(authStr, ValidatorException.NO_JWT_TOKEN);
        Validator.startsWith(authStr, AUTH_PREFIX, ValidatorException.NO_JWT_TOKEN);

        String jwtStr = authStr.substring(AUTH_PREFIX.length());
        Validator.notBlank(jwtStr, ValidatorException.NO_JWT_TOKEN);
        return JwtUtil.parseJwt(jwtStr);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：获取标记切点的注解的实例
     */
    private T getAnnotation(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T annotation = method.getAnnotation(tClass);
        if (annotation == null) {
            annotation = method.getDeclaringClass().getAnnotation(tClass);
        }

        return annotation;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：校验流程模板方法
     */
    @Around("pointcut()")
    protected Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        User jwtUser = getJwtUser(joinPoint);
        T annotation = getAnnotation(joinPoint);
        Validator.notNull(annotation, ValidatorException.unexpected(errorLogWhenAnnotationNotFound()));

        doAuth(jwtUser, annotation);

        SessionContext.setCurrentUser(jwtUser);
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
