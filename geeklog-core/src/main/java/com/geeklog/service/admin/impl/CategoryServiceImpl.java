package com.geeklog.service.admin.impl;


import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Article;
import com.geeklog.domain.Category;
import com.geeklog.dto.Acategory;
import com.geeklog.mapper.ArticleMapper;
import com.geeklog.mapper.CategoryMapper;
import com.geeklog.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/13
 * 功能 分类相关实现
 */

@Service("admin.CategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    public List<Category> listCategory() {
        return categoryMapper.selectAll();

    }


    @Transactional
    public Category addCategory(String name, String description) {
        Category category = categoryMapper.queryByCategoryName(name);
        if (category != null){
            return null;
        }
        Category rcategory = new Category();
        rcategory.setName(name);
        rcategory.setDescription(description);
        categoryMapper.insert(rcategory);
        return rcategory;
    }


    @Transactional
    public Acategory updateCategory(int categoryId, String name, String description) {

        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Validator.notNull(category, ValidatorException.CATEGORY_NOT_EXIST);
        category.setName(name);
        category.setDescription(description);
        categoryMapper.updateByPrimaryKey(category);
        Acategory acategory = new Acategory();
        acategory.setName(category.getName());
        acategory.setDescription(category.getDescription());
        return acategory;


    }


    @Transactional
    public Acategory deleteCategory(int categoryId) {
        Article article = new Article();
        article.setCategoryId(categoryId);

        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Validator.notNull(category, ValidatorException.CATEGORY_NOT_EXIST);

        List<Article> articleList = articleMapper.queryPaging(article, 0, 5);
        Validator.isTrue(articleList.isEmpty(), ValidatorException.STILL_HAVE_ARTICLE);

        categoryMapper.deleteByPrimaryKey(categoryId);
        Acategory acategory = new Acategory();
        acategory.setName(category.getName());
        acategory.setDescription(category.getDescription());
        return acategory;
    }
}
