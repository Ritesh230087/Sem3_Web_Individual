package org.example.sem3_rolo.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.pojo.UserPojo;
import org.example.sem3_rolo.repository.UserRepository;
import org.example.sem3_rolo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void saveData(UserPojo userPojo) {
        UserEntity user = new UserEntity();
        user.setId(userPojo.getId());
        user.setUsername(userPojo.getUsername());
        user.setPassword(userPojo.getPassword());
        user.setFull_name(userPojo.getFull_name());
        user.setEmail(userPojo.getEmail());
        user.setAddress(userPojo.getAddress());
        userRepository.save(user);
    }

    @Override
    public UserPojo findUserByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        if (entity != null) {
            UserPojo pojo = new UserPojo();
            BeanUtils.copyProperties(entity, pojo);
            return pojo;
        }
        return null;
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUsersById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUsers(Integer id) {
        userRepository.deleteById(id);
    }
}
