package org.example.sem3_rolo.service;

import org.example.sem3_rolo.pojo.OrderPojo;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    void saveOrder(OrderPojo orderPojo);
    List<OrderPojo> getAllOrders();
    Optional<OrderPojo> getOrderById(Integer id);
    void deleteOrder(Integer id);
}
