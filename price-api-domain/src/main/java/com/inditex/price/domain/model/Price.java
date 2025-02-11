package com.inditex.price.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "prices")
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, name="PRICE_LIST")
    private Long id;

    @Column(nullable = false, name="BRAND_ID")
    private Long brandId;

    @Column(nullable = false, name="PRODUCT_ID")
    private Long productId;

    @Column(nullable = false, name="START_DATE")
    private LocalDateTime startDate;

    @Column(nullable = false, name="END_DATE")
    private LocalDateTime endDate;

    @Column(nullable = false, name="PRIORITY")
    private Short priority;

    @Column(nullable = false, name="PRICE")
    private BigDecimal price;

    @Column(nullable = false, name="CURR")
    private String currency;

}
