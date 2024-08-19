package org.example.sem3_rolo.service.impl;

import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.CartEntity;
import org.example.sem3_rolo.entity.UserEntity;
import org.example.sem3_rolo.pojo.CartPojo;
import org.example.sem3_rolo.repository.BagRepository;
import org.example.sem3_rolo.repository.CartRepository;
import org.example.sem3_rolo.repository.UserRepository;
import org.example.sem3_rolo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BagRepository bagRepository;

    @Override
    public void addToCart(CartPojo cartPojo) {
        UserEntity user = userRepository.findById(cartPojo.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BagEntity bag = bagRepository.findById(cartPojo.getBagId())
                .orElseThrow(() -> new RuntimeException("Bag not found"));

        CartEntity cartEntity = new CartEntity();
        cartEntity.setUser(user);
        cartEntity.setBag(bag);
        cartEntity.setQuantity(cartPojo.getQuantity());

        cartRepository.save(cartEntity);
    }

    @Override
    public List<CartPojo> getCartByUserId(Integer userId) {
        List<CartEntity> cartEntities = cartRepository.findAllByUserId(userId);
        return cartEntities.stream()
                .map(this::convertEntityToPojo)
                .collect(Collectors.toList());
    }

    private CartPojo convertEntityToPojo(CartEntity cartEntity) {
        CartPojo cartPojo = new CartPojo();
        cartPojo.setId(cartEntity.getId());
        cartPojo.setUserId(cartEntity.getUser().getId());
        cartPojo.setBagId(cartEntity.getBag().getBagId());
        cartPojo.setQuantity(cartEntity.getQuantity());
        return cartPojo;
    }

    @Override
    public void removeFromCart(Integer id) {
        cartRepository.deleteById(id);
    }
}
