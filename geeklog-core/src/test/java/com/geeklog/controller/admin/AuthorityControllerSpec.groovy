package com.geeklog.controller.admin

import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员权限管理控制器的单元测试
 */
class AuthorityControllerSpec extends LoggedController {

    def "AuthorityController GET /admin/authorities"() {
        getAuthorization()

        when:
        def entity = restTemplate.exchange("$URL_PREFFIX/admin/authorities",
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
                            authority_id: 1,
                            name: "can_write_article"
                    ],
                    [
                            authority_id: 2,
                            name: "can_comment"
                    ]
            ]
        }

    }
}
