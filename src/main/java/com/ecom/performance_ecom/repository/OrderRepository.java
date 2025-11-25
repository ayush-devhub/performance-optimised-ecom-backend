package com.ecom.performance_ecom.repository;



import com.ecom.performance_ecom.model.Order;
import com.ecom.performance_ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    @Query("SELECT o FROM Order o " +
            "LEFT JOIN FETCH o.items i " +
            "LEFT JOIN FETCH i.product " +
            "WHERE o.user = :user")

    List<Order> findByUserWithItemsAndProducts(User user);
}