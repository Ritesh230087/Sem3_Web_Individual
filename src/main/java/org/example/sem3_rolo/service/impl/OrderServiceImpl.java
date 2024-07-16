package org.example.sem3_rolo.service.impl;

import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.OrderEntity;
import org.example.sem3_rolo.pojo.OrderPojo;
import org.example.sem3_rolo.repository.OrderRepository;
import org.example.sem3_rolo.service.BagService;
import org.example.sem3_rolo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BagService bagService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, BagService bagService) {
        this.orderRepository = orderRepository;
        this.bagService = bagService;
    }

    @Override
    public void saveOrder(OrderPojo orderPojo) {
        // Implement your logic to save order and decrease bag quantities
        OrderEntity orderEntity = new OrderEntity();
        // Set orderEntity properties from orderPojo
        orderRepository.save(orderEntity);

        // Decrease bag quantities
        for (BagEntity bag : orderPojo.getBags()) {
            bagService.decreaseBagQuantity(bag.getBagId(), 1); // Assuming 1 item per bag for now
        }
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
