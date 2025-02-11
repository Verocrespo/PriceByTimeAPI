package com.inditex.price.controller;

import com.inditex.price.adapter.PriceAdapter;
import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.service.IPriceSearchService;
import com.inditex.price.infraestructure.api.PriceApi;
import com.inditex.price.infraestructure.model.PriceRequest;
import com.inditex.price.infraestructure.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PriceApiController implements PriceApi {

    private final IPriceSearchService priceSearchService;

    @Override
    public ResponseEntity<PriceResponse> findPriceByCriteria(PriceRequest priceRequest) {
        PriceAdapter adapter = new PriceAdapter();
        PriceCriteria priceCriteria = adapter.asPriceCriteria(priceRequest);
        Optional<Price> price = priceSearchService.findPriorityPriceByCriteria(priceCriteria);

        if(price.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adapter.asPriceResponse(price.get()));
    }
}
