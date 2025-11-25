package com.ecom.performance_ecom.service;


import com.ecom.performance_ecom.model.*;
import com.ecom.performance_ecom.model.User;
import com.ecom.performance_ecom.repository.*;
import jakarta.transaction.Transactional;
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

    @Transactional // This is CRITICAL for an "all-or-nothing" checkout
    public Order checkout(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();

        // 1. Use the N+1 fix to get the cart, items, and products in ONE query
        Cart cart = cartRepo.findByUserWithItemsAndProducts(user).orElseThrow();

        // 2. Create the parent Order *first*
        Order order = Order.builder()
                .user(user)
                .status("PLACED")
                .items(new ArrayList<>()) // Initialize an empty list
                .build();

        double total = 0;

        // 3. Loop through cart items
        for (CartItem ci : cart.getItems()) {
            double price = ci.getProduct().getPrice() * ci.getQuantity();
            total += price;

            // 4. Create the OrderItem and set its parent
            OrderItem oi = OrderItem.builder()
                    .product(ci.getProduct())
                    .quantity(ci.getQuantity())
                    .priceAtPurchase(ci.getProduct().getPrice())
                    .order(order) // <-- THE FIX: Set the parent
                    .build();

            // 5. Add the child to the parent's list
            order.getItems().add(oi);
        }

        // 6. Now set the final total
        order.setTotalAmount(total);

        // 7. Save the Order. CascadeType.ALL will now save the OrderItems,
        // which all have a valid (non-null) order.
        Order saved = orderRepo.save(order);

        // 9. Clear the cart
        cartItemRepo.deleteAll(cart.getItems());

        return saved;
    }


    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        // Call the new, efficient query instead of the default one
        return orderRepo.findByUserWithItemsAndProducts(user);
    }
}