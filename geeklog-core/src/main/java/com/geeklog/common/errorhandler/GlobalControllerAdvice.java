package com.geeklog.common.errorhandler;

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
            logger.error(validatorException.getLog());
        }

        return ResponseEntity.build(validatorException.getCode(), validatorException.getMessage());
    }
}
