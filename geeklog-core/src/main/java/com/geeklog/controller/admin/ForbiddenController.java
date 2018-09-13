package com.geeklog.controller.admin;


import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.dto.BeForbidden;
import com.geeklog.service.admin.ForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 午康俊
 * 创建时间 2018/9/12
 * 功能 管理员管理api
 */
@GeekLogController("/admin")
@RequireRole(Role.ADMIN)
public class ForbiddenController {

    @Autowired
    private ForbiddenService forbiddenService;

    @PostMapping("/forbiddens")
    public ResponseEntity forbidden(@RequestBody BeForbidden beForbidden) {

        forbiddenService.isLegalUser(beForbidden.getUserId(), beForbidden.getAuthorityId());

        return ResponseEntity.ok("权限设置成功", forbiddenService.forbid(beForbidden.getUserId(), beForbidden.getAuthorityId()));

    }

    @DeleteMapping("/forbiddens/{user_id}/{authority_id}")
    public ResponseEntity deleteForbidden(@PathVariable("user_id") int userId, @PathVariable("authority_id") int authorityId) {

        forbiddenService.isLegalUser(userId, authorityId);

        return ResponseEntity.ok("权限删除成功", forbiddenService.deleteForbidden(userId, authorityId));
    }


}
