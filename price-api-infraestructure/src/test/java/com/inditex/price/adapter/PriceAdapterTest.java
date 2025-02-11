package com.inditex.price.adapter;

import com.inditex.price.domain.model.Price;
import com.inditex.price.domain.model.PriceCriteria;
import com.inditex.price.infraestructure.model.PriceRequest;
import com.inditex.price.infraestructure.model.PriceResponse;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PriceAdapterTest {

    PriceAdapter adapter = new PriceAdapter();

    private Long brandId = 1L;
    private Long productId = 1L;

    private String dateInString = "2015-01-05 10:50:55";
    private String dateInLocalDateTime = "2015-01-05T10:50:55";

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    void asPriceCriteriaWhenIsNull() {
        assertNull(adapter.asPriceCriteria(null));
    }

    @Test
    void asPriceCriteria() throws ParseException {

        PriceRequest request = new PriceRequest();
        request.setBrandId(brandId);
        request.setProductId(productId);
        request.setApplyDate(DateUtils.parseDate(dateInString, DATE_FORMAT));

        PriceCriteria expected = PriceCriteria.builder()
                .brandId(brandId)
                .productId(productId)
                .applyDate(LocalDateTime.parse(dateInLocalDateTime))
                .build();

        PriceCriteria priceCriteria = adapter.asPriceCriteria(request);

        assertEquals(expected, priceCriteria);
    }

    @Test
    void asPriceResponseWhenIsNull() {
        assertNull(adapter.asPriceResponse(null));
    }

    @Test
    void asPriceResponse() {
        PriceResponse expected = new PriceResponse();
        expected.setProductId(productId);
        expected.setBrandId(brandId);
        expected.setPriceValue(BigDecimal.TEN);

        Price price = Price.builder()
                .brandId(brandId)
                .productId(productId)
                .price(BigDecimal.TEN)
                .build();

        PriceResponse response = adapter.asPriceResponse(price);

        assertEquals(expected, response);
    }
    @Test
    void convertToDateWhenIsNull() {
        assertNull(adapter.convertToDate(null));
    }

    @Test
    void convertToDate() throws ParseException {
        Date expected = DateUtils.parseDate(dateInString, DATE_FORMAT);
        Date date = adapter.convertToDate(LocalDateTime.parse(dateInLocalDateTime));

        assertEquals(expected, date);
    }
}