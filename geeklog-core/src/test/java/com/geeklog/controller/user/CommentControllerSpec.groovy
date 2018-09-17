package com.geeklog.controller.user

import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

import java.text.SimpleDateFormat

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：用户模块的评论控制器单元测试
 */
class CommentControllerSpec extends LoggedController {

    def "GET /comments/latest/{count}"() {
        headers.remove("Authorization")
        assert headers.get("Authorization") == null

        when: "count == 0"
        def entity = restTemplate.exchange("$URL_PREFFIX/comments/latest/0",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.LATEST_COMMENT_COUNT_OUT_OF_RANGE.code
            body.message == ValidatorException.LATEST_COMMENT_COUNT_OUT_OF_RANGE.message
            body.data == null
        }

        when: "count == 1"
        entity = restTemplate.exchange("$URL_PREFFIX/comments/latest/1",
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
                    [
                            comment_id: 10,
                            user_id: 10,
                            article_id: 4,
                            content: "文章在这里XXX，我觉得写得很不错",
                            parent_id: null,
                            root_id: 10,
                            created_at: new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-09-06 10:06:09").getTime()
                    ]
            ]
        }
    }

//    def "POST /comments"() {
//
//        when: "没有评论权限"
//        getAuthorization("e123456", "123456")
//        then:
//
//    }
}
