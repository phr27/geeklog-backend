package com.geeklog.controller.admin;


import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
@RequireRole(Role.ADMIN)

public class ForbiddenController {

    @Autowired
    private ForbiddenService forbiddenService;

    @PostMapping("/forbiddens")
    public ResponseEntity<Forbidden> forbidden(@RequestBody Integer user_id, @RequestBody Integer authority_id) {
        Validator.isLegal(authority_id, ValidatorException.AUTHORITY_OUT_OF_RANGE);

        return ResponseEntity.ok("权限设置成功", forbiddenService);

    }
}
