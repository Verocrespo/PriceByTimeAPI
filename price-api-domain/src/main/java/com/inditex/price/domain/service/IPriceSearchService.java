package com.inditex.price.domain.service;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.Price;

import java.util.Optional;

public interface IPriceSearchService {

    Optional<Price> findPriorityPriceByCriteria(PriceCriteria priceCriteria);
}
