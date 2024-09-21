package com.learn.product_service.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private String productId;//this productId should be unique
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
