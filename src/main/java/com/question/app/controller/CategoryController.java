package com.question.app.controller;

import com.question.app.model.Category;
import com.question.app.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Simple controller for managing categories
 * @author Jakub Setla
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    private CategoryService categoryService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /**
     * Creating new Category
     * @param category
     * @return newly created category with assigned id and default values
     */
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        log.info("Creating category {}", category);
        return categoryService.saveCategory(category);
    }

    /**
     * Returns String representation of tree with questions.
     * @param name is root of printed tree. To see full tree use 'root'
     * @return String representation of tree
     */
    @GetMapping("tree/{name}")
    public String getCategoryTree(@PathVariable(value = "name") String name) {
        log.info("Getting string representation of category tree with root: {}", name);
        return categoryService.getStringTreeByName(name);
    }


    /**
     * Returns json representation of tree with questions.
     * @param name is root of tree. To see full tree use 'root'
     * @return Category with questions and subcategories
     */
    @GetMapping("/{name}")
    public Category getCategory(@PathVariable(value = "name") String name) {
        log.info("Getting json category tree with root: {}", name);
        return categoryService.getTreeByName(name);
    }


}
