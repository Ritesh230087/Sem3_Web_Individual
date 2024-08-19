package org.example.sem3_rolo.controller;

import org.example.sem3_rolo.pojo.CartPojo;
import org.example.sem3_rolo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartPojo cartPojo) {
        cartService.addToCart(cartPojo);
        return ResponseEntity.ok("Added to cart successfully!");
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<CartPojo>> getCartByUserId(@PathVariable Integer userId) {
        List<CartPojo> cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFromCart(@PathVariable Integer id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok("Removed from cart successfully!");
    }
}
