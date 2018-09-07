package com.geeklog.controller

import com.geeklog.Application
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author 潘浩然
 * 创建时间 2018/9/6
 * 功能：控制器单元测试的基类
 * 修改人：潘浩然
 * 修改时间：2018/9/7
 */
@SpringBootTest(classes = Application, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
abstract class ControllerSpecification extends Specification {

    /**
     * @author 潘浩然
     * 创建时间 2018/9/6
     * 功能：作为 restful http client 向控制器对应的 url 发起请求
     */
    @Shared
    protected TestRestTemplate restTemplate = new TestRestTemplate()

    protected static final URL_PREFFIX = "http://localhost:8080"

}
