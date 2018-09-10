package com.geeklog.common.util

import spock.lang.Specification

/**
 * @author 潘浩然
 * 创建时间 2018/09/10
 * 功能：MD5Util 工具类测试
 */
class MD5UtilSpec extends Specification {

    def "MD5Util test"() {
        when:
        String encoded1 = MD5Util.md5("1234567")
        String encoded2 = MD5Util.md5("1234")
        then:
        println encoded1.length()
        println encoded1
        println encoded2
    }
}
