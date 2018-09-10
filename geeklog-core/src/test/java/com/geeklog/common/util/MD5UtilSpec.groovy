package com.geeklog.common.util

import com.geeklog.common.exception.ValidatorException
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：MD5Util 工具类测试
 */
class MD5UtilSpec extends BaseUtilSpec {

    def "MD5Util test"() {
        when: "要加密的字符串为 null"
        MD5Util.md5(null)
        then:
        ValidatorException validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.md5(plainText 不符合密码格式)")

        when: "要加密的字符串为空串"
        MD5Util.md5("    ")
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.md5(plainText 不符合密码格式)")
    }

}
