package com.ecom.performance_ecom.repository;


import com.ecom.performance_ecom.model.Product;
import com.ecom.performance_ecom.repository.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.id AS id, p.name AS name, p.price AS price FROM Product p")
    List<ProductProjection> findAllProjected();

}
