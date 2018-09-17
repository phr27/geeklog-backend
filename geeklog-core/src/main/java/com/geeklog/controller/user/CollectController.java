package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.StarCollectBaseException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Collect;
import com.geeklog.dto.StarCollectRequestBody;
import com.geeklog.service.user.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：收藏相关控制器
 */
@GeekLogController("user.CollectController")
@RequireRole(Role.USER)
public class CollectController {

    @Autowired
    @Qualifier("user.CollectService")
    private CollectService collectService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：收藏
     */
    @PostMapping("/add-collect")
    public ResponseEntity<Collect> collect(@RequestBody StarCollectRequestBody starCollectRequestBody) {
        Validator.validStarCollectRequestBody(starCollectRequestBody, StarCollectBaseException.COLLECT);

        return ResponseEntity.ok("success", collectService.collect(starCollectRequestBody));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：取消收藏
     */
    @PostMapping("/delete-collect")
    public ResponseEntity<Collect> uncollect(@RequestBody StarCollectRequestBody starCollectRequestBody) {
        Validator.validStarCollectRequestBody(starCollectRequestBody, StarCollectBaseException.COLLECT);

        return ResponseEntity.ok("success", collectService.uncollect(starCollectRequestBody));
    }
}
