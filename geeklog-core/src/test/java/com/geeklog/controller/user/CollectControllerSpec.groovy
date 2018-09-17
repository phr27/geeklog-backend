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
 * 功能：收藏控制器单元测试
 */
class CollectControllerSpec extends LoggedController {

    def "POST /add-collect"() {
        getAuthorization()

        StarCollectRequestBody starCollectRequestBody = new StarCollectRequestBody()

        when: "未提供用户 id"
        def entity = restTemplate.exchange("$URL_PREFFIX/add-collect",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.NO_COLLECT_INFO.code
            body.message == ValidatorException.NO_COLLECT_INFO.message
            body.data == null
        }

        when: "未提供文章 id"
        starCollectRequestBody.userId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/add-collect",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.NO_COLLECT_INFO.code
            body.message == ValidatorException.NO_COLLECT_INFO.message
            body.data == null
        }

        when: "用户 id 不是当前会话的用户 id"
        starCollectRequestBody.articleId = 50
        entity = restTemplate.exchange("$URL_PREFFIX/add-collect",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.OTHER_USER_COLLECT.code
            body.message == RoleException.OTHER_USER_COLLECT.message
            body.data == null
        }

        when: "文章不存在"
        starCollectRequestBody.userId = 1
        entity = restTemplate.exchange("$URL_PREFFIX/add-collect",
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

        when: "已收藏过再次收藏"
        starCollectRequestBody.articleId = 1
        entity = restTemplate.exchange("$URL_PREFFIX/add-collect",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ALREADY_COLLECT.code
            body.message == ValidatorException.ALREADY_COLLECT.message
            body.data == null
        }

        when: "正常收藏"
        starCollectRequestBody.articleId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/add-collect",
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
                collect_id != null
                user_id == 1
                article_id == 3
                created_at != null
            }
        }
    }

    def "POST /delete-collect"() {
        getAuthorization()

        StarCollectRequestBody starCollectRequestBody = new StarCollectRequestBody()
        starCollectRequestBody.userId = 1
        starCollectRequestBody.articleId = 5

        when: "取消收藏后再次取消收藏"
        def entity = restTemplate.exchange("$URL_PREFFIX/delete-collect",
                HttpMethod.POST,
                new HttpEntity<>(starCollectRequestBody, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ALREADY_UNCOLLECT.code
            body.message == ValidatorException.ALREADY_UNCOLLECT.message
            body.data == null
        }

        when: "正常取消收藏"
        starCollectRequestBody.articleId = 3
        entity = restTemplate.exchange("$URL_PREFFIX/delete-collect",
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
                collect_id != null
                user_id == 1
                article_id == 3
                created_at != null
            }
        }
    }
}
