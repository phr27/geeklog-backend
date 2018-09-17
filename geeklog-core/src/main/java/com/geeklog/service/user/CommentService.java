package com.geeklog.service.user;

import java.util.List;

import com.geeklog.domain.Comment;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：用户模块的评论相关服务接口
 */
public interface CommentService {

    List<Comment> listLatestComment(int count);
}
