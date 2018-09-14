package com.geeklog.controller.user

import com.geeklog.common.exception.RoleException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import com.geeklog.domain.User
import com.geeklog.dto.UserRegistry
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import spock.lang.Ignore

class UserControllerSpec extends LoggedController {

    def "GET /users/{user_id}"() {
        getAuthorization()

        when: "用户不存在"
        def entity = restTemplate.exchange("$URL_PREFFIX/users/60",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.USER_NOT_EXIST.code
            body.message == RoleException.USER_NOT_EXIST.message
            body.data == null
        }

        when: "正常获取普通用户"
        entity = restTemplate.exchange("$URL_PREFFIX/users/6",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            body.data == [
                    user_id: 6,
                    username: "f123456",
                    nickname: "小付",
                    avatar: null,
                    bio: null,
                    is_admin: false,
                    can_write_article: false,
                    can_comment: true

            ]
        }

        when: "正常获取管理员"
        entity = restTemplate.exchange("$URL_PREFFIX/users/2",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            body.data == [
                    user_id: 2,
                    username: "b1234567",
                    nickname: "测试Nickname",
                    avatar: "测试路径222",
                    bio: "测试Bio",
                    is_admin: true,
                    can_write_article: true,
                    can_comment: true
            ]
        }
    }

    @Ignore
    def "POST /users"() {
        getAuthorization()

        UserRegistry newUser = new UserRegistry()
        newUser.username = "a123456"
        newUser.password = "123456"
        newUser.nickname = "phr"
        newUser.bio = "个人简介"

        when: "用户已存在"
        def entity = restTemplate.exchange("$URL_PREFFIX/users",
                HttpMethod.POST,
                new HttpEntity<Object>(newUser, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.USER_ALREADY_EXIST.code
            body.message == RoleException.USER_ALREADY_EXIST.message
            body.data == null
        }

        when: "正常注册"
        newUser.username = "phr2018"
        entity = restTemplate.exchange("$URL_PREFFIX/users",
                HttpMethod.POST,
                new HttpEntity<Object>(newUser, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "注册成功"
            body.data == [
                    user_id: null,
                    username: newUser.username,
                    nickname: newUser.nickname,
                    avatar: null,
                    bio: newUser.bio,
                    is_admin: false,
                    can_comment: true,
                    can_write_article: true
            ]
        }
    }
}
