package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.dto.UserWithPermissionBio;
import com.geeklog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@GeekLogController(path = "/users", value = "user.UserController")
@RequireRole(Role.USER)
public class UserController {

    @Autowired
    @Qualifier("user.UserService")
    private UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserWithPermissionBio> findUserById(@PathVariable("user_id") int userId) {
        return ResponseEntity.ok("success", userService.findUserById(userId));
    }
}
