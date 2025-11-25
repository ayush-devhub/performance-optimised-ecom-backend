package com.ecom.performance_ecom.repository;

import com.ecom.performance_ecom.model.Cart;
import com.ecom.performance_ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

    @Query("SELECT c FROM Cart c " +
            "LEFT JOIN FETCH c.items i " +
            "LEFT JOIN FETCH i.product " +
            "WHERE c.user = :user")
    Optional<Cart> findByUserWithItemsAndProducts(User user);
}