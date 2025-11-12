package com.ecom.performance_ecom.controller;


import com.ecom.performance_ecom.model.Order;
import com.ecom.performance_ecom.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/checkout/{userId}")
    public Order checkout(@PathVariable Long userId) {
        return service.checkout(userId);
    }

    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return service.getOrdersByUser(userId);
    }
}
