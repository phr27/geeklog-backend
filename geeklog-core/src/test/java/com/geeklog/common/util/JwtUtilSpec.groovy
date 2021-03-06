package com.geeklog.common.util

import com.geeklog.common.exception.SessionException
import com.geeklog.common.exception.ValidatorException
import com.geeklog.domain.User
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import spock.lang.Specification

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：Jwt 工具类单元测试
 */
class JwtUtilSpec extends BaseUtilSpec {

    def "JwtUtil test"() {
        User testUser = new User()
        testUser.userId = null
        testUser.username = "李四"
        testUser.nickname = "李狗蛋"
        testUser.avatar = "/a/b"
        testUser.isAdmin = false

        when: "owner 为 null 时"
        JwtUtil.createJwt(null)
        then:
        ValidatorException validatorException = thrown()
        unexpectedValidatorError(validatorException, "createJwt(owner cannot be null)")

        when: "owner id 为 null 时"
        JwtUtil.createJwt(testUser)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "createJwt(owner id cannot be null)")

        when: "要解析的 jwt 为 null"
        JwtUtil.parseJwt(null)
        then:
        validatorException = thrown()
        unexpectedValidatorError(validatorException, "parseJwt(jwt cannot be blank)")

        when: "要解析的 jwt 格式错误"
        JwtUtil.parseJwt("123456")
        then:
        SessionException sessionException = thrown()
        with(sessionException) {
            code == 630
            message == "会话无效，请重新登录"
            JwtException.isAssignableFrom(cause.class)
        }

        when: "正确解析"
        testUser.userId = 1
        String jwt = JwtUtil.createJwt(testUser)
        println jwt
        User parsedUser = JwtUtil.parseJwt(jwt)
        then:
        notThrown(Throwable)
        testUser.userId == parsedUser.userId
        testUser.username == parsedUser.username
        testUser.nickname == parsedUser.nickname
        testUser.avatar == parsedUser.avatar
        testUser.isAdmin == parsedUser.isAdmin
    }
}
