package com.geeklog.controller.admin;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.dto.ArticleDisplaySetter;
import com.geeklog.dto.Page;
import com.geeklog.service.admin.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的文章管理控制器
 * 修改时间 2018/09/13
 * 修改人 潘浩然
 */
@GeekLogController(path = "/admin", value = "ArticleController")
@RequireRole(Role.ADMIN)
public class ArticleController {

    @Autowired
    @Qualifier("admin.ArticleService")
    private ArticleService articleService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：categoryId 为 null，分页列出所有文章；categoryId 不为 null，分页列出某一分类的所有文章
     */
    @GetMapping("/articles")
    public ResponseEntity<Page<Article>> listArticle(@RequestParam(required = false, name = "category_id") Integer categoryId,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        return ResponseEntity.ok("success", articleService.listArticle(categoryId, page, size));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/13
     * 功能：根据文章 id 删除文章
     */
    @DeleteMapping("/articles/{article_id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("article_id") int articleId) {
        return ResponseEntity.ok("success", articleService.deleteArticle(articleId));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/13
     * 功能：设置文章是否可见
     */
    @PutMapping("/articles/{article_id}")
    public ResponseEntity<Article> updateArticleDisplay(@PathVariable("article_id") int articleId,
                                                        @RequestBody ArticleDisplaySetter articleDisplaySetter) {
        return ResponseEntity.ok("success", articleService.updateArticleDisplay(articleId, articleDisplaySetter.isDisplay()));
    }
}
