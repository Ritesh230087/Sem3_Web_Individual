package org.example.sem3_rolo.controller;

import org.example.sem3_rolo.pojo.OrderPojo;
import org.example.sem3_rolo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<?> saveOrder(@RequestBody OrderPojo orderPojo) {
        try {
            orderService.saveOrder(orderPojo);
            return ResponseEntity.ok(orderService);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error placing order: " + e.getMessage());
        }
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderPojo>> getAllOrders() {
        try {
            List<OrderPojo> orders = orderService.getAllOrders();
            if (orders == null || orders.isEmpty()) {
                return ResponseEntity.noContent().build(); // Returns 204 No Content if the list is null or empty
            }
            return ResponseEntity.ok(orders); // Returns the orders as JSON
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Returns 500 if there's an error
        }
    }


    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrderPojo> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting order: " + e.getMessage());
        }
    }
}
