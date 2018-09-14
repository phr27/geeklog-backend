package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.dto.UserRegistry;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/14
 * 功能：用户模块的用户管理控制器
 */
@GeekLogController(path = "/users", value = "user.UserController")
public class UserController {

    @Autowired
    @Qualifier("user.UserService")
    private UserService userService;

    @GetMapping("/{user_id}")
    @RequireRole(Role.USER)
    public ResponseEntity<UserWithPermissionBio> findUserById(@PathVariable("user_id") int userId) {
        return ResponseEntity.ok("success", userService.findUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserWithPermissionBio> register(@RequestBody UserRegistry userRegistry) {
        Validator.notNull(userRegistry, ValidatorException.NO_REGISTER_INFO);
        Validator.username(userRegistry.getUsername());
        Validator.password(userRegistry.getPassword());

        return ResponseEntity.ok("注册成功", userService.register(userRegistry));
    }
}
