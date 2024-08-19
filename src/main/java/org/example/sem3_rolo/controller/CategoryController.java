package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.CategoryEntity;
import org.example.sem3_rolo.pojo.CategoryPojo;
import org.example.sem3_rolo.service.CategoryService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public void category(@RequestBody CategoryPojo addCategory) {
        categoryService.saveCategory(addCategory);
    }

    @PutMapping("/categories/{id}")
    public void updateCategory(@PathVariable("id") Integer id,@RequestBody CategoryPojo categoryPojo) {
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setId(id);
        categoryEntity.setCategoryName(categoryPojo.getCategoryName());
        categoryService.updateCategory(categoryPojo);
    }

    @GetMapping("/categories")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Optional<CategoryEntity> getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}
