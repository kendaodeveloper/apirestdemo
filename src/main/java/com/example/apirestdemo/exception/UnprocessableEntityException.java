package com.example.apirestdemo.exception;

public class UnprocessableEntityException extends RuntimeException {
  public UnprocessableEntityException(String details) {
    super(details);
  }
}