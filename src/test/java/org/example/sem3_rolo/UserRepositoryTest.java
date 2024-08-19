package org.example.sem3_rolo;


import org.assertj.core.api.Assertions;
import org.example.sem3_rolo.entity.UserEntity;
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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {
        UserEntity user=new UserEntity();
        user.setUsername("rolo");
        user.setPassword("Rolo@1234");
        user.setFullName("Rolo Nepal");
        user.setAddress("Kathmandu");
        user.setEmail("rolonepal@gmail.com");
        user=userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    public  void findByIdTest(){

        UserEntity userGet= userRepository.findById(1).get();
        Assertions.assertThat(userGet.getId()).isEqualTo(1);
    }


    @Test
    @Order(3)
    public void getListOfUserTest(){
        List<UserEntity> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    @Rollback(value = false)
    public void deleteCustomerTest(){
        UserEntity user = userRepository.findById(1).get();
        userRepository.delete(user);
        UserEntity user1 = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findById(1);
        if(optionalUserEntity.isPresent()){
            user1 = optionalUserEntity.get();
        }
        Assertions.assertThat(user1).isNull();
    }
}
