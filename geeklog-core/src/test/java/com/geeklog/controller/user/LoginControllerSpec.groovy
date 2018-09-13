package com.geeklog.controller.user

import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.ControllerSpecification
import com.geeklog.dto.AuthToken
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

/**
 * @author 潘浩然
 * 创建时间 2018/09/13
 * 功能：用户登录控制器单元测试
 */
class LoginControllerSpec extends ControllerSpecification {

    def "POST /login"() {
        AuthToken authToken = new AuthToken()

        when: "没有请求体，bad request"
        def entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 400
            body.code == 400
            body.message == HttpStatus.BAD_REQUEST.reasonPhrase
            body.data == null
        }

        when: "用户名为空"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.USERNAME_INVALID.code
            body.message == ValidatorException.USERNAME_INVALID.message
            body.data == null
        }

        when: "用户名格式错误"
        authToken.username = " 中文 "
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.USERNAME_INVALID.code
            body.message == ValidatorException.USERNAME_INVALID.message
            body.data == null
        }

        when: "密码为空"
        authToken.username = "phr272018"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.PWD_INVALID.code
            body.message == ValidatorException.PWD_INVALID.message
            body.data == null
        }

        when: "密码格式错误"
        authToken.password = "12345"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.PWD_INVALID.code
            body.message == ValidatorException.PWD_INVALID.message
            body.data == null
        }

        when: "用户不存在"
        authToken.username = "phr272018"
        authToken.password = "123456"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.USERNAME_OR_PWD_ERROR.code
            body.message == ValidatorException.USERNAME_OR_PWD_ERROR.message
            body.data == null
        }

        when: "密码错误"
        authToken.username = "a123456"
        authToken.password = "1234567"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.USERNAME_OR_PWD_ERROR.code
            body.message == ValidatorException.USERNAME_OR_PWD_ERROR.message
            body.data == null
        }

        when: "普通用户登录"
        authToken.username = "c123456"
        authToken.password = "123456"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "登录成功"
            println body.data
        }

        when: "管理员登录"
        authToken.username = "a123456"
        authToken.password = "123456"
        entity = restTemplate.exchange("$URL_PREFFIX/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "登录成功"
            println body.data
        }

    }
}
