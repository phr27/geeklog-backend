package com.geeklog.controller.user

import com.geeklog.common.exception.RoleException
import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import com.geeklog.dto.StarRequestBody
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

class StarControllerSpec extends LoggedController {

    def "POST /add-star"() {
        getAuthorization()

        StarRequestBody starRequestBody = new StarRequestBody()

        when: "未提供用户 id"
        def entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.NO_STAR_INFO.code
            body.message == ValidatorException.NO_STAR_INFO.message
            body.data == null
        }

        when: "未提供文章 id"
        starRequestBody.userId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.NO_STAR_INFO.code
            body.message == ValidatorException.NO_STAR_INFO.message
            body.data == null
        }

        when: "用户 id 不是当前会话的用户 id"
        starRequestBody.articleId = 50
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.OTHER_USER_STAR.code
            body.message == RoleException.OTHER_USER_STAR.message
            body.data == null
        }

        when: "文章不存在"
        starRequestBody.userId = 1
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ARTICLE_NOT_EXIST.code
            body.message == ValidatorException.ARTICLE_NOT_EXIST.message
            body.data == null
        }

        when: "已点过赞再次点赞"
        starRequestBody.articleId = 1
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ALREADY_STAR.code
            body.message == ValidatorException.ALREADY_STAR.message
            body.data == null
        }

        when: "正常点赞"
        starRequestBody.articleId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                star_id != null
                user_id == 1
                article_id == 3
            }
        }
    }

    def "POST /delete-star"() {
        getAuthorization()

        StarRequestBody starRequestBody = new StarRequestBody()
        starRequestBody.userId = 1
        starRequestBody.articleId = 5

        when: "取消点赞后再次取消点赞"
        def entity = restTemplate.exchange("$URL_PREFFIX/delete-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ALREADY_UNSTAR.code
            body.message == ValidatorException.ALREADY_UNSTAR.message
            body.data == null
        }

        when: "正常取消点赞"
        starRequestBody.articleId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/delete-star",
                HttpMethod.POST,
                new HttpEntity<>(starRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                star_id != null
                user_id == 1
                article_id == 3
            }
        }
    }
}
