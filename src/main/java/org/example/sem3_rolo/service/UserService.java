package org.example.sem3_rolo.service;

import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.pojo.UserPojo;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveData(UserPojo userPojo);
    UserPojo findUserByEmail(String email);
    boolean authenticateUser(String email, String password);
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUsersById(Integer id);
    void deleteUsers(Integer id);
}
