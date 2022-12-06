package com.example.apirestdemo.endpoint.userauth.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthRequest {
  @NotNull
  @NotBlank
  @Schema(required = true, description = "Username", example = "test")
  private String username;

  @NotNull
  @NotBlank
  @Schema(required = true, description = "Password", example = "123")
  private String password;

  @JsonIgnore
  public boolean isValidCredentials() {
    return (/* this.username.equals("test") && */ this.password.equals("123"));
  }
}
