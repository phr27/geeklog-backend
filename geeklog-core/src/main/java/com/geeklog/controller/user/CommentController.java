package com.geeklog.controller.user;

import java.util.List;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequirePermission;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Permission;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Comment;
import com.geeklog.dto.CommentDto;
import com.geeklog.dto.CommentPublish;
import com.geeklog.service.user.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：用户模块的评论相关控制器
 */
@GeekLogController(path = "/comments", value = "user.CommentController")
public class CommentController {

    @Autowired
    @Qualifier("user.CommentService")
    private CommentService commentService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：列出最新的 count 条评论
     */
    @GetMapping("/latest/{count}")
    public ResponseEntity<List<CommentDto>> listLatestComment(@PathVariable int count) {
        Validator.min(count, 1, ValidatorException.LATEST_COMMENT_COUNT_OUT_OF_RANGE);

        return ResponseEntity.ok("success", commentService.listLatestComment(count));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：发布评论
     */
    @PostMapping
    @RequireRole(Role.USER)
    @RequirePermission(Permission.CAN_COMMENT)
    public ResponseEntity<Comment> publishComment(@RequestBody CommentPublish commentPublish) {
        Validator.notNull(commentPublish, ValidatorException.NO_COMMENT_PUBLISH_INFO);
        Validator.notNull(commentPublish.getUserId(), ValidatorException.NO_COMMENT_PUBLISH_INFO);
        Validator.notBlank(commentPublish.getContent(), ValidatorException.COMMENT_CONTENT_BLANK);
        Validator.isCurrentUser(commentPublish.getUserId(), RoleException.OTHER_USER_COMMENT);

        return ResponseEntity.ok("success", commentService.publishComment(commentPublish));
    }
}
