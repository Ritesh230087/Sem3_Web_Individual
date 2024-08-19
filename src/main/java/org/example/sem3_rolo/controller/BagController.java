package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.pojo.BagPojo;
import org.example.sem3_rolo.repository.BagRepository;
import org.example.sem3_rolo.service.BagService;
import org.example.sem3_rolo.utils.ImageToBase64Bag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bag")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BagController {

    private final BagService bagService;
    private final BagRepository bagRepository;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> addBag(@ModelAttribute BagPojo bagPojo) {
        try {
            if (bagPojo.getBagImage() != null) {
                // Image processing or validation logic if needed
            }
            bagService.saveBag(bagPojo);
            return ResponseEntity.ok("Bag added successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding bag: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @PatchMapping("/decrease/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> decreaseBagQuantity(
            @PathVariable("id") Integer id,
            @RequestParam("quantity") int quantityToDecrease) {
        try {
            bagService.decreaseBagQuantity(id, quantityToDecrease);
            return ResponseEntity.ok("Bag quantity decreased successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> updateBag(@PathVariable("id") Integer id, @ModelAttribute BagPojo bagPojo) {
        try {
            bagPojo.setBagId(id);
            bagService.updateBag(bagPojo);
            return ResponseEntity.ok("Bag updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating bag: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<BagEntity>> getAllBags() {
        try {
            List<BagEntity> bags = bagService.getAllBags();
            return ResponseEntity.ok(bags);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/list/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Optional<BagEntity>> getBagById(@PathVariable Integer id) {
        try {
            Optional<BagEntity> bag = bagService.getBagById(id);
            if (bag.isPresent()) {
                return ResponseEntity.ok(bag);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Optional.empty());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Optional.empty());
        }
    }
    @GetMapping("/by-category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<BagEntity>> getBagsByCategoryId(@PathVariable Integer categoryId) {
        try {
            List<BagEntity> bags = bagService.getBagsByCategoryId(categoryId);
            return ResponseEntity.ok(bags);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> deleteBag(@PathVariable Integer id) {
        try {
            bagService.deleteBag(id);
            return ResponseEntity.ok("Bag deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting bag: " + e.getMessage());
        }
    }
}
