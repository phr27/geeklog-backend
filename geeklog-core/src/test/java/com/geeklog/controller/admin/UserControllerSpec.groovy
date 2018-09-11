package com.geeklog.controller.admin

import com.geeklog.common.aspect.RoleAspect
import com.geeklog.common.exception.RoleException
import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.JwtUtil
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.ControllerSpecification
import com.geeklog.domain.User
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class UserControllerSpec extends ControllerSpecification {

    def "GET /admin/users?page=#page&size=#size"() {

        when: "没有登录"
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=1&size=1",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            entity.statusCodeValue == 200
            body.code == ValidatorException.NO_JWT_TOKEN.code
            body.message == ValidatorException.NO_JWT_TOKEN.message
            body.data == null
        }

        when: "不是管理员"
        User user = new User()
        user.userId = 1
        user.username = "phr27"
        user.nickname = "phr"
        user.avatar = "/a/b"
        user.isAdmin = false
        String jwtStr = JwtUtil.createJwt(user)
        headers.add("Authorization", RoleAspect.AUTH_PREFIX + jwtStr)
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=1&size=1",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.NOT_ADMIN.code
            body.message == RoleException.NOT_ADMIN.message
            body.data == null
        }

        when: "缺少分页参数"
        user.isAdmin = true
        jwtStr = JwtUtil.createJwt(user)
        headers.set("Authorization", RoleAspect.AUTH_PREFIX + jwtStr)
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=1",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 400
            body.code == HttpStatus.BAD_REQUEST.value()
            body.message == HttpStatus.BAD_REQUEST.reasonPhrase
            body.data == null
        }

        when: "分页参数不是数字"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=a&size=1",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 400
            body.code == HttpStatus.BAD_REQUEST.value()
            body.message == HttpStatus.BAD_REQUEST.reasonPhrase
            body.data == null
        }

        when: "page < 1"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=0&size=1",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.PAGE_OUT_OF_RANGE.code
            body.message == ValidatorException.PAGE_OUT_OF_RANGE.message
            body.data == null
        }

        when: "page 太大"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=5&size=3",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.PAGE_OUT_OF_RANGE.code
            body.message == ValidatorException.PAGE_OUT_OF_RANGE.message
            body.data == null
        }

        when: "size < 1"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=5&size=-1",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.SIZE_OUT_OF_RANGE.code
            body.message == ValidatorException.SIZE_OUT_OF_RANGE.message
            body.data == null
        }

        when: "获取最后一页"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=3&size=3",
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
                    total: 8,
                    entities: [
                            [
                                    user_id: 9,
                                    username: "i123456",
                                    nickname: "小爱",
                                    avatar: null,
                                    is_admin: false,
                                    can_comment: true,
                                    can_write_article: true
                            ],
                            [
                                    user_id: 10,
                                    username: "j123456",
                                    nickname: "小建",
                                    avatar: null,
                                    is_admin: false,
                                    can_comment: true,
                                    can_write_article: true
                            ]
                    ]
            ]
        }

        when: "正常分页"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/users?page=2&size=2",
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
                    total: 8,
                    entities: [
                            [
                                    user_id: 5,
                                    username: "e123456",
                                    nickname: "小哦",
                                    avatar: null,
                                    is_admin: false,
                                    can_comment: false,
                                    can_write_article: true
                            ],
                            [
                                    user_id: 6,
                                    username: "f123456",
                                    nickname: "小付",
                                    avatar: null,
                                    is_admin: false,
                                    can_comment: true,
                                    can_write_article: false
                            ]
                    ]
            ]
        }
    }
}
