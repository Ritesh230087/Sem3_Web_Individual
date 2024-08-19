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
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
    private final BagRepository bagRepository;
    private final CategoryRepository categoryRepository;
    private final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/bagfile";

    @Override
    public void saveBag(BagPojo bagPojo) throws IOException {
        BagEntity bagEntity = new BagEntity();
        bagEntity.setBagId(bagPojo.getBagId());
        bagEntity.setBagName(bagPojo.getBagName());
        bagEntity.setBagDescription(bagPojo.getBagDescription());
        bagEntity.setQuantity(bagPojo.getQuantity());
        bagEntity.setPrice(bagPojo.getPrice());

        if (bagPojo.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }

        CategoryEntity category = categoryRepository.findById(bagPojo.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        bagEntity.setCategory(category);

        if (bagPojo.getBagImage() != null && !bagPojo.getBagImage().isEmpty()) {
            Path filesave = Paths.get(UPLOAD_DIRECTORY, bagPojo.getBagImage().getOriginalFilename());
            Files.write(filesave, bagPojo.getBagImage().getBytes());
            bagEntity.setBagImage(bagPojo.getBagImage().getOriginalFilename());
        }
        bagRepository.save(bagEntity);
        System.out.println("Bag added successfully: " + bagEntity);
    }

    @Override
    public void updateBag(BagPojo bagPojo) throws IOException {
        Optional<BagEntity> optionalBagEntity = bagRepository.findById(bagPojo.getBagId());
        if (optionalBagEntity.isPresent()) {
            BagEntity bagEntity = optionalBagEntity.get();
            bagEntity.setBagId(bagPojo.getBagId());
            bagEntity.setBagName(bagPojo.getBagName());
            bagEntity.setBagDescription(bagPojo.getBagDescription());
            bagEntity.setQuantity(bagPojo.getQuantity());
            bagEntity.setPrice(bagPojo.getPrice());

            if (bagPojo.getBagImage() != null) {
                Path filesave = Paths.get(UPLOAD_DIRECTORY, bagPojo.getBagImage().getOriginalFilename());
                Files.write(filesave, bagPojo.getBagImage().getBytes());
                bagEntity.setBagImage(bagPojo.getBagImage().getOriginalFilename());
            }
            bagRepository.save(bagEntity);
            System.out.println("Bag updated successfully: " + bagEntity);
        } else {
            throw new IllegalArgumentException("Bag with ID " + bagPojo.getBagId() + " not found");
        }
    }

    @Override
    public void decreaseBagQuantity(Integer bagId, int quantity) {
        BagEntity bag = getBagById(bagId)
                .orElseThrow(() -> new RuntimeException("Bag not found"));

        if (bag.getQuantity() < quantity) {
            throw new RuntimeException("Not enough quantity available");
        }

        bag.setQuantity(bag.getQuantity() - quantity);
        bagRepository.save(bag);
    }

    @Override
    public List<BagEntity> getAllBags() {
        ImageToBase64Bag imageToBase64Bag = new ImageToBase64Bag();
        List<BagEntity> bagEntities = bagRepository.findAll();
        for (BagEntity bag : bagEntities) {
            if (bag.getCategory() != null) {
                bag.getCategory().getCategoryName(); // Force initialization
            }
            bag.setBagImage(imageToBase64Bag.getImageBase64(bag.getBagImage()));
        }
        return bagEntities;
    }
    @Override
    public Optional<BagEntity> getBagById(Integer id) {
        try {
            Optional<BagEntity> bagEntity = bagRepository.findById(id);
            if (bagEntity.isPresent()) {
                BagEntity bag = bagEntity.get();
                if (bag.getCategory() != null) {
                    bag.getCategory().getCategoryName(); // Force initialization
                }
                if (bag.getBagImage() != null) {
                    String base64Image = new ImageToBase64Bag().getImageBase64(bag.getBagImage());
                    bag.setBagImage(base64Image);
                }
            }
            return bagEntity;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return Optional.empty();
        }
    }


    @Override
    public void deleteBag(Integer id) {
        bagRepository.deleteById(id);
    }

    @Override
    public List<BagEntity> getBagsByCategoryId(Integer categoryId) {
        List<BagEntity> bags = bagRepository.findByCategory_Id(categoryId);
        for (BagEntity bag : bags) {
            if (bag.getBagImage() != null) {
                Path imagePath = Paths.get(UPLOAD_DIRECTORY, bag.getBagImage());
                try {
                    byte[] imageBytes = Files.readAllBytes(imagePath);
                    String base64Image = Base64Utils.encodeToString(imageBytes);
                    bag.setBagImage(base64Image);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle error appropriately
                }
            }
        }
        return bags;
    }
}

