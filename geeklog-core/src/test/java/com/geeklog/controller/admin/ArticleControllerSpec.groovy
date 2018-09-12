package com.geeklog.controller.admin

import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

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
}
