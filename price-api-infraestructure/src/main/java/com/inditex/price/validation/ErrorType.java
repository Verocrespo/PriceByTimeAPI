package com.inditex.price.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorType {

    FATAL("fatal"),
    ERROR("error"),
    WARNING("warning");

    private final String name;

}
