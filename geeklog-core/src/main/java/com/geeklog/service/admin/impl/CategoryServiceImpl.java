package com.geeklog.service.admin.impl;

import com.geeklog.domain.Category;
import com.geeklog.mapper.CategoryMapper;
import com.geeklog.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Category addCategory(String name, String description) {


    }
}
