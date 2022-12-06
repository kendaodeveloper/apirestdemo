package com.example.apirestdemo.endpoint.advice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponseError {
  private Integer code;
  private String message;
  private String details;
}
