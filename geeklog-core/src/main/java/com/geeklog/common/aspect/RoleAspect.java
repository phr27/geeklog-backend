package com.geeklog.common.aspect;

import javax.servlet.http.HttpServletRequest;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：切点定义，所有标注了 @RequireRole 的类（中的所有方法）或方法
     */
    @Pointcut("@within(com.geeklog.common.annotation.RequireRole) || @annotation(com.geeklog.common.annotation.RequireRole)")
    public void checkRolePointcut() {
    }

    @Around("checkRolePointcut()")
    public ResponseEntity checkRole(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Validator.notBlank(request.getHeader("Authorization"), ValidatorException.NO_JWT_TOKEN);
        return null; // todo
    }
}
