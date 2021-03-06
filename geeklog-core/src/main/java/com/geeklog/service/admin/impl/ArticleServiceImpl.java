package com.geeklog.service.admin.impl;

import java.util.List;
import java.util.Objects;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.PageUtil;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Category;
import com.geeklog.dto.Page;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.CategoryMapper;
import com.geeklog.service.admin.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 潘浩然
 * 创建时间 2018/09/12
 * 功能：管理员的文章管理服务实现
 * 修改时间 2018/09/13
 * 修改人 潘浩然
 */
@Service("admin.ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/12
     * 功能：categoryId 为 null，分页列出所有文章；categoryId 不为 null，分页列出某一分类的所有文章
     */
    @Transactional
    public Page<Article> listArticle(Integer categoryId, int page, int size) {
        Validator.min(page, 1, ValidatorException.PAGE_OUT_OF_RANGE);
        Validator.min(size, 1, ValidatorException.SIZE_OUT_OF_RANGE);

        if (categoryId != null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            Validator.notNull(category, ValidatorException.CATEGORY_NOT_EXIST);
        }

        Article articleWithCategoryId = new Article();
        articleWithCategoryId.setCategoryId(categoryId);

        int total = articleMapper.queryNumOfArticles(articleWithCategoryId);
        int totalPage = PageUtil.getTotalPage(total, size);
        Validator.max(page, totalPage, ValidatorException.PAGE_OUT_OF_RANGE);

        List<Article> articles = articleMapper.queryPaging(articleWithCategoryId, (page - 1) * size, size);

        return new Page<>(total, articles.toArray(new Article[articles.size()]));
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/13
     * 功能：根据文章 id 删除文章
     */
    @Transactional
    public Article deleteArticle(int articleId) {
        Article targetArticle = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(targetArticle, ValidatorException.ARTICLE_NOT_EXIST);

        int count = articleMapper.deleteByPrimaryKey(articleId);
        Validator.equals(count, 1, ValidatorException.unexpected("ArticleServiceImpl.deleteArticle(..) 删除异常，未知错误"));

        return targetArticle;
    }

    /**
     * @author 潘浩然
     * 创建时间 2018/09/13
     * 功能：更新文章是否可见
     */
    @Transactional
    public Article updateArticleDisplay(int articleId, boolean display) {
        Article targetArticle = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(targetArticle, ValidatorException.ARTICLE_NOT_EXIST);

        if (Objects.equals(display, targetArticle.getDisplay())) {
            return targetArticle;
        }

        Article newArticle = new Article();
        newArticle.setArticleId(targetArticle.getArticleId());
        newArticle.setDisplay(display);
        int effectRow = articleMapper.updateByPrimaryKey(newArticle);
        Validator.equals(effectRow, 1, ValidatorException.unexpected("ArticleServiceImpl.updateArticleDisplay(..) 更新异常，未知错误"));

        targetArticle.setDisplay(display);
        return targetArticle;
    }

    /**
     * @author 午康俊
     * 创建时间 2018/09/18
     * 功能：根据文章id获取文章
     */
    public Article article(int articleId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        Validator.notNull(article, ValidatorException.ARTICLE_NOT_EXIST);

        return article;
    }
}
