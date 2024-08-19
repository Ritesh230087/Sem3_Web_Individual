package org.example.sem3_rolo.repository;

import org.example.sem3_rolo.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    List<CartEntity> findAllByUserId(Integer userId);
}
