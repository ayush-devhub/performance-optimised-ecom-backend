package com.ecom.performance_ecom.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private Integer quantity;
    private Double priceAtPurchase;
}
