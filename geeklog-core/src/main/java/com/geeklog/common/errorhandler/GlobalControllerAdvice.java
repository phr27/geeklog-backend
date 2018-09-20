package com.geeklog.common.errorhandler;

import com.geeklog.common.exception.*;
import com.geeklog.common.util.ResponseEntity;
import io.undertow.server.handlers.form.MultiPartParserDefinition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.util.NestedServletException;

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

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：处理上传文件时没有提供文件的异常
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException multipartException) {
        if (StringUtils.equals(multipartException.getMessage(), "Current request is not a multipart request")) {
            return ResponseEntity.build(ValidatorException.NO_MULTIPART_FILE.getCode(), ValidatorException.NO_MULTIPART_FILE.getMessage());
        }

        logger.error("Unknown MultipartException", multipartException);
        return ResponseEntity.serverInnerError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/20
     * 功能：处理 FTP 上传或删除文件过程中的异常
     */
    @ExceptionHandler(FTPException.class)
    public ResponseEntity<Object> handleFTPException(FTPException ftpException) {
        if (ftpException.getCause() != null && logger.isErrorEnabled()) {
            logger.error("Unknown FTPException", ftpException.getCause());
        }

        return ResponseEntity.build(ftpException.getCode(), ftpException.getMessage());
    }


}
