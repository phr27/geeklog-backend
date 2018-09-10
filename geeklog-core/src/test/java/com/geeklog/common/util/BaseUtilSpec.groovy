package com.geeklog.common.util

import com.geeklog.common.exception.ValidatorException
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：工具类单元测试基类
 */
class BaseUtilSpec extends Specification {

    void unexpectedValidatorError(ValidatorException validatorException, String expectedLog) {
        with(validatorException) {
            assert code == 500
            assert message == HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
            assert innerError
            assert log == expectedLog
        }
    }
}
