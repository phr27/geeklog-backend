package com.geeklog.common.aspect;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.JwtUtil;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.SessionContext;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：角色检查切面
 */
@Component
@Aspect
public class RoleAspect {

    public static final String AUTH_PREFIX = "Bearer ";

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：切点定义，所有标注了 @RequireRole 的类（中的所有方法）或方法
     */
    @Pointcut("@within(com.geeklog.common.annotation.RequireRole) || @annotation(com.geeklog.common.annotation.RequireRole)")
    public void checkRolePointcut() {
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：角色检查切面
     */
    @Around("checkRolePointcut()")
    public ResponseEntity checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authStr = request.getHeader("Authorization");
        Validator.notBlank(authStr, ValidatorException.NO_JWT_TOKEN);
        Validator.startsWith(authStr, AUTH_PREFIX, ValidatorException.NO_JWT_TOKEN);

        String jwtStr = authStr.substring(AUTH_PREFIX.length());
        Validator.notBlank(jwtStr, ValidatorException.NO_JWT_TOKEN);
        User currentUser = JwtUtil.parseJwt(jwtStr);
        // jwt 正确解析

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RequireRole requireRoleAnnotation = method.getClass().getAnnotation(RequireRole.class);
        if (requireRoleAnnotation == null) {
            requireRoleAnnotation = method.getDeclaringClass().getAnnotation(RequireRole.class);
        }
        Validator.notNull(requireRoleAnnotation, ValidatorException.unexpected("checkRole 切面未找到 @RequireRole"));
        if (Role.ADMIN.equals(requireRoleAnnotation.value()) && !currentUser.getIsAdmin()) {
            throw RoleException.NOT_ADMIN;
        }
        // 角色检查通过

        SessionContext.setCurrentUser(currentUser);

        return (ResponseEntity) joinPoint.proceed(joinPoint.getArgs());
    }
}
