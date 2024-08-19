package org.example.sem3_rolo.service.impl;

import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.CartEntity;
import org.example.sem3_rolo.entity.OrderEntity;
import org.example.sem3_rolo.pojo.OrderPojo;
import org.example.sem3_rolo.repository.BagRepository;
import org.example.sem3_rolo.repository.CartRepository;
import org.example.sem3_rolo.repository.OrderRepository;
import org.example.sem3_rolo.repository.UserRepository;
import org.example.sem3_rolo.service.CartService;
import org.example.sem3_rolo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BagRepository bagRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;


    @Override
    public void saveOrder(OrderPojo orderPojo) {
        // Fetch all cart items for the user
        List<CartEntity> cartItems = cartRepository.findAllByUserId(orderPojo.getUserId());

        for (CartEntity cartItem : cartItems) {
            BagEntity bag = cartItem.getBag();
            Integer quantityInCart = cartItem.getQuantity();

            // Ensure the bag is not out of stock
            if (bag.getQuantity() < quantityInCart) {
                throw new RuntimeException("Insufficient stock for bag with ID: " + bag.getBagId());
            }

            // Create an OrderEntity for each cart item
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUser(cartItem.getUser());
            orderEntity.setBag(bag);
            orderEntity.setTotalAmount(bag.getPrice() * quantityInCart); // Adjust based on how you calculate total amount

            // Save the order
            orderRepository.save(orderEntity);

            // Decrease the bag quantity
            bag.setQuantity(bag.getQuantity() - quantityInCart);
            bagRepository.save(bag);

            // Remove item from the cart
            cartService.removeFromCart(cartItem.getId());
        }
    }


    @Override
    public List<OrderPojo> getAllOrders() {
        // Fetch all OrderEntity from the repository
        List<OrderEntity> orderEntities = orderRepository.findAll();

        // Convert OrderEntity list to OrderPojo list
        return orderEntities.stream()
                .map(this::convertToPojo) // Convert entities to POJOs
                .collect(Collectors.toList());
    }

    private OrderPojo convertToPojo(OrderEntity orderEntity) {
        // Implement the conversion logic here
        OrderPojo orderPojo = new OrderPojo();
        orderPojo.setId(orderEntity.getId());
        orderPojo.setUserId(orderEntity.getUser().getId()); // Adjust as needed
        orderPojo.setBagId(orderEntity.getBag().getBagId());
        orderPojo.setTotalAmount(orderEntity.getTotalAmount());
        // Set other fields if necessary
        return orderPojo;
    }

    @Override
    public Optional<OrderPojo> getOrderById(Integer id) {
        // Implement logic to return order by ID
        return Optional.empty();
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
