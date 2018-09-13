package com.geeklog.controller.admin

import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import com.geeklog.dto.ArticleDisplaySetter
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import spock.lang.Ignore

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的文章管理控制器的单元测试
 */
class ArticleControllerSpec extends LoggedController {

    def "ArticleController GET /admin/articles?category_id=#category_id&page=#page&size=#size"() {
        getAuthorization() // 登录

        when: "没有 category_id 参数"
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/articles?page=2&size=2",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                total == 10
                entities[0].article_id == 8
                entities[1].article_id == 7
            }
        }

        when: "category_id 参数为 null"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles?page=1&size=2&category_id=null",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == HttpStatus.BAD_REQUEST.value()
            body.code == HttpStatus.BAD_REQUEST.value()
            body.message == HttpStatus.BAD_REQUEST.reasonPhrase
            body.data == null
        }

        when: "分类不存在"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles?page=2&size=2&category_id=8",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.CATEGORY_NOT_EXIST.code
            body.message == ValidatorException.CATEGORY_NOT_EXIST.message
            body.data == null
        }

        when: "分类下没有文章"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles?page=1&size=10&category_id=7",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            body.data == [
                    total   : 0,
                    entities: []
            ]
        }

        when: "分类下有文章，正常分页"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles?page=1&size=10&category_id=1",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                total == 2
                entities[0].article_id == 3
                entities[1].article_id == 2
            }
        }

    }

    @Ignore
    def "ArticleController DELETE /admin/articles/{article_id}"() {
        getAuthorization()

        when: "文章不存在"
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/articles/50",
                HttpMethod.DELETE,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ARTICLE_NOT_EXIST.code
            body.message == ValidatorException.ARTICLE_NOT_EXIST.message
            body.data == null
        }

        when: "正常删除"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles/1",
                HttpMethod.DELETE,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                article_id == 1
                title == "Java开发技巧"
                user_id == 1
                display == true
                category_id == 2
                tags == "Java"
            }

        }
    }

    def "ArticleController PUT /admin/articles/{article_id}"() {
        getAuthorization()

        when: "没有请求体"
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/articles/1",
                HttpMethod.PUT,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == HttpStatus.BAD_REQUEST.value()
            body.code == HttpStatus.BAD_REQUEST.value()
            body.message == HttpStatus.BAD_REQUEST.reasonPhrase
            body.data == null
        }

        when: "原来值和更新值一致"
        ArticleDisplaySetter displaySetter = new ArticleDisplaySetter()
        displaySetter.setDisplay(true)
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles/1",
                HttpMethod.PUT,
                new HttpEntity<Object>(displaySetter, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            body.data.display
        }

        when: "正常更新"
        displaySetter.setDisplay(false)
        entity = restTemplate.exchange("$URL_PREFFIX/admin/articles/1",
                HttpMethod.PUT,
                new HttpEntity<Object>(displaySetter, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            !body.data.display
        }
    }
}
