package org.example.sem3_rolo.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.CategoryEntity;
import org.example.sem3_rolo.pojo.CategoryPojo;
import org.example.sem3_rolo.repository.CategoryRepository;
import org.example.sem3_rolo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class CategoryServiceImpl implements CategoryService {
        private final CategoryRepository categoryRepository;

        @Override
        public void saveCategory(CategoryPojo categoryPojo) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(categoryPojo.getId());
            categoryEntity.setCategoryName(categoryPojo.getCategoryName());
            categoryRepository.save(categoryEntity);
        }

        @Override
        public void updateCategory(CategoryPojo categoryPojo) {
            CategoryEntity categoryEntity = categoryRepository.findById(categoryPojo.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            categoryEntity.setCategoryName(categoryPojo.getCategoryName());
            categoryRepository.save(categoryEntity);
        }

        @Override
        public List<CategoryEntity> getAllCategories() {
            return categoryRepository.findAll();
        }

        @Override
        public Optional<CategoryEntity> getCategoryById(Integer id) {
            return categoryRepository.findById(id);
        }

        @Override
        public void deleteCategory(Integer id) {
            categoryRepository.deleteById(id);
        }
    }
