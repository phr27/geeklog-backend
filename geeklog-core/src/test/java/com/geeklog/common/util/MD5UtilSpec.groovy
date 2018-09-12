package com.geeklog.common.util

import com.geeklog.common.exception.ValidatorException
import spock.lang.Shared

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：MD5Util 工具类测试
 */
class MD5UtilSpec extends BaseUtilSpec {

    @Shared String encoded = null

    static final String plainText = "feiuhfw#%^*(f234fsdf"

    def "MD5Util.md5 test"() {
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

        when: "要加密的字符串不符合密码格式（长度不够）"
        MD5Util.md5("12345")
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.md5(plainText 不符合密码格式)")

        when: "要加密的字符串不符合密码格式（出现不允许字符，比如空格和中文）"
        MD5Util.md5("  中文  ")
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.md5(plainText 不符合密码格式)")

        when: "正常加密"
        String encodedPwd = MD5Util.md5(plainText)
        println encodedPwd
        encoded = encodedPwd
        then:
        notThrown(Throwable)
    }

    def "MD5Util.verify test"() {
        when: "要验证的字符串为 null"
        MD5Util.verify(null, encoded)
        then:
        ValidatorException validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.verify(plainText 不符合密码格式)")

        when: "要验证的字符串为空串"
        MD5Util.verify("    ", encoded)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.verify(plainText 不符合密码格式)")

        when: "要验证的字符串不符合密码格式（长度不够）"
        MD5Util.verify("12345", encoded)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.verify(plainText 不符合密码格式)")

        when: "要验证的字符串不符合密码格式（出现不允许字符，比如空格和中文）"
        MD5Util.verify("  中文  ", encoded)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "MD5Util.verify(plainText 不符合密码格式)")

        expect: "加密密码为 null"
        !MD5Util.verify(plainText, null)
        and: "加密密码为空串"
        !MD5Util.verify(plainText, "   ")
        and: "加密密码长度不对"
        !MD5Util.verify(plainText, "123456789")
        and: "验证不通过"
        !MD5Util.verify("123456", "8479f8a5156cfbcaf506fff6012df8c4c3d8c8")
        and: "验证通过"
        MD5Util.verify(plainText, encoded)
    }

}
