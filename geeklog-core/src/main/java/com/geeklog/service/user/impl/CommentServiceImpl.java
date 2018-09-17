package com.geeklog.service.user.impl;

import java.util.List;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Comment;
import com.geeklog.mapper.CommentMapper;
import com.geeklog.service.user.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：用户模块的评论相关服务实现
 */
@Service("user.CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：列出最新的 count 条评论
     */
    public List<Comment> listLatestComment(int count) {
        Validator.min(count, 1, ValidatorException.LATEST_COMMENT_COUNT_OUT_OF_RANGE);

        return commentMapper.queryPagingDESC(new Comment(), 0, count);
    }
}
