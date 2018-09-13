package com.geeklog.controller.admin;


import com.geeklog.common.annotation.GeekLogController;
import com.geeklog.common.annotation.RequireRole;
import com.geeklog.common.enumeration.Role;
import com.geeklog.common.util.ResponseEntity;
import com.geeklog.domain.Category;
import com.geeklog.dto.Acategory;
import com.geeklog.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author 午康俊
 * 创建时间 2018/9/13
 * 功能 管理员管理分类控制器
 */


@GeekLogController(path = "/admin", value = "admin.CategoryController")
@RequireRole(Role.ADMIN)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categoryList(){
        return ResponseEntity.ok("success", categoryService.listCategory());
    }

    @PostMapping("/categories")
    public ResponseEntity addCategory(@RequestBody Acategory acategory){

        Category category = categoryService.addCategory(acategory.getName(), acategory.getDescription());

        if (category == null){
            return ResponseEntity.badRequest("分类已存在，禁止重复添加");
        } else {
            return ResponseEntity.ok("添加分类成功",category);
        }


    }

    @PutMapping("/categories/{category_id}")
    public ResponseEntity<Acategory> updateCategory(@RequestBody Acategory acategory, @PathVariable("category_id") int categoryId){

        return ResponseEntity.ok("更新分类成功", categoryService.updateCategory(categoryId, acategory.getName(), acategory.getDescription()));

    }

    @DeleteMapping("/categories/{category_id}")
    public ResponseEntity<Acategory> deleteCategory(@PathVariable("category_id") int categoryId){

        return ResponseEntity.ok("删除分类成功", categoryService.deleteCategory(categoryId));
    }



}
