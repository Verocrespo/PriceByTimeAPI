package com.inditex.price.domain.service;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.PriceEntity;

import java.util.Optional;

public interface IPriceSearchService {

    Optional<PriceEntity> findPriorityPriceByCriteria(PriceCriteria priceCriteria);
}
