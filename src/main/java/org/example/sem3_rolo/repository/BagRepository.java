package org.example.sem3_rolo.repository;

import org.example.sem3_rolo.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BagRepository extends JpaRepository<BagEntity, Integer> {
    List<BagEntity> findByCategory_Id(Integer categoryId);
}
