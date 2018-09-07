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

    /**
     * @author 潘浩然
     * 创建时间 2018/9/6
     * 功能：spring boot 应用的子进程
     */
    @Shared
    private Process process

    /**
     * @author 潘浩然
     * 创建时间 2018/9/7
     * 功能：在子进程中启动用于测试的 spring boot 应用，并监听子进程的标准输出和错误输出，以确定是否正常启动
     */
    def setupSpec() {
        Thread thread = Thread.currentThread()
        "fuser -k -n tcp 8080".execute().waitFor()
        process = "mvn clean spring-boot:run".execute()

        new Thread({
            try {
                process.inputStream.withReader {
                    it.eachLine {
                        println it
                        if (it.contains("Started Application in")) {
                            thread.interrupt()
                        }
                    }
                }
            } catch (IOException e) {
                if (e.message != "Stream closed") {
                    throw e
                }
            }
        }).start()

        new Thread({
            try {
                process.errorStream.withReader {
                    it.eachLine {
                        println it
                    }
                }
            } catch (IOException e) {
                if (e.message != "Stream closed") {
                    throw e
                }
            }
        }).start()

        try {
            process.waitFor()
        } catch (InterruptedException e) {
        }
    }

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
