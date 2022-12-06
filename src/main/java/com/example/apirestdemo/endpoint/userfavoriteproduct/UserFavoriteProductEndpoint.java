package com.example.apirestdemo.endpoint.userfavoriteproduct;

import com.example.apirestdemo.entity.user.User;
import com.example.apirestdemo.service.userfavoriteproduct.UserFavoriteProductService;
import com.example.apirestdemo.storage.userauth.UserAuthStorage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/users")
@Tag(name = "User Favorite Product Endpoint", description = "/users/favoriteproducts")
public class UserFavoriteProductEndpoint {
  private final HttpServletRequest httpServletRequest;
  private final UserFavoriteProductService userFavoriteProductService;

  @PostMapping("/{userId}/favoriteproducts/{productId}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Add User Favorite Product")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Created"),
          @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "User not found | Product not found"),
          @ApiResponse(responseCode = "409", description = "Favorite Product already been added"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public User addFavoriteProduct(
      @PathVariable @Parameter(name = "userId", description = "user id") Integer userId,
      @PathVariable @Parameter(name = "productId", description = "product id") Integer productId
  ) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    return this.userFavoriteProductService.addFavoriteProduct(userId, productId);
  }

  @DeleteMapping("/{userId}/favoriteproducts/{productId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Remove User Favorite Product")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "No Content"),
          @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "User not found | Favorite Product not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public void removeFavoriteProduct(
      @PathVariable @Parameter(name = "userId", description = "user id") Integer userId,
      @PathVariable @Parameter(name = "productId", description = "product id") Integer productId
  ) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    this.userFavoriteProductService.removeFavoriteProduct(userId, productId);
  }
}
