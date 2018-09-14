package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.dto.UserInfoUpdate;
import com.geeklog.dto.UserRegistry;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：根据 userId 查找用户
     */
    @GetMapping("/{user_id}")
    @RequireRole(Role.USER)
    public ResponseEntity<UserWithPermissionBio> findUserById(@PathVariable("user_id") int userId) {
        return ResponseEntity.ok("success", userService.findUserById(userId));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：用户注册接口
     */
    @PostMapping
    public ResponseEntity<UserWithPermissionBio> register(@RequestBody UserRegistry userRegistry) {
        Validator.notNull(userRegistry, ValidatorException.NO_REGISTER_INFO);
        Validator.username(userRegistry.getUsername());
        Validator.password(userRegistry.getPassword());

        return ResponseEntity.ok("注册成功", userService.register(userRegistry));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/14
     * 功能：更新自己的个人信息
     */
    @PutMapping("/{user_id}")
    @RequireRole(Role.USER)
    public ResponseEntity<UserWithPermissionBio> updateUserInfo(@PathVariable("user_id") int userId,
                                                                @RequestBody UserInfoUpdate userInfoUpdate) {
        Validator.isCurrentUser(userId, RoleException.UPDATE_OTHER_USER);
        Validator.notNull(userInfoUpdate, ValidatorException.NO_USER_UPDATE_INFO);

        return ResponseEntity.ok("更新成功", userService.updateUserInfo(userId, userInfoUpdate));
    }
}
