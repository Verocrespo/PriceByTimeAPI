package com.inditex.price.domain.repository;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.Price;

import java.util.List;

public interface IPriceRepository {

    List<Price> findPriceByCriteria(PriceCriteria priceCriteria);
}
