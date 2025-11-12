package com.ecom.performance_ecom.repository;

import com.ecom.performance_ecom.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}