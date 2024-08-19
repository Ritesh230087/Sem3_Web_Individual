package org.example.sem3_rolo.repository;

import org.example.sem3_rolo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
