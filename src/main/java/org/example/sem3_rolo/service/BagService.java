package org.example.sem3_rolo.service;

import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.pojo.BagPojo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BagService {
    void saveBag(BagPojo bagPojo) throws IOException;
    void updateBag(BagPojo bagPojo) throws IOException;
    List<BagEntity> getAllBags();
    Optional<BagEntity> getBagById(Integer id);
    void deleteBag(Integer id);
}
