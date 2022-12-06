package com.example.apirestdemo.exception;

public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(String details) {
    super(details);
  }
}