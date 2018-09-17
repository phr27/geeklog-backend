package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Star;
import com.geeklog.dto.StarCollectRequestBody;
import com.geeklog.service.user.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/15
 * 功能：点赞相关控制器
 */
@GeekLogController("user.StarController")
@RequireRole(Role.USER)
public class StarController {

    @Autowired
    @Qualifier("user.StarService")
    private StarService starService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：点赞
     */
    @PostMapping("/add-star")
    public ResponseEntity<Star> star(@RequestBody StarCollectRequestBody starCollectRequestBody) {
        Validator.validStarCollectRequestBody(starCollectRequestBody);

        return ResponseEntity.ok("success", starService.star(starCollectRequestBody));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/15
     * 功能：取消点赞
     */
    @PostMapping("/delete-star")
    public ResponseEntity<Star> unstar(@RequestBody StarCollectRequestBody starCollectRequestBody) {
        Validator.validStarCollectRequestBody(starCollectRequestBody);

        return ResponseEntity.ok("success", starService.unstar(starCollectRequestBody));
    }
}
