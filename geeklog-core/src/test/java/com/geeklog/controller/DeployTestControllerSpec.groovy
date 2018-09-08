package com.geeklog.controller

import org.springframework.http.HttpMethod

/**
 * spock 测试样板，正式环境删除
 */
class DeployTestControllerSpec extends ControllerSpecification {

    def "DeployTestController /deploy/test"() {

        when:
        def entity = restTemplate.exchange("$URL_PREFFIX/deploy/test", HttpMethod.GET, null, List)

        then:
        entity.statusCode.is2xxSuccessful()
        entity.body == ["Java开发技巧", "Vue总结", "JavaScript技巧", "Docker使用经验", "Python开发技巧"]

    }
}
