package com.jaysonmm.meetime_test.controller.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class CustomUnauthorizedException extends RuntimeException {
    public CustomUnauthorizedException() {
        super();
    }
    public CustomUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomUnauthorizedException(String message) {
        super(message);
    }
    public CustomUnauthorizedException(Throwable cause) {
        super(cause);
    }
}