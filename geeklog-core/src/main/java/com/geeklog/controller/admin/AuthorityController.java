package com.geeklog.controller.admin;

import java.util.List;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.domain.Authority;
import com.geeklog.service.admin.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员权限管理控制器
 */
@GeekLogController(path = "/admin", value = "AuthorityController")
@RequireRole(Role.ADMIN)
public class AuthorityController {

    @Autowired
    @Qualifier("admin.AuthorityService")
    private AuthorityService authorityService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：列出数据库中的所有权限
     */
    @GetMapping("/authorities")
    public ResponseEntity<List<Authority>> listAuthority() {
        return ResponseEntity.ok("success", authorityService.listAuthority());
    }
}
