package com.ecom.performance_ecom.repository;

import com.ecom.performance_ecom.model.Cart;
import com.ecom.performance_ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}