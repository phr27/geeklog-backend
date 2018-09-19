package com.geeklog.controller.user;

import java.util.List;

import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.domain.Category;
import com.geeklog.service.user.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 潘浩然
 * 创建时间 2018/09/18
 * 功能：用户的分类控制器
 */
@GeekLogController("user.CategoryController")
public class CategoryController {

    @Autowired
    @Qualifier("user.CategoryService")
    private CategoryService categoryService;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/18
     * 功能：列出所有的分类
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> listCategory() {
        return ResponseEntity.ok("success", categoryService.listCategory());
    }
}
