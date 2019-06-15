package com.question.app.controller;

import com.question.app.model.Category;
import com.question.app.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Category getAllCategories() {
        return null;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{name}")
    public String getCategoryTree(@PathVariable(value = "name") String name) {
        return categoryService.getTreeByName(name);
    }


}
