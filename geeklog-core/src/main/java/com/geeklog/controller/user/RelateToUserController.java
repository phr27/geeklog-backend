package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.dto.ArticleDisplaySetter;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;
import com.geeklog.service.user.RelateToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 午康俊
 * 创建时间 2018/9/18
 * 功能 用户id关联查询控制器
 */

@GeekLogController(value = "user.RelateToUserController")
@RequireRole(Role.USER)
public class RelateToUserController {

    @Autowired
    @Qualifier("user.RelateToUserService")
    private RelateToUserService relateToUserService;

    @GetMapping("/users/{user_id}/write/articles")
    public ResponseEntity<Page<ArticleDto>> writeArticles(@PathVariable("user_id") int userId){
        return ResponseEntity.ok("success", relateToUserService.getWroteArticles(userId));
    }

    @GetMapping("/users/{user_id}/star/articles")
    public ResponseEntity<Page<ArticleDto>> starArticles(@PathVariable("user_id") int userId){
        return ResponseEntity.ok("success", relateToUserService.getStarredArticles(userId));
    }


}
