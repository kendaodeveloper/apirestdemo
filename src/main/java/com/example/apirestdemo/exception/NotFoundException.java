package com.example.apirestdemo.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String details) {
    super(details);
  }
}