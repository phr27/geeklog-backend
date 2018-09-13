package com.geeklog.service.admin.impl;

import com.geeklog.common.exception.ValidatorException;
import com.geeklog.common.util.Validator;
import com.geeklog.domain.Category;
import com.geeklog.dto.Acategory;
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

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> listCategory() {
        return categoryMapper.selectAll();

    }

//    public Category addCategory(String name, String description) {
//
//
//    }

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
}
