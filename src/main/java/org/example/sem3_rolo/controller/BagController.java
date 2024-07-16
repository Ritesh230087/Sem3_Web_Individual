package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.pojo.BagPojo;
import org.example.sem3_rolo.service.BagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bag")
@RequiredArgsConstructor
public class BagController {
    private final BagService bagService;


    @PostMapping("/add")
    public void addBag(@RequestBody @ModelAttribute BagPojo bagPojo) throws IOException {
        bagService.saveBag(bagPojo);
    }

    @PutMapping("/bag/{id}")
    public ResponseEntity<String> updateBag( @PathVariable("id") Integer id, @ModelAttribute BagPojo bagPojo) {
        try {
            bagPojo.setBagId(id);
            bagService.updateBag(bagPojo);
            return ResponseEntity.ok("Bag updated successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error updating bag: " + e.getMessage());
        }
    }

    @GetMapping("/baglist")
    public List<BagEntity> getAllBags() {
        return bagService.getAllBags();
    }

    @GetMapping("/baglist/{id}")
    public Optional<BagEntity> getBagById(@PathVariable Integer id) {
        return bagService.getBagById(id);
    }

    @DeleteMapping("/addlist/{id}")
    public void deleteBag(@PathVariable Integer id) {
        bagService.deleteBag(id);
    }
}
