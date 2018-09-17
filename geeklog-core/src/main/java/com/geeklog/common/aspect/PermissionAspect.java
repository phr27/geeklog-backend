package com.geeklog.common.aspect;

import java.util.List;

import com.geeklog.common.annotation.RequirePermission;
import com.geeklog.common.exception.PermissionException;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.mapper.ForbiddenMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：权限检查切面
 */
@Aspect
@Component
public class PermissionAspect extends AuthAspect<RequirePermission> {

    @Autowired
    private ForbiddenMapper forbiddenMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：切点定义，所有标注了 @RequirePermission 的类（中的所有方法）或方法
     */
    @Override
    @Pointcut("@within(com.geeklog.common.annotation.RequirePermission) || @annotation(com.geeklog.common.annotation.RequirePermission)")
    public void pointcut() {
    }

    @Override
    protected String errorLogWhenAnnotationNotFound() {
        return "权限检查切面未找到 @RequirePermission";
    }

    @Override
    protected void doAuth(User jwtUser, RequirePermission annotation) {
        if (jwtUser.getIsAdmin()) {
            return;
        }

        List<Forbidden> forbiddens = forbiddenMapper.queryByUserId(jwtUser.getUserId());
        if (forbiddens == null || forbiddens.size() == 0) {
            return;
        }

        for (Forbidden forbidden: forbiddens) {
            if (forbidden.getAuthorityId() == annotation.value().getCode()) {
                switch (annotation.value()) {
                    case CAN_WRITE_ARTICLE:
                        throw PermissionException.NO_WRITE_ARTICLE_PERMISSION;
                    case CAN_COMMENT:
                        throw PermissionException.NO_COMMENT_PERMISSION;
                }
            }
        }
    }
}
