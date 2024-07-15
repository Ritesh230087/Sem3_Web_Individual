package org.example.sem3_rolo.repository;

import org.example.sem3_rolo.entity.BagEntity;
import org.hibernate.annotations.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<BagEntity, Integer> {
}
