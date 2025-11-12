package com.ecom.performance_ecom.controller;


import com.ecom.performance_ecom.model.Cart;
import com.ecom.performance_ecom.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/{userId}/add")
    public Cart addItem(@PathVariable Long userId,
                        @RequestParam Long productId,
                        @RequestParam(defaultValue = "1") int quantity) {
        return service.addItemToCart(userId, productId, quantity);
    }

    @GetMapping("/{userId}")
    public Cart viewCart(@PathVariable Long userId) {
        return service.viewCart(userId);
    }

    @DeleteMapping("/item/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        service.removeItem(itemId);
    }
}