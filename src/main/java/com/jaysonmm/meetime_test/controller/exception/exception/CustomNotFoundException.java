package com.jaysonmm.meetime_test.controller.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException() {
        super();
    }
    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public CustomNotFoundException(String message) {
        super(message);
    }
    public CustomNotFoundException(Throwable cause) {
        super(cause);
    }
}