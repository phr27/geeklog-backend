package com.geeklog.controller.admin

import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import spock.lang.Ignore

import java.text.SimpleDateFormat

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员评论管理控制器的单元测试
 */
class CommentControllerSpec extends LoggedController {

    def "CommentController GET /admin/comments?article_id=#article_id&page=#page&size=#size"() {
        getAuthorization()

        when: "没有文章 id 参数"
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/comments?page=3&size=2",
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
                    total   : 9,
                    entities: [
                            [
                                    comment_id: 5,
                                    user_id   : 6,
                                    article_id: 2,
                                    content   : "学习前端Vue中，写的不错",
                                    parent_id : null,
                                    root_id   : 5,
                                    created_at: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-09-06 09:59:48").getTime()
                            ],
                            [
                                    comment_id: 6,
                                    user_id   : 2,
                                    article_id: 2,
                                    content   : "感谢感谢，一点自己的心得",
                                    parent_id : 5,
                                    root_id   : 5,
                                    created_at: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-09-06 09:59:55").getTime()
                            ]
                    ]
            ]
        }

        when: "文章不存在"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/comments?article_id=20&page=1&size=10",
                HttpMethod.GET,
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

        when: "文章存在，正常分页"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/comments?article_id=2&page=2&size=2",
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
                    total   : 4,
                    entities: [
                            [
                                    comment_id: 7,
                                    user_id   : 7,
                                    article_id: 2,
                                    content   : "写的不错",
                                    parent_id : 5,
                                    root_id   : 5,
                                    created_at: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-09-06 10:00:23").getTime()
                            ],
                            [
                                    comment_id: 8,
                                    user_id   : 9,
                                    article_id: 2,
                                    content   : "我也觉得确实写的不错",
                                    parent_id : 7,
                                    root_id   : 5,
                                    created_at: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-09-06 10:03:06").getTime()
                            ]
                    ]
            ]
        }
    }

    @Ignore
    def "CommentController DELETE /comments/{comment_id}"() {
        getAuthorization()

        when: "评论 id 不存在"
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/comments/20",
                HttpMethod.DELETE,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.COMMENT_NOT_EXIST.code
            body.message == ValidatorException.COMMENT_NOT_EXIST.message
            body.data == null
        }

        when: "正常删除"
        entity = restTemplate.exchange("$URL_PREFFIX/admin/comments/10",
                HttpMethod.DELETE,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            body.data == [
                    comment_id: 10,
                    user_id   : 10,
                    article_id: 4,
                    content   : "文章在这里XXX，我觉得写得很不错",
                    parent_id : null,
                    root_id   : 10,
                    created_at: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-09-06 10:06:09").getTime()
            ]
        }
    }

}
