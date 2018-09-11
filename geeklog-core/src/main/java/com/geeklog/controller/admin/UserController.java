package com.geeklog.controller.admin;

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

@RestController
@CrossOrigin
@RequestMapping("/admin")
@RequireRole(Role.ADMIN)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<UserWithPermission>> userList(@RequestParam int page, @RequestParam int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        return ResponseEntity.ok("success", userService.listUser(page, size));
    }
}
