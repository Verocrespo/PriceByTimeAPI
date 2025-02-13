package com.inditex.price.controller;

import com.inditex.price.validation.PriceBadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PriceResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PriceBadRequestException.class})
    protected ResponseEntity<Object> handleConflict(
            PriceBadRequestException badRequestException, WebRequest request) {
        return handleExceptionInternal(badRequestException, badRequestException.getApiErrorResponse(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
