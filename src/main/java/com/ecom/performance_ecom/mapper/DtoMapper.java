package com.ecom.performance_ecom.mapper;

import com.ecom.performance_ecom.dto.*;
import com.ecom.performance_ecom.model.*;
import java.util.stream.Collectors;

public class DtoMapper {

    // PRODUCT
    public static ProductDTO toProductDTO(Product p) {
        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .price(p.getPrice())
                .build();
    }

    // CART ITEM
    public static CartItemDTO toCartItemDTO(CartItem ci) {
        return CartItemDTO.builder()
                .id(ci.getId())
                .quantity(ci.getQuantity())
                .product(toProductDTO(ci.getProduct()))
                .build();
    }

    // CART
    public static CartResponseDTO toCartDTO(Cart c) {
        return CartResponseDTO.builder()
                .id(c.getId())
                .userId(c.getUser().getId())
                .items(
                        c.getItems().stream()
                                .map(DtoMapper::toCartItemDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    // ORDER ITEM
    public static OrderItemDTO toOrderItemDTO(OrderItem oi) {
        return OrderItemDTO.builder()
                .id(oi.getId())
                .quantity(oi.getQuantity())
                .priceAtPurchase(oi.getPriceAtPurchase())
                .product(toProductDTO(oi.getProduct()))
                .build();
    }

    // ORDER
    public static OrderResponseDTO toOrderDTO(Order o) {
        return OrderResponseDTO.builder()
                .id(o.getId())
                .userId(o.getUser().getId())
                .totalAmount(o.getTotalAmount())
                .status(o.getStatus())
                .items(
                        o.getItems().stream()
                                .map(DtoMapper::toOrderItemDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
