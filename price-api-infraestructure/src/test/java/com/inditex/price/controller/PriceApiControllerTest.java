package com.inditex.price.controller;

import com.inditex.price.adapter.PriceAdapter;
import com.inditex.price.infraestructure.model.PriceRequest;
import com.inditex.price.infraestructure.model.PriceResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceApiControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final PriceAdapter priceAdapter = new PriceAdapter();

    private static final int YEAR = 2020;
    private static final int MONTH = 6;

    private static final int PRODUCT = 35455;

    private static final int BRAND = 1;


    @Test
    @DisplayName("When criteria has not data then return not found")
    void findPriceByCriteriaNotFound() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setBrandId(0L);
        priceRequest.setProductId(0L);
        priceRequest.setApplyDate(Date.valueOf(LocalDate.now()));
        ResponseEntity<PriceResponse> priceResponse = restTemplate.postForEntity("/prices/findByCriteria",
                priceRequest,
                PriceResponse.class);

        assertEquals(HttpStatus.NOT_FOUND, priceResponse.getStatusCode());
    }


    @Test
    @DisplayName("Request 1: 10:00 day 14, product 35455, brand 1 ZARA ")

    void findPriceByCriteriaRequest1() {
        PriceRequest priceRequest = createPriceRequest(PRODUCT, BRAND, 14, 10, 0);
        ResponseEntity<PriceResponse> priceResponse = restTemplate.postForEntity("/prices/findByCriteria",
                priceRequest,
                PriceResponse.class);

        assertEquals(HttpStatus.OK, priceResponse.getStatusCode());
        assertEquals(35.50, priceResponse.getBody().getPriceValue().doubleValue());
    }

    @Test
    @DisplayName("Request 2: 16:00 day 14, product 35455, brand 1 ZARA ")

    void findPriceByCriteriaRequest2() {
        PriceRequest priceRequest = createPriceRequest(PRODUCT, BRAND, 14, 16, 0);
        ResponseEntity<PriceResponse> priceResponse = restTemplate.postForEntity("/prices/findByCriteria",
                priceRequest,
                PriceResponse.class);

        assertEquals(HttpStatus.OK, priceResponse.getStatusCode());
        assertEquals(25.45, priceResponse.getBody().getPriceValue().doubleValue());
    }

    @Test
    @DisplayName("Request 3: 21:00 day 14, product 35455, brand 1 ZARA ")
    void findPriceByCriteriaRequest3() {
        PriceRequest priceRequest = createPriceRequest(PRODUCT, BRAND, 14, 21, 0);
        ResponseEntity<PriceResponse> priceResponse = restTemplate.postForEntity("/prices/findByCriteria",
                priceRequest,
                PriceResponse.class);

        assertEquals(HttpStatus.OK, priceResponse.getStatusCode());
        assertEquals(35.5, priceResponse.getBody().getPriceValue().doubleValue());
    }

    @Test
    @DisplayName("Request 4: 10:00 day 15, product 35455, brand 1 ZARA ")
    void findPriceByCriteriaRequest4() {
        PriceRequest priceRequest = createPriceRequest(PRODUCT, BRAND, 15, 10, 0);
        ResponseEntity<PriceResponse> priceResponse = restTemplate.postForEntity("/prices/findByCriteria",
                priceRequest,
                PriceResponse.class);

        assertEquals(HttpStatus.OK, priceResponse.getStatusCode());
        assertEquals(30.5, priceResponse.getBody().getPriceValue().doubleValue());
    }

    @Test
    @DisplayName("Request 5: 21:00 day 16, product 35455, brand 1 ZARA ")
    void findPriceByCriteriaRequest5() {
        PriceRequest priceRequest = createPriceRequest(PRODUCT, BRAND, 16, 21, 0);
        ResponseEntity<PriceResponse> priceResponse = restTemplate.postForEntity("/prices/findByCriteria",
                priceRequest,
                PriceResponse.class);

        assertEquals(HttpStatus.OK, priceResponse.getStatusCode());
        assertEquals(38.95, priceResponse.getBody().getPriceValue().doubleValue());
    }


    private PriceRequest createPriceRequest(long productId, long brandId, int day, int hour, int minute) {
        PriceRequest priceRequest = new PriceRequest();

        priceRequest.setProductId(productId);
        priceRequest.setBrandId(brandId);
        LocalDateTime localDateTime = LocalDateTime.of(2020, 06, day,hour, minute);


        priceRequest.setApplyDate(priceAdapter.convertToDate(localDateTime));

        return priceRequest;
    }
}