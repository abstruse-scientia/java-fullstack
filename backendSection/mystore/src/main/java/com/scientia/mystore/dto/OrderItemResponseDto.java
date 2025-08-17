package com.scientia.mystore.dto;

import java.math.BigDecimal;

public record OrderItemResponseDto(String productName, Integer quantity, BigDecimal price, String imageUrl) {
}
