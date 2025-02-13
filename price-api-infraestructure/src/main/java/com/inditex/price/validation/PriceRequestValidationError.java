package com.inditex.price.validation;

import com.inditex.price.infraestructure.model.ApiErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PriceRequestValidationError {

    BRAND_REQUIRED(1, ErrorType.ERROR, "The brand is mandatory"),
    PRODUCT_REQUIRED(2, ErrorType.ERROR, "The product is mandatory"),
    DATE_REQUIRED(3, ErrorType.ERROR, "The application date is mandatory");
    private final int code;
    private final ErrorType errorType;
    private final String message;

    ApiErrorResponse createErrorResponse(){
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setType(errorType.getName());
        errorResponse.setMessage(message);

        return errorResponse;
    }

}
