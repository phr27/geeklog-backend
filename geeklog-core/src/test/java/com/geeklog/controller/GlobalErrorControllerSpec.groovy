package com.geeklog.controller

import com.geeklog.common.util.ResponseEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

/**
 * @author 潘浩然
 * 创建时间 2018/09/07
 * 功能：GlobalErrorController 单元测试
 */
class GlobalErrorControllerSpec extends ControllerSpecification {

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：测试 404 异常是否正确处理
     */
    def "GlobalErrorController GET /a/b(404)"() {
        when:
        def entity = restTemplate.exchange("$URL_PREFFIX/a/b", HttpMethod.GET, null, ResponseEntity)
        then:
        entity.statusCode == HttpStatus.NOT_FOUND
        with(entity.body) {
            code == 404
            message == "Not Found"
            data == null
        }
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：测试 405 异常是否正确处理
     */
    def "DeployTestController POST /deploy/test(405)"() {
        when:
        def entity = restTemplate.exchange("$URL_PREFFIX/deploy/test", HttpMethod.POST, null, ResponseEntity)
        then:
        entity.statusCode == HttpStatus.METHOD_NOT_ALLOWED
        with(entity.body) {
            code == 405
            message == "Method Not Allowed"
            data == null
        }
    }
}
