package com.geeklog.service.admin;

import com.geeklog.domain.Category;

import java.util.List;

/**
 * @author 午康俊
 * 创建时间 2018/9/13
 * 功能 分类接口
 */
public interface CategoryService {

    List<Category> listCategory();
//    Category addCategory(String name, String description);
}
