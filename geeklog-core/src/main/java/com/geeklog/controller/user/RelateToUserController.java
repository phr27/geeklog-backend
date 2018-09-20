package com.geeklog.controller.user;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.domain.Comment;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.Page;
import com.geeklog.service.user.RelateToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 午康俊
 * 创建时间 2018/9/18
 * 功能 用户id关联查询控制器
 */

@GeekLogController(value = "user.RelateToUserController")
public class RelateToUserController {

    @Autowired
    @Qualifier("user.RelateToUserService")
    private RelateToUserService relateToUserService;

    @GetMapping("/users/{user_id}/write/articles")

    public ResponseEntity<Page<ArticleDto>> writeArticles(@RequestParam int page, @RequestParam int size, @PathVariable("user_id") int userId){

        return ResponseEntity.ok("success", relateToUserService.getWroteArticles(page, size, userId));
    }

    @GetMapping("/users/{user_id}/star/articles")

    public ResponseEntity<Page<ArticleDto>> starArticles(@RequestParam int page, @RequestParam int size,@PathVariable("user_id") int userId){

        return ResponseEntity.ok("success", relateToUserService.getStarredArticles(page, size, userId));
    }

    @GetMapping("/users/{user_id}/collect/articles")

    public ResponseEntity<Page<ArticleDto>> collectArticles(@RequestParam int page, @RequestParam int size,@PathVariable("user_id") int userId){

        return ResponseEntity.ok("success", relateToUserService.getCollectedArticles(page, size, userId));
    }

    @GetMapping("/users/{user_id}/comments")

    public ResponseEntity<Page<Comment>> comments(@RequestParam int page, @RequestParam int size,@PathVariable("user_id") int userId){

        return ResponseEntity.ok("success", relateToUserService.getComments(page, size, userId));
    }


}
