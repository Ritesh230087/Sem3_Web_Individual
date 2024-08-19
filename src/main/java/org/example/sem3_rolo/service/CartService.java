package org.example.sem3_rolo.service;

import org.example.sem3_rolo.pojo.CartPojo;
import java.util.List;

public interface CartService {
    void addToCart(CartPojo cartPojo);
    List<CartPojo> getCartByUserId(Integer userId);
    void removeFromCart(Integer id);
}
