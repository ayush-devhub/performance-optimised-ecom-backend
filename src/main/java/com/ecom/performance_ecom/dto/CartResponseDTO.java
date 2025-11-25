package com.ecom.performance_ecom.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;
}
