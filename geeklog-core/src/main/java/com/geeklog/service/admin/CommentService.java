package com.geeklog.service.admin;

import com.geeklog.domain.Comment;
import com.geeklog.dto.Page;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的评论管理接口
 */
public interface CommentService {

    Page<Comment> listComment(Integer articleId, int page, int size);

    Comment deleteComment(int commentId);
}
