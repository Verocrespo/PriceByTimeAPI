package com.inditex.price.validation;

import com.inditex.price.infraestructure.model.PriceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidateRequestServiceTest {

    @Autowired
    private ValidateRequestService validateRequestService;

    @Test
    void validateRequestWithIncorrectBrand() {
        PriceRequest priceRequest = new PriceRequest();

        PriceBadRequestException resultException = assertThrows(PriceBadRequestException.class,
                () -> validateRequestService.validateRequest(priceRequest));

        assertEquals(PriceRequestValidationError.BRAND_REQUIRED.getCode(),
                resultException.getApiErrorResponse().getCode());

    }

    @Test
    void validateRequestWithIncorrectProduct() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setBrandId(2L);

        PriceBadRequestException resultException = assertThrows(PriceBadRequestException.class,
                () -> validateRequestService.validateRequest(priceRequest));

        assertEquals(PriceRequestValidationError.PRODUCT_REQUIRED.getCode(),
                resultException.getApiErrorResponse().getCode());

    }

    @Test
    void validateRequestWithIncorrectDate() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setBrandId(2L);
        priceRequest.setProductId(3L);

        PriceBadRequestException resultException = assertThrows(PriceBadRequestException.class,
                () -> validateRequestService.validateRequest(priceRequest));

        assertEquals(PriceRequestValidationError.DATE_REQUIRED.getCode(),
                resultException.getApiErrorResponse().getCode());

    }

    @Test
    void validateRequestWithAllFieldsOK() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setBrandId(2L);
        priceRequest.setProductId(3L);
        priceRequest.setApplyDate(Date.valueOf(LocalDate.now()));

        assertDoesNotThrow(() -> validateRequestService.validateRequest(priceRequest));
    }

}