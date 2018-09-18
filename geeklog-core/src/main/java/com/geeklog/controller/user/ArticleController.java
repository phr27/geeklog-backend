package com.geeklog.controller.user;


import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequirePermission;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Permission;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.domain.Article;
import com.geeklog.dto.ArticleDto;
import com.geeklog.dto.ArticleInsert;
import com.geeklog.dto.ArticleUpdate;
import com.geeklog.dto.Page;
import com.geeklog.service.user.ArticleService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/17
 * 功能 文章控制器
 */

@GeekLogController(value = "user.ArticleController")

public class ArticleController {

    @Autowired
    @Qualifier("user.ArticleService")
    private ArticleService articleService;

    @GetMapping("/articles/hot/{count}")
    public ResponseEntity<List<ArticleDto>> hotArticles(@PathVariable("count") int count) {

        return ResponseEntity.ok("success", articleService.hotArticles(count));
    }
    @GetMapping("/articles")
    public ResponseEntity<Page<ArticleDto>> categoryArticles(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("category_id") int categoryId){

        return ResponseEntity.ok("success", articleService.categoryArticles(page, size, categoryId));
    }

    @GetMapping("/articles/{article_id}")
    public ResponseEntity<ArticleDto> article(@PathVariable("article_id") int articleId) {

        return ResponseEntity.ok("success", articleService.article(articleId));
    }

    @PostMapping("/articles")
    @RequireRole(Role.USER)
    @RequirePermission(Permission.CAN_WRITE_ARTICLE)
    public ResponseEntity<Article> insertArticle(@RequestBody ArticleInsert articleInsert) {
        return ResponseEntity.ok("success", articleService.insertArticle(articleInsert.getTitle(), articleInsert.getContent(), articleInsert.getUserId(), articleInsert.getCategoryId(), articleInsert.getTags()));
    }

    @PutMapping("/articles/{article_id}")
    @RequireRole(Role.USER)
    public ResponseEntity<Article> updateArticle(@PathVariable("article_id") int articleId, @RequestBody ArticleUpdate articleUpdate){
        return ResponseEntity.ok("success", articleService.updateArticle(articleId, articleUpdate.getTitle(), articleUpdate.getContent(), articleUpdate.getCategoryId(), articleUpdate.getTags()));
    }

    @DeleteMapping("/articles/{article_id}")
    @RequireRole(Role.USER)
    public ResponseEntity<Article> deleteArticle(@PathVariable("article_id") int articleId){
        return ResponseEntity.ok("success", articleService.deleteArticle(articleId));
    }

}
