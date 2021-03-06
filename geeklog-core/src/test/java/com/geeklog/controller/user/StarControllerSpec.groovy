package com.geeklog.controller.user

import com.geeklog.common.exception.RoleException
import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import com.geeklog.dto.StarCollectRequestBody
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：点赞控制器单元测试
 */
class StarControllerSpec extends LoggedController {

    def "POST /add-star"() {
        getAuthorization()

        StarCollectRequestBody starCollectRequestBody = new StarCollectRequestBody()

        when: "未提供用户 id"
        def entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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
        starCollectRequestBody.userId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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
        starCollectRequestBody.articleId = 50
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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
        starCollectRequestBody.userId = 1
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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
        starCollectRequestBody.articleId = 2
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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
        starCollectRequestBody.articleId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/add-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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

        StarCollectRequestBody starCollectRequestBody = new StarCollectRequestBody()
        starCollectRequestBody.userId = 1
        starCollectRequestBody.articleId = 3

        when: "正常取消点赞"
        def entity = restTemplate.exchange("$URL_PREFFIX/delete-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
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

        when: "取消点赞后再次取消点赞"
        entity = restTemplate.exchange("$URL_PREFFIX/delete-star",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ALREADY_UNSTAR.code
            body.message == ValidatorException.ALREADY_UNSTAR.message
            body.data == null
        }
    }
}
