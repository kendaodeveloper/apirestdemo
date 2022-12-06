package com.example.apirestdemo.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String details) {
    super(details);
  }
}