package com.geeklog.service.admin.impl;

import java.util.List;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.PageUtil;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Comment;
import com.geeklog.dto.Page;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.CommentMapper;
import com.geeklog.service.admin.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的评论管理服务实现
 */
@Service("admin.CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：分页列出文章的所有评论，若 rootId 为 null，则一级评论，否则二级评论
     */
    @Transactional
    public Page<Comment> listComment(Integer articleId, int page, int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        if (articleId != null) {
            Article targetArticle = articleMapper.selectByPrimaryKey(articleId);
            Validator.notNull(targetArticle, ValidatorException.ARTICLE_NOT_EXIST);
        }

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        int total = commentMapper.queryNumOfComments(comment);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);
        List<Comment> comments = commentMapper.queryPaging(comment, (page - 1) * size, size);

        return new Page<>(total, comments.toArray(new Comment[comments.size()]));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：根据评论 id 删除评论
     */
    @Transactional
    public Comment deleteComment(int commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        Validator.notNull(comment, ValidatorException.COMMENT_NOT_EXIST);

        int count = commentMapper.deleteByPrimaryKey(commentId);
        Validator.equals(count, 1, ValidatorException.unexpected("CommentServiceImpl.deleteComment(..) 删除异常，未知错误"));

        return comment;
    }
}
