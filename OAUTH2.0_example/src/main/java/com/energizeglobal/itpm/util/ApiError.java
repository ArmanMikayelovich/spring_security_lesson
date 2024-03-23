package com.energizeglobal.itpm.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public class ApiError {

    private final String message;
    private final int status;

    public ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }
}
