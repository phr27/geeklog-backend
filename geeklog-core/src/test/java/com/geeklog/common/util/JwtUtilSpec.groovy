package com.geeklog.common.util

import com.geeklog.common.exception.JwtParseException
import com.geeklog.domain.User
import io.jsonwebtoken.JwtException
import spock.lang.Specification

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：Jwt 工具类单元测试
 */
class JwtUtilSpec extends Specification {

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
        NullPointerException nullPointerException = thrown()
        nullPointerException.message == "owner cannot be null"

        when: "owner id 为 null 时"
        JwtUtil.createJwt(testUser)
        then:
        nullPointerException = thrown()
        nullPointerException.message == "owner id cannot be null"

        when: "要解析的 jwt 为 null"
        JwtUtil.parseJwt(null)
        then:
        JwtParseException jwtParseException = thrown()
        jwtParseException.message == "会话无效，请重新登录"
        IllegalArgumentException.isAssignableFrom(jwtParseException.cause.class)

        when: "要解析的 jwt 格式错误"
        JwtUtil.parseJwt("123456")
        then:
        jwtParseException = thrown()
        jwtParseException.message == "会话无效，请重新登录"
        JwtException.isAssignableFrom(jwtParseException.cause.class)

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
