package org.example.sem3_rolo.service;

import org.example.sem3_rolo.entity.BagEntity;
import org.example.sem3_rolo.entity.OrderEntity;
import org.example.sem3_rolo.pojo.CategoryPojo;
import org.example.sem3_rolo.pojo.OrderPojo;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void saveOrder(OrderPojo orderPojo) ;
    List<OrderEntity> getAllOrders();
    Optional<OrderEntity> getOrderById(Integer id);
    void deleteOrder(Integer id);
}
