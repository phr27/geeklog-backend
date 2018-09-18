package com.geeklog.controller.user

import com.geeklog.common.util.ResponseEntity
import com.geeklog.controller.LoggedController
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

class CategoryControllerSpec extends LoggedController {

    def "GET /categories"() {
        when:
        def entity = restTemplate.exchange("$URL_PREFFIX/categories",
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
                            category_id: 1,
                            name: "前端开发",
                            description: "这是前端"
                    ],
                    [
                            category_id: 2,
                            name: "后端开发",
                            description: "这是后端"
                    ],
                    [
                            category_id: 3,
                            name: "运维",
                            description: "这是运维"
                    ],
                    [
                            category_id: 4,
                            name: "测试",
                            description: "这是测试"
                    ],
                    [
                            category_id: 5,
                            name: "机器学习",
                            description: "这是机器学习"
                    ],
                    [
                            category_id: 6,
                            name: "大数据",
                            description: "这是大数据"
                    ],
                    [
                            category_id: 7,
                            name: "深度学习",
                            description: "这是深度学习"
                    ]
            ]
        }
    }
}
