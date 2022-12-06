package com.example.apirestdemo.endpoint.userauth;

import com.example.apirestdemo.endpoint.userauth.request.UserAuthRequest;
import com.example.apirestdemo.entity.user.UserAuth;
import com.example.apirestdemo.service.userauth.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/users")
@Tag(name = "User Auth Endpoint", description = "/users/auth")
public class UserAuthEndpoint {
  private final UserAuthService userAuthService;

  @PostMapping("/auth")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Authenticate User")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public UserAuth auth(@RequestBody @Valid UserAuthRequest request) {
    return this.userAuthService.auth(request);
  }
}
