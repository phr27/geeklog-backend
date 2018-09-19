package com.geeklog.service.user;

import java.util.List;

import com.geeklog.domain.Category;

/**
 * @author 潘浩然
 * 创建时间 2018/09/18
 * 功能：用户的分类服务接口
 */
public interface CategoryService {

    List<Category> listCategory();
}
