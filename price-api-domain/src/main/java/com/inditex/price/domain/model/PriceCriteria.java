package com.inditex.price.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PriceCriteria (Long productId, Long brandId, LocalDateTime applyDate) {
}
