package com.inditex.price.validation;

import com.inditex.price.infraestructure.model.PriceRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidateRequestService {

    public void validateRequest(PriceRequest priceRequest) {
        if (priceRequest.getBrandId() == null) {
            throw new PriceBadRequestException(PriceRequestValidationError.BRAND_REQUIRED.createErrorResponse());
        }

        if (priceRequest.getProductId() == null || priceRequest.getProductId() <=0L) {
            throw new PriceBadRequestException(PriceRequestValidationError.PRODUCT_REQUIRED.createErrorResponse());
        }

        if (priceRequest.getApplyDate() == null) {
            throw new PriceBadRequestException(PriceRequestValidationError.DATE_REQUIRED.createErrorResponse());
        }
    }


}
