package org.example.sem3_rolo.service;

import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.CategoryEntity;
import org.example.sem3_rolo.pojo.CategoryPojo;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void saveCategory(CategoryPojo categoryPojo) ;
    void updateCategory(CategoryPojo categoryPojo) ;
    List<CategoryEntity> getAllCategories();
    Optional<CategoryEntity> getCategoryById(Integer id);
    void deleteCategory(Integer id);
    List<BagEntity> getBagsByCategoryId(Integer categoryId);
}
