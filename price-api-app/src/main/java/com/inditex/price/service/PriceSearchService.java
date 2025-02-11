package com.inditex.price.service;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.repository.IPriceRepository;
import com.inditex.price.domain.service.IPriceSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceSearchService implements IPriceSearchService {


    private final IPriceRepository priceRepository;

    @Override
    public Optional<Price> findPriorityPriceByCriteria(PriceCriteria priceCriteria) {
        log.info("PriceSearchService -> findPriorityPriceByCriteria");
        List<Price> prices = priceRepository.findPriceByCriteria(priceCriteria);
        if(prices.isEmpty()) {
            return Optional.empty();
        }
        prices.sort(Comparator.comparing(Price::getPriority).reversed());

        return Optional.of(prices.get(0));
    }
}
