package com.inditex.price.domain.model;

import java.time.LocalDateTime;

public record PriceCriteria (Long productId, Long brandId, LocalDateTime applyDate) {
}
