package com.inditex.price.adapter;

import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.domain.model.Price;
import com.inditex.price.infraestructure.model.PriceRequest;
import com.inditex.price.infraestructure.model.PriceResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PriceAdapter {

    public PriceCriteria asPriceCriteria(PriceRequest priceRequest){

        return PriceCriteria.builder()
                .brandId(priceRequest.getBrandId())
                .productId(priceRequest.getProductId())
                .applyDate(LocalDateTime.ofInstant(priceRequest.getApplyDate().toInstant(), ZoneId.systemDefault()))
                .build();
    }

    public PriceResponse asPriceResponse(Price price) {
        PriceResponse priceResponse = new PriceResponse();

        priceResponse.setPriceId(price.getId());
        priceResponse.setPriceValue(price.getPrice());
        priceResponse.setCurrency(price.getCurrency());
        priceResponse.setBrandId(price.getBrandId());
        priceResponse.setApplyEndDate(convertToDate(price.getEndDate()));
        priceResponse.setApplyStartDate(convertToDate(price.getStartDate()));
        priceResponse.setProductId(price.getProductId());

        return priceResponse;
    }

    public Date convertToDate(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

}
