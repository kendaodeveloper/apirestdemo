package com.example.apirestdemo.storage.userauth;

import com.example.apirestdemo.exception.UnauthorizedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAuthStorage {
  private static UUID validToken = UUID.randomUUID();

  public static void setValidToken(UUID validToken) {
    UserAuthStorage.validToken = validToken;
  }

  public static void validateToken(String token) {
    if (!UserAuthStorage.validToken.toString().equals(token)) {
      throw new UnauthorizedException("Token is empty/invalid");
    }
  }
}
