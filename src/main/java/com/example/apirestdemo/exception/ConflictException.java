package com.example.apirestdemo.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String details) {
    super(details);
  }
}