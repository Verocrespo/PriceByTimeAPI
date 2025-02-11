package com.inditex.price.service;

import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.repository.IPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PriceSearchServiceTest {

    @Mock
    private IPriceRepository priceRepository;

    @InjectMocks
    private PriceSearchService priceSearchService;

    @Test
    void findPriorityPriceByCriteriaEmptyList() {
        doReturn(List.of()).when(priceRepository).findPriceByCriteria(any());

        Optional<Price> price = priceSearchService.findPriorityPriceByCriteria(PriceCriteria.builder().build());

        assertTrue(price.isEmpty());
    }

    @Test
    void findPriorityPriceByCriteriaReturnAList() {
        Price priceLowPriority = Price.builder()
                .priority((short) 1)
                .price(BigDecimal.valueOf(32.12)).build();
        Price mediumPriority = Price.builder()
                .priority((short) 3)
                .price(BigDecimal.valueOf(10.12)).build();
        Price maxPriority =  Price.builder()
                .priority((short) 5)
                .price(BigDecimal.valueOf(55.12)).build();

        List<Price> prices = new ArrayList<>();
        prices.add(priceLowPriority);
        prices.add(mediumPriority);
        prices.add(maxPriority);

        doReturn(prices).when(priceRepository).findPriceByCriteria(any());

        Optional<Price> price = priceSearchService.findPriorityPriceByCriteria(PriceCriteria.builder().build());

        assertEquals(maxPriority, price.get());
    }


}