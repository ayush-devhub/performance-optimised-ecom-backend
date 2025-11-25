package com.ecom.performance_ecom.service;

import com.ecom.performance_ecom.model.Product;
import com.ecom.performance_ecom.repository.ProductRepository;
import com.ecom.performance_ecom.repository.projections.ProductProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }

    public List<ProductProjection> getAllProductsOptimized() {
        return productRepository.findAllProjected();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
