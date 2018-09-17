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
public class RoleAspect extends AuthAspect<RequireRole> {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：切点定义，所有标注了 @RequireRole 的类（中的所有方法）或方法
     */
    @Override
    @Pointcut("@within(com.geeklog.common.annotation.RequireRole) || @annotation(com.geeklog.common.annotation.RequireRole)")
    public void pointcut() {
    }

    @Override
    protected String errorLogWhenAnnotationNotFound() {
        return "角色检查切面未找到 @RequireRole";
    }

    @Override
    protected void doAuth(User jwtUser, RequireRole annotation) {
        if (Role.ADMIN.equals(annotation.value()) && !jwtUser.getIsAdmin()) {
            throw RoleException.NOT_ADMIN;
        }
    }
}
