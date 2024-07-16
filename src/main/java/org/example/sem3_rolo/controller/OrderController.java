package org.example.sem3_rolo.controller;

import lombok.RequiredArgsConstructor;
import org.example.sem3_rolo.entity.OrderEntity;
import org.example.sem3_rolo.pojo.OrderPojo;
import org.example.sem3_rolo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public void addOrder(@RequestBody OrderPojo orderPojo) {
        orderService.saveOrder(orderPojo);
    }

    @GetMapping("/orderlist")
    public List<OrderEntity> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orderlist/{id}")
    public Optional<OrderEntity> getOrderById(Integer id){
        return orderService.getOrderById(id);
    }

    @DeleteMapping("/delete/id")
    public void deleteOrderById(Integer id) {
        orderService.deleteOrder(id);
    }

}
