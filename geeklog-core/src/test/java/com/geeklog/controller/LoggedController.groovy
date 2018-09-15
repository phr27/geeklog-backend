package com.geeklog.controller

import com.geeklog.common.aspect.RoleAspect
import com.geeklog.common.util.ResponseEntity
import com.geeklog.dto.AuthToken
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：测试基类，封装登录过程
 */
abstract class LoggedController extends ControllerSpecification {

    void getAuthorization() {
        AuthToken authToken = new AuthToken()
        authToken.username = "a123456"
        authToken.password = "1234567"

        def entity = restTemplate.exchange("$URL_PREFFIX/admin/login",
                HttpMethod.POST,
                new HttpEntity<>(authToken, headers),
                ResponseEntity
        )

        def authorization = RoleAspect.AUTH_PREFIX + entity.body.data.token
        headers.set("Authorization", authorization)
    }
}
