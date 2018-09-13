package com.geeklog.controller.admin;


import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Forbidden;
import com.geeklog.domain.User;
import com.geeklog.dto.BeForbidden;
import com.geeklog.mapper.UserMapper;
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


    @Autowired
    private UserMapper userMapper;


    @PostMapping("/forbiddens")
    public ResponseEntity forbidden(@RequestBody BeForbidden beForbidden) {

        Validator.isLegal(beForbidden.getAuthorityId(), ValidatorException.AUTHORITY_OUT_OF_RANGE);

        User user = userMapper.selectByPrimaryKey(beForbidden.getUserId());
        if (user == null){
            return ResponseEntity.badRequest("非法用户");
        }
        if(user.getIsAdmin()){
            return ResponseEntity.forbidden("非法操作，禁止修改管理员权限");
        }
        return ResponseEntity.ok("权限设置成功", forbiddenService.forbid(beForbidden.getUserId(), beForbidden.getAuthorityId()));

    }
}
