package org.example.sem3_rolo;

import org.assertj.core.api.Assertions;
import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.CategoryEntity;
import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.repository.BagRepository;
import org.example.sem3_rolo.repository.CategoryRepository;
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
public class BagRepositoryTest {
    @Autowired
    private BagRepository bagRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveBagTest() {
        CategoryEntity category = new CategoryEntity();
        category.setCategoryName("Rolo Large Backpack");
        category = categoryRepository.save(category);

        BagEntity bag=new BagEntity();
        bag.setBagName("Rolo Bagpack");
        bag.setBagDescription("Crafted in Nepal");
        bag.setPrice(4300.0);
        bag.setQuantity(20.0);
        bag.setCategory(category);
        bag.setBagImage("rolo.png");
        bag=bagRepository.save(bag);
        System.out.println(bag.getBagId());
        Assertions.assertThat(bag.getBagId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public void findByIdTest() {
        Optional<BagEntity> optionalBag = bagRepository.findById(2);
        Assertions.assertThat(optionalBag.isPresent()).isTrue();
        if (optionalBag.isPresent()) {
            BagEntity bagGet = optionalBag.get();
            Assertions.assertThat(bagGet.getBagId()).isEqualTo(2);
        }
    }



    @Test
    @Order(3)
    public void getListOfUserTest(){
        List<BagEntity> bags = bagRepository.findAll();
        Assertions.assertThat(bags.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateBagTest() {
        Optional<BagEntity> optionalBag = bagRepository.findById(2);
        Assertions.assertThat(optionalBag.isPresent()).isTrue();
        if (optionalBag.isPresent()) {
            BagEntity bag = optionalBag.get();
            bag.setBagName("Rolo Tote Bag");
            bag.setBagDescription("Tapestry Of Nepalese Heritage");
            bag.setPrice(4500.0);
            bag.setQuantity(60.0);
            bag.setBagImage("rolotote.png");

            BagEntity bagUpdated = bagRepository.save(bag);
            Assertions.assertThat(bagUpdated.getBagName()).isEqualTo("Rolo Tote Bag");
            Assertions.assertThat(bagUpdated.getBagDescription()).isEqualTo("Tapestry Of Nepalese Heritage");
            Assertions.assertThat(bagUpdated.getPrice()).isEqualTo(4500.0);
            Assertions.assertThat(bagUpdated.getQuantity()).isEqualTo(60.0);
            Assertions.assertThat(bagUpdated.getBagImage()).isEqualTo("rolotote.png");
        }
    }



    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteBagTest() {
        Optional<BagEntity> optionalBag = bagRepository.findById(2);
        Assertions.assertThat(optionalBag.isPresent()).isTrue();
        if (optionalBag.isPresent()) {
            BagEntity bag = optionalBag.get();
            bagRepository.delete(bag);

            Optional<BagEntity> deletedBag = bagRepository.findById(2);
            Assertions.assertThat(deletedBag.isEmpty()).isTrue();
        }
    }

}
