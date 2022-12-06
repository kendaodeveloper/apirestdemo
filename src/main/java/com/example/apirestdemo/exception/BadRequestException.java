package com.example.apirestdemo.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String details) {
    super(details);
  }
}