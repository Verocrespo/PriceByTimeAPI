package com.inditex.price.domain.repository;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.PriceEntity;

import java.util.List;

public interface IPriceRepository {

    List<PriceEntity> findPriceByCriteria(PriceCriteria priceCriteria);
}
