package com.inditex.price.validation;

import com.inditex.price.infraestructure.model.ApiErrorResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class PriceBadRequestException extends RuntimeException {

    private final ApiErrorResponse apiErrorResponse;

}
