package com.geeklog.common.errorhandler;

import com.geeklog.common.exception.PermissionException;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.SessionException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：全局异常处理器
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：处理数据校验异常
     */
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<Object> handleValidatorException(ValidatorException validatorException) {
        if (validatorException.isInnerError() && logger.isErrorEnabled()) {
            logger.error(validatorException.getLog(), validatorException);
        }

        return ResponseEntity.build(validatorException.getCode(), validatorException.getMessage());
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/10
     * 功能：处理会话异常
     */
    @ExceptionHandler(SessionException.class)
    public ResponseEntity<Object> handleSessionException(SessionException sessionException) {
        Throwable cause = sessionException.getCause();
        if (cause != null && logger.isErrorEnabled()) {
            logger.error("session exception caused by {}: {}", cause.getClass().getSimpleName(), cause.getMessage());
        }
        return ResponseEntity.build(sessionException.getCode(), sessionException.getMessage());
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：处理角色检查异常
     */
    @ExceptionHandler(RoleException.class)
    public ResponseEntity<Object> handleRoleException(RoleException roleException) {
        return ResponseEntity.build(roleException.getCode(), roleException.getMessage());
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：处理权限检查异常
     */
    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<Object> handlePermissionException(PermissionException permissionException) {
        return ResponseEntity.build(permissionException.getCode(), permissionException.getMessage());
    }
}
