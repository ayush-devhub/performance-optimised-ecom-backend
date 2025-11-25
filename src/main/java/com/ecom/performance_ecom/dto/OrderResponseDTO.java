package com.ecom.performance_ecom.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private String status;
    private List<OrderItemDTO> items;
}
