package com.geeklog.controller.admin;


import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.dto.BeForbidden;
import com.geeklog.service.admin.ForbiddenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@GeekLogController("/admin")
@RequireRole(Role.ADMIN)
public class ForbiddenController {

    @Autowired
    private ForbiddenService forbiddenService;

    @PostMapping("/forbiddens")
    public ResponseEntity<Forbidden> forbidden(@RequestBody BeForbidden beForbidden) {

        Validator.isLegal(beForbidden.getAuthorityId(), ValidatorException.AUTHORITY_OUT_OF_RANGE);

        return ResponseEntity.ok("权限设置成功", forbiddenService.forbid(beForbidden.getUserId(), beForbidden.getAuthorityId()));

    }
}
