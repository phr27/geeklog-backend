package com.geeklog.controller.admin;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Comment;
import com.geeklog.dto.Page;
import com.geeklog.service.admin.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的评论管理控制器
 */
@GeekLogController("/admin")
@RequireRole(Role.ADMIN)
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：分页列出文章的所有评论，若 rootId 为 null，则一级评论，否则二级评论
     */
    @GetMapping("/comments")
    public ResponseEntity<Page<Comment>> listComment(@RequestParam(value = "article_id", required = false) Integer articleId,
                                                     @RequestParam int page,
                                                     @RequestParam int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        return ResponseEntity.ok("success", commentService.listComment(articleId, page, size));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：根据评论 id 删除评论
     */
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("comment_id") int commentId) {
        return ResponseEntity.ok("success", commentService.deleteComment(commentId));
    }
}
