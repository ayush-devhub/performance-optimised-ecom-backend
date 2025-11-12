package com.ecom.performance_ecom.service;

import com.ecom.performance_ecom.model.Cart;
import com.ecom.performance_ecom.model.CartItem;
import com.ecom.performance_ecom.model.Product;
import com.ecom.performance_ecom.model.User;
import com.ecom.performance_ecom.repository.CartItemRepository;
import com.ecom.performance_ecom.repository.CartRepository;
import com.ecom.performance_ecom.repository.ProductRepository;
import com.ecom.performance_ecom.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, CartItemRepository itemRepo,
                       UserRepository userRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public Cart getOrCreateCart(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return cartRepo.findByUser(user)
                .orElseGet(() -> cartRepo.save(Cart.builder().user(user).build()));
    }

    public Cart addItemToCart(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepo.findById(productId).orElseThrow();

        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();

        itemRepo.save(item);
        return cartRepo.findById(cart.getId()).orElseThrow();
    }

    public Cart viewCart(Long userId) {
        return getOrCreateCart(userId);
    }

    public void removeItem(Long itemId) {
        itemRepo.deleteById(itemId);
    }
}