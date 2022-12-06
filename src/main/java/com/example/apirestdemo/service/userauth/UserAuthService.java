package com.example.apirestdemo.service.userauth;

import com.example.apirestdemo.endpoint.userauth.request.UserAuthRequest;
import com.example.apirestdemo.entity.user.UserAuth;
import com.example.apirestdemo.exception.UnauthorizedException;
import com.example.apirestdemo.storage.userauth.UserAuthStorage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAuthService {
  public UserAuth auth(UserAuthRequest request) {
    if (!request.isValidCredentials()) {
      throw new UnauthorizedException("Invalid username/password");
    }

    UUID validToken = UUID.randomUUID();

    UserAuthStorage.setValidToken(validToken);

    return new UserAuth(null, null, validToken);
  }
}
