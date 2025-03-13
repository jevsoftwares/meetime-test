package com.jaysonmm.meetime_test.controller.exception;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaysonmm.meetime_test.controller.exception.exception.CustomNotFoundException;
import com.jaysonmm.meetime_test.controller.exception.exception.CustomUnauthorizedException;
import com.jaysonmm.meetime_test.controller.exception.model.ErrorMessage;
import com.jaysonmm.meetime_test.controller.exception.model.adapter.LocalDateTypeAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

  private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).create();

  @ExceptionHandler(value = {CustomNotFoundException.class})
  public ResponseEntity<ErrorMessage> customNotFoundException(Exception ex, WebRequest request) {

    ErrorMessage message = ErrorMessage.builder()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .param(request.getParameterNames())
            .target(request.getDescription(false))
            .timestamp(LocalDateTime.now())
            .message(ex.getMessage())
            .build();


    log.error( gson.toJson(message) );
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {CustomUnauthorizedException.class, AuthorizationDeniedException.class})
  public ResponseEntity<ErrorMessage> customUnauthorizedException(Exception ex, WebRequest request) {

    ErrorMessage message = ErrorMessage.builder()
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .param(request.getParameterNames())
            .target(request.getDescription(false))
            .timestamp(LocalDateTime.now())
            .message(ex.getMessage())
            .build();


    log.error( gson.toJson(message) );
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {

    ErrorMessage message = ErrorMessage.builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .param(request.getParameterNames())
            .target(request.getDescription(false))
            .timestamp(LocalDateTime.now())
            .message(ex.getMessage())
            .build();

    log.error( gson.toJson(message) );
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}