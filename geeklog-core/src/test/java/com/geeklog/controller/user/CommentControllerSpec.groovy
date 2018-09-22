package com.geeklog.controller.user

import com.geeklog.common.exception.PermissionException
import com.geeklog.common.exception.RoleException
import com.geeklog.common.exception.ValidatorException
import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import com.geeklog.dto.AuthToken
import com.geeklog.dto.CommentPublish
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
                            comment_id: 9,
                            user_id   : 1,
                            article_id: 3,
                            content   : "学习了，学习了",
                            parent_id : null,
                            root_id   : 9,
                            created_at: new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-09-06 10:06:01").getTime()
                    ]
            ]
        }
    }

    def "POST /comments"() {

        CommentPublish commentPublish = new CommentPublish()

        when: "没有评论权限"
        getAuthorization(new AuthToken("e123456", "123456"))
        def entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == PermissionException.NO_COMMENT_PERMISSION.code
            body.message == PermissionException.NO_COMMENT_PERMISSION.message
            body.data == null
        }

        when: "没有 user id"
        getAuthorization(new AuthToken("c123456", "123456"))
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.NO_COMMENT_PUBLISH_INFO.code
            body.message == ValidatorException.NO_COMMENT_PUBLISH_INFO.message
            body.data == null
        }

        when: "评论内容为空"
        commentPublish.userId = 1
        commentPublish.content = ""
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.COMMENT_CONTENT_BLANK.code
            body.message == ValidatorException.COMMENT_CONTENT_BLANK.message
            body.data == null
        }

        when: "user id 和当前会话用户的 id 不一致"
        commentPublish.content = "评论内容"
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == RoleException.OTHER_USER_COMMENT.code
            body.message == RoleException.OTHER_USER_COMMENT.message
            body.data == null
        }

        when: "父评论不存在"
        commentPublish.userId = 3
        commentPublish.parentId = 50
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.PARENT_COMMENT_NOT_EXIST.code
            body.message == ValidatorException.PARENT_COMMENT_NOT_EXIST.message
            body.data == null
        }

        when: "正常发布评论的评论"
        commentPublish.userId = 3
        commentPublish.parentId = 9
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                comment_id != null
                user_id == commentPublish.userId
                article_id == 3
                content == commentPublish.content
                parent_id == commentPublish.parentId
                root_id == 9
                created_at != null
            }

        }

        when: "parent id 和 article id 同时为 null"
        commentPublish.userId = 3
        commentPublish.parentId = null
        commentPublish.articleId = null
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.NO_COMMENT_PUBLISH_INFO.code
            body.message == ValidatorException.NO_COMMENT_PUBLISH_INFO.message
            body.data == null
        }

        when: "文章不存在"
        commentPublish.userId = 3
        commentPublish.parentId = null
        commentPublish.articleId = 50
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == ValidatorException.ARTICLE_NOT_EXIST.code
            body.message == ValidatorException.ARTICLE_NOT_EXIST.message
            body.data == null
        }

        when: "正常发布一级评论"
        commentPublish.articleId = 2
        entity = restTemplate.exchange("$URL_PREFFIX/comments",
                HttpMethod.POST,
                new HttpEntity<Object>(commentPublish, headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
            with(body.data) {
                comment_id != null
                user_id == commentPublish.userId
                article_id == commentPublish.articleId
                content == commentPublish.content
                parent_id == commentPublish.parentId
                root_id == comment_id
                created_at != null
            }

        }
    }

    def "GET /articles/{article_id}/comments?page=#page&size=#size"() {
        when: "文章不存在"
        def entity = restTemplate.exchange("$URL_PREFFIX/articles/50/comments?page=1&size=2",
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
        when: "正常获取"
        entity = restTemplate.exchange("$URL_PREFFIX/articles/2/comments?page=1&size=2",
                HttpMethod.GET,
                new HttpEntity<Object>(headers),
                ResponseEntity
        )
        then:
        with(entity) {
            statusCodeValue == 200
            body.code == 200
            body.message == "success"
        }
        with(entity.body.data) {
            total == 2
            entities[0] == [
                    comment_id: 5,
                    user_id   : 6,
                    article_id: 2,
                    content   : "学习前端Vue中，写的不错",
                    parent_id : null,
                    root_id   : 5,
                    created_at: new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-09-06 09:59:48").getTime(),
                    from_user_nickname: "小付",
                    to_user_nickname: null,
                    article_title: "Vue总结",
                    from_user_avatar: null,
                    to_user_avatar: null
            ]
            with(entities[1]) {
                comment_id == 12
                user_id == 3
                article_id == 2
                content == "评论内容"
                parent_id == null
                root_id == comment_id
                created_at != null
                from_user_nickname == "小菜1"
                to_user_nickname == null
                article_title == "Vue总结"
                from_user_avatar == "http://47.106.158.254/static/docker.jpeg"
                to_user_avatar == null
            }
        }
    }

    def "GET /comments/{comment_id}/sub_comments"() {
        when: "评论不存在"
        def entity = restTemplate.exchange("$URL_PREFFIX/comments/50/sub_comments?page=1&size=2",
                HttpMethod.GET,
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

        when: "正常获取"
        entity = restTemplate.exchange("$URL_PREFFIX/comments/5/sub_comments?page=1&size=2",
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
                    total: 3,
                    entities: [
                            [
                                    comment_id: 6,
                                    user_id   : 2,
                                    article_id: 2,
                                    content   : "感谢感谢，一点自己的心得",
                                    parent_id : 5,
                                    root_id   : 5,
                                    created_at: new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-09-06 09:59:55").getTime(),
                                    from_user_nickname: "测试Nickname",
                                    to_user_nickname: "小付",
                                    article_title: "Vue总结",
                                    from_user_avatar: null,
                                    to_user_avatar: null
                            ],
                            [
                                    comment_id: 7,
                                    user_id   : 7,
                                    article_id: 2,
                                    content   : "写的不错",
                                    parent_id : 5,
                                    root_id   : 5,
                                    created_at: new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2018-09-06 10:00:23").getTime(),
                                    from_user_nickname: "小高",
                                    to_user_nickname: "小付",
                                    article_title: "Vue总结",
                                    from_user_avatar: null,
                                    to_user_avatar: null
                            ]
                    ]
            ]
        }
    }
}
