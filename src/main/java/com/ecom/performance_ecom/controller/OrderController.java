package com.ecom.performance_ecom.controller;


import com.ecom.performance_ecom.dto.OrderResponseDTO;
import com.ecom.performance_ecom.mapper.DtoMapper;
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
    public OrderResponseDTO checkout(@PathVariable Long userId) {
        return DtoMapper.toOrderDTO(service.checkout(userId));
    }

    @GetMapping("/{userId}")
    public List<OrderResponseDTO> getOrders(@PathVariable Long userId) {
        return service.getOrdersByUser(userId).stream()
                .map(DtoMapper::toOrderDTO)
                .toList();
    }

}
