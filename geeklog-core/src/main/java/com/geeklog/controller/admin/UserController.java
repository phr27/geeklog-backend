package com.geeklog.controller.admin;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.dto.Page;
import com.geeklog.dto.UserWithPermission;
import com.geeklog.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 潘浩然
 * 创建时间 2018/09/11
 * 功能：管理员的用户管理控制器
 */
@GeekLogController("/admin")
@RequireRole(Role.ADMIN)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/11
     * 功能：分页列出普通用户及其权限
     */
    @GetMapping("/users")
    public ResponseEntity<Page<UserWithPermission>> userList(@RequestParam int page, @RequestParam int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        return ResponseEntity.ok("success", userService.listUser(page, size));
    }
}
