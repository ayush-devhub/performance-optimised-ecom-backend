package com.ecom.performance_ecom.service;


import com.ecom.performance_ecom.model.*;
import com.ecom.performance_ecom.model.User;
import com.ecom.performance_ecom.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final UserRepository userRepo;

    public OrderService(OrderRepository orderRepo, OrderItemRepository orderItemRepo,
                        CartRepository cartRepo, CartItemRepository cartItemRepo,
                        UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.userRepo = userRepo;
    }

    public Order checkout(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        Cart cart = cartRepo.findByUser(user).orElseThrow();

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem ci : cart.getItems()) {
            double price = ci.getProduct().getPrice() * ci.getQuantity();
            total += price;

            OrderItem oi = OrderItem.builder()
                    .product(ci.getProduct())
                    .quantity(ci.getQuantity())
                    .priceAtPurchase(ci.getProduct().getPrice())
                    .build();
            orderItems.add(oi);
        }

        Order order = Order.builder()
                .user(user)
                .totalAmount(total)
                .status("PLACED")
                .items(orderItems)
                .build();

        Order saved = orderRepo.save(order);
        for (OrderItem oi : orderItems) {
            oi.setOrder(saved);
            orderItemRepo.save(oi);
        }

        // Clear cart
        cartItemRepo.deleteAll(cart.getItems());

        return saved;
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return orderRepo.findByUser(user);
    }
}