package com.inditex.price.controller;

import com.inditex.price.adapter.PriceAdapter;
import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.service.IPriceSearchService;
import com.inditex.price.infraestructure.api.PriceApi;
import com.inditex.price.infraestructure.model.PriceRequest;
import com.inditex.price.infraestructure.model.PriceResponse;
import com.inditex.price.validation.ValidateRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PriceApiController implements PriceApi {

    private final IPriceSearchService priceSearchService;

    private final ValidateRequestService validateRequestService;

    @Override
    public ResponseEntity<PriceResponse> findPriceByCriteria(PriceRequest priceRequest) {
        PriceAdapter adapter = new PriceAdapter();
        validateRequestService.validateRequest(priceRequest);
        PriceCriteria priceCriteria = adapter.asPriceCriteria(priceRequest);
        Optional<Price> price = priceSearchService.findPriorityPriceByCriteria(priceCriteria);

        if(price.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(adapter.asPriceResponse(price.get()));
    }
}
