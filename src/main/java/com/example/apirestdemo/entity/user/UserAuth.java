package com.example.apirestdemo.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuth {
  @JsonIgnore
  private String username;
  @JsonIgnore
  private String password;
  private UUID token;
}
