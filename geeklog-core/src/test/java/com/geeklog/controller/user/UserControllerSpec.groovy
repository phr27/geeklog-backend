package com.geeklog.controller.user

import com.geeklog.common.exception.RoleException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

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
}
