package com.geeklog.service.user.impl;

import java.util.Date;
import java.util.List;

import com.geeklog.common.exception.RoleException;
import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Converter;
import com.geeklog.common.util.PageUtil;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Comment;
import com.geeklog.dto.CommentPublish;
import com.geeklog.dto.Page;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.CommentMapper;
import com.geeklog.service.user.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/17
 * 功能：用户模块的评论相关服务实现
 */
@Service("user.CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：列出最新的 count 条评论
     */
    public List<Comment> listLatestComment(int count) {
        Validator.min(count, 1, ValidatorException.LATEST_COMMENT_COUNT_OUT_OF_RANGE);

        return commentMapper.queryPagingDESC(new Comment(), 0, count);
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/17
     * 功能：发布评论
     */
    @Transactional
    public Comment publishComment(CommentPublish commentPublish) {
        Validator.notNull(commentPublish, ValidatorException.NO_COMMENT_PUBLISH_INFO);
        Validator.notNull(commentPublish.getUserId(), ValidatorException.NO_COMMENT_PUBLISH_INFO);
        Validator.notBlank(commentPublish.getContent(), ValidatorException.COMMENT_CONTENT_BLANK);
        Validator.isCurrentUser(commentPublish.getUserId(), RoleException.OTHER_USER_COMMENT);

        Comment comment = Converter.convert(commentPublish, Comment.class);
        comment.setCreatedAt(new Date());
        Integer parentId = comment.getParentId();
        boolean isRoot = parentId == null;
        if (!isRoot) {
            Comment parentComment = commentMapper.selectByPrimaryKey(parentId);
            Validator.notNull(parentComment, ValidatorException.PARENT_COMMENT_NOT_EXIST);

            comment.setRootId(parentComment.getRootId());
            comment.setArticleId(parentComment.getArticleId());
        } else {
            Integer articleId = comment.getArticleId();
            Validator.notNull(articleId, ValidatorException.NO_COMMENT_PUBLISH_INFO);
            Article article = articleMapper.selectByPrimaryKey(articleId);
            Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);
        }

        int effectRow = commentMapper.insert(comment);
        Validator.equals(effectRow, 1, ValidatorException.unexpected("CommentServiceImpl.publishComment(..) 保存评论至数据库异常，未知错误"));

        if (isRoot) {
            Comment commentForUpdateRootId = new Comment();
            commentForUpdateRootId.setCommentId(comment.getCommentId());
            commentForUpdateRootId.setRootId(comment.getCommentId());

            effectRow = commentMapper.updateByPrimaryKey(commentForUpdateRootId);
            Validator.equals(effectRow, 1, ValidatorException.unexpected(
                    "CommentServiceImpl.publishComment(..) 更新一级评论的 root id 异常，未知错误"));

            comment.setRootId(comment.getCommentId());
        }

        return comment;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/18
     * 功能：列出文章的所有一级评论
     */
    @Transactional
    public Page<Comment> listCommentsOfArticle(int articleId, int page, int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        Article article = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);

        Comment commentQueryByArticleId = new Comment();
        commentQueryByArticleId.setArticleId(articleId);
        int total = commentMapper.queryNumOfRoot(commentQueryByArticleId);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);

        List<Comment> rootComments = commentMapper.queryPagingRoot(commentQueryByArticleId, (page - 1) * size, size);

        return new Page<>(total, rootComments.toArray(new Comment[rootComments.size()]));
    }
}
