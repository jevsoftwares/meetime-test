package com.jaysonmm.meetime_test.controller.exception.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Iterator;

@Builder
@Getter
public class ErrorMessage {
  private int statusCode;
  private Iterator<String> param;
  private String target;
  private LocalDateTime timestamp;
  private String message;
}