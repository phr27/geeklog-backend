package com.geeklog.controller.admin;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Validator;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.dto.AuthToken;
import com.geeklog.dto.JwtToken;
import com.geeklog.service.common.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author 潘浩然
 * 创建时间 2018/09/09
 * 功能：管理员登录控制器
 */
@GeekLogController(path = "/admin", value = "admin.LoginController")
public class LoginController {

    @Autowired
    @Qualifier("common.LoginService")
    private LoginService loginService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/09
     * 功能：管理员登录 api
     */
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody AuthToken authToken) {
        Validator.notNull(authToken, ValidatorException.NO_AUTH_TOKEN);
        Validator.username(authToken.getUsername());
        Validator.password(authToken.getPassword());

        return ResponseEntity.ok("登录成功", loginService.login(authToken.getUsername(), authToken.getPassword(), Role.ADMIN));
    }
}
