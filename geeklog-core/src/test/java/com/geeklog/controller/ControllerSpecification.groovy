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

    /**
     * @author 潘浩然
     * 创建时间 2018/9/6
     * 功能：启动 spring boot 应用的子进程
     */
    @Shared
    private Process process = "mvn spring-boot:run".execute()

    protected static final URL_PREFFIX = "http://localhost:8080"

    /**
     * @author 潘浩然
     * 创建时间 2018/9/6
     * 功能：销毁 spring boot 应用进程
     */
    def cleanupSpec() {
        process.destroy()
    }
}
