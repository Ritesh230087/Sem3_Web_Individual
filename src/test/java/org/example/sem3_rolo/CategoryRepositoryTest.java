package org.example.sem3_rolo;

import org.assertj.core.api.Assertions;
import org.example.sem3_rolo.entity.CategoryEntity;
import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.repository.CategoryRepository;
import org.example.sem3_rolo.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCategoryTest() {
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setCategoryName("Rolo Large Backpack");
        categoryEntity=categoryRepository.save(categoryEntity);
        Assertions.assertThat(categoryEntity.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public  void findByIdTest(){

        CategoryEntity categoryGet= categoryRepository.findById(1).get();
        Assertions.assertThat(categoryGet.getId()).isEqualTo(1);
    }


    @Test
    @Order(3)
    public void getListOfCategoryTest(){
        List<CategoryEntity> category = categoryRepository.findAll();
        Assertions.assertThat(category.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCategoryTest(){

        CategoryEntity category = categoryRepository.findById(1).get();

        category.setCategoryName("Rolo Laptop Bag");

        CategoryEntity categoryUpdated =  categoryRepository.save(category);

        Assertions.assertThat(categoryUpdated.getCategoryName()).isEqualTo("Rolo Laptop Bag");

    }


    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteCustomerTest(){
        CategoryEntity category = categoryRepository.findById(1).get();
        categoryRepository.delete(category);
        CategoryEntity category1 = null;
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(1);
        if(optionalCategoryEntity.isPresent()){
            category1 = optionalCategoryEntity.get();
        }
        Assertions.assertThat(category1).isNull();
    }
}
