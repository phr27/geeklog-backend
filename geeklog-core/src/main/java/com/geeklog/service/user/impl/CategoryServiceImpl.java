package com.geeklog.service.user.impl;

import java.util.List;

import com.geeklog.domain.Category;
import com.geeklog.mapper.CategoryMapper;
import com.geeklog.service.user.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 潘浩然
 * 创建时间 2018/09/18
 * 功能：用户的分类服务实现
 */
@Service("user.CategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/18
     * 功能：列出所有的分类
     */
    public List<Category> listCategory() {
        return categoryMapper.selectAll();
    }
}
