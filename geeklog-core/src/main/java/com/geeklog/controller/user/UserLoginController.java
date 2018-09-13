package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.dto.AuthToken;
import com.geeklog.dto.JwtToken;
import com.geeklog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/13
 * 功能：用户登录控制器
 */
@GeekLogController
public class UserLoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody AuthToken authToken) {
        Validator.notNull(authToken, ValidatorException.NO_AUTH_TOKEN);
        Validator.username(authToken.getUsername());
        Validator.password(authToken.getPassword());

        return ResponseEntity.ok("登录成功", loginService.login(authToken.getUsername(), authToken.getPassword(), null));
    }
}
