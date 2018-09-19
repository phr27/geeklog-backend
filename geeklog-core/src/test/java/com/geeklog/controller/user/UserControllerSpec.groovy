package com.geeklog.controller.user

import com.geeklog.common.exception.RoleException
import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.common.util.Validator
import com.geeklog.controller.LoggedController
import com.geeklog.dto.AuthToken
import com.geeklog.dto.PasswordUpdate
import com.geeklog.dto.UserInfoUpdate
import com.geeklog.dto.UserRegistry
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import spock.lang.Ignore

/**
 * @author 潘浩然
 * 创建时间 2018/09/14
 * 功能：用户模块的用户管理控制器单元测试
 */
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

        when: "正常获取普通用户"
        entity = restTemplate.exchange("$URL_PREFFIX/users/3",
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
                    user_id: 3,
                    username: "c123456",
                    nickname: "小菜1",
                    avatar: null,
                    bio: null,
                    is_admin: false,
                    can_write_article: true,
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
                    avatar: null,
                    bio: "测试Bio",
                    is_admin: true,
                    can_write_article: true,
                    can_comment: true
            ]
        }

    }

//    @Ignore
    def "POST /users"() {
        UserRegistry newUser = new UserRegistry()
        newUser.username = "c123456"
        newUser.password = "123456"
        newUser.nickname = "phr"
        newUser.bio = "个人简介"
        headers.remove("Authorization")
        assert headers.get("Authorization") == null

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

    def "PUT /users/{user_id}"() {
        getAuthorization()

        UserInfoUpdate userInfoUpdate = new UserInfoUpdate()

        when: "更新其他用户信息"
        def entity = restTemplate.exchange("$URL_PREFFIX/users/10",
                HttpMethod.PUT,
                new HttpEntity<Object>(userInfoUpdate, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.UPDATE_OTHER_USER.code
            body.message == RoleException.UPDATE_OTHER_USER.message
            body.data == null
        }

        when: "不更新所有字段"
        entity = restTemplate.exchange("$URL_PREFFIX/users/1",
                HttpMethod.PUT,
                new HttpEntity<Object>(userInfoUpdate, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "更新成功"
            body.data == [
                    user_id: null,
                    username: "a123456",
                    nickname: "小啊",
                    avatar: null,
                    bio: null,
                    is_admin: true,
                    can_comment: true,
                    can_write_article: true
            ]
        }

        when: "更新部分字段"
        userInfoUpdate.bio = "管理员bio"
        entity = restTemplate.exchange("$URL_PREFFIX/users/1",
                HttpMethod.PUT,
                new HttpEntity<Object>(userInfoUpdate, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "更新成功"
            body.data == [
                    user_id: null,
                    username: "a123456",
                    nickname: "小啊",
                    avatar: null,
                    bio: userInfoUpdate.bio,
                    is_admin: true,
                    can_comment: true,
                    can_write_article: true
            ]
        }

        when: "更新所有字段"
        userInfoUpdate.nickname = "小啊1"
        userInfoUpdate.bio = "管理员bio1"
        entity = restTemplate.exchange("$URL_PREFFIX/users/1",
                HttpMethod.PUT,
                new HttpEntity<Object>(userInfoUpdate, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "更新成功"
            body.data == [
                    user_id: null,
                    username: "a123456",
                    nickname: userInfoUpdate.nickname,
                    avatar: null,
                    bio: userInfoUpdate.bio,
                    is_admin: true,
                    can_comment: true,
                    can_write_article: true
            ]
        }
    }

    def "POST /change-password"() {
        getAuthorization()

        PasswordUpdate passwordUpdate = new PasswordUpdate()
        passwordUpdate.userId = 3
        passwordUpdate.oldPassword = "123456"
        passwordUpdate.newPassword = "1234567"

        when: "修改别人的密码"
        def entity = restTemplate.exchange("$URL_PREFFIX/change-password",
                HttpMethod.POST,
                new HttpEntity<>(passwordUpdate, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.UPDATE_OTHER_PWD.code
            body.message == RoleException.UPDATE_OTHER_PWD.message
            body.data == null
        }

        when: "正常修改密码为 1234567"
        passwordUpdate.userId = 1
        entity = restTemplate.exchange("$URL_PREFFIX/change-password",
                HttpMethod.POST,
                new HttpEntity<>(passwordUpdate, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "修改成功"
            body.data == [
                    user_id: null,
                    username: "a123456",
                    nickname: "小啊1",
                    avatar: null,
                    bio: "管理员bio1",
                    is_admin: true,
                    can_comment: true,
                    can_write_article: true
            ]
        }

        when: "使用旧密码登录失败"
        headers.remove("Authorization")
        assert headers.get("Authorization") == null
        AuthToken authToken = new AuthToken()
        authToken.username = "a123456"
        authToken.password = "123456"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/login",
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

        when: "新密码登录成功"
        authToken.password = "1234567"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "登录成功"
        }
        println entity.body.data
    }
}
