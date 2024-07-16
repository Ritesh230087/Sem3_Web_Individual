package org.example.sem3_rolo.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.CategoryEntity;
import org.example.sem3_rolo.pojo.BagPojo;
import org.example.sem3_rolo.repository.BagRepository;
import org.example.sem3_rolo.repository.CategoryRepository;
import org.example.sem3_rolo.service.BagService;
import org.example.sem3_rolo.utils.ImageToBase64Bag;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
    private final BagRepository bagRepository;

    private final CategoryRepository categoryRepository;

    private final String UPLOAD_DIRECTORY =System.getProperty("user.dir")+"/bagfile";
    @Override
    public void saveBag(BagPojo bagPojo) throws IOException {
        BagEntity bagEntity = new BagEntity();
        bagEntity.setBagId(bagPojo.getBagId());
        bagEntity.setBagName(bagPojo.getBagName());
        bagEntity.setBagDescription(bagPojo.getBagDescription());
        bagEntity.setQuantity(bagPojo.getQuantity());
        bagEntity.setPrice(bagPojo.getPrice());

        CategoryEntity category = categoryRepository.findById(bagPojo.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        bagEntity.setCategory(category);

        if(bagPojo.getBagImage()!=null){
            Path filesave= Paths.get(UPLOAD_DIRECTORY,bagPojo.getBagImage().getOriginalFilename());
            Files.write(filesave,bagPojo.getBagImage().getBytes());
            bagEntity.setBagImage(bagPojo.getBagImage().getOriginalFilename());
        }
        bagRepository.save(bagEntity);
    }

    @Override
    public void updateBag(BagPojo bagPojo) throws IOException {
        BagEntity bagEntity = bagRepository.findById(bagPojo.getBagId())
                .orElseThrow(() -> new RuntimeException("Bag not found"));
        bagEntity.setBagName(bagPojo.getBagName());
        bagEntity.setBagDescription(bagPojo.getBagDescription());
        bagEntity.setQuantity(bagPojo.getQuantity());
        bagEntity.setPrice(bagPojo.getPrice());

        CategoryEntity category = categoryRepository.findById(bagPojo.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        bagEntity.setCategory(category);

        // Handle the image update logic
        if (bagPojo.getBagImage() != null && !bagPojo.getBagImage().isEmpty()) {
            // Delete the old image file if necessary
            if (bagEntity.getBagImage() != null) {
                Path oldFilePath = Paths.get(UPLOAD_DIRECTORY, bagEntity.getBagImage());
                Files.deleteIfExists(oldFilePath);
            }

            // Save the new image file
            Path fileSavePath = Paths.get(UPLOAD_DIRECTORY, bagPojo.getBagImage().getOriginalFilename());
            Files.write(fileSavePath, bagPojo.getBagImage().getBytes());
            bagEntity.setBagImage(bagPojo.getBagImage().getOriginalFilename());
        }

        // Save the updated BagEntity back to the repository
        bagRepository.save(bagEntity);
    }

    @Override
    public void decreaseBagQuantity(Integer bagId, int quantityToDecrease) {
        Optional<BagEntity> optionalBagEntity = bagRepository.findById(bagId);
        if (optionalBagEntity.isPresent()) {
            BagEntity bagEntity = optionalBagEntity.get();
            Double currentQuantity = bagEntity.getQuantity();
            if (currentQuantity >= quantityToDecrease) {
                bagEntity.setQuantity(currentQuantity - quantityToDecrease);
                bagRepository.save(bagEntity);
            } else {
                throw new IllegalArgumentException("Not enough quantity available in stock");
            }
        } else {
            throw new RuntimeException("Bag not found");
        }
    }

    @Override
    public List<BagEntity> getAllBags() {
        ImageToBase64Bag imageToBase64Bike=new ImageToBase64Bag();
        List<BagEntity> bagEntities = bagRepository.findAll();
        bagEntities=bagEntities.stream().map(bike->{
            bike.setBagImage(imageToBase64Bike.getImageBase64(bike.getBagImage()));
            return bike;
        }).collect(Collectors.toList());
        return bagEntities;
    }

    @Override
    public Optional<BagEntity> getBagById(Integer id) {
        return bagRepository.findById(id);
    }

    @Override
    public void deleteBag(Integer id) {
        bagRepository.deleteById(id);
    }
}
