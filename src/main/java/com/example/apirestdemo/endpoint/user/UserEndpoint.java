package com.example.apirestdemo.endpoint.user;

import com.example.apirestdemo.endpoint.user.request.WriteUserRequest;
import com.example.apirestdemo.entity.base.PageableEntity;
import com.example.apirestdemo.entity.user.User;
import com.example.apirestdemo.service.user.UserService;
import com.example.apirestdemo.storage.userauth.UserAuthStorage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/users")
@Tag(name = "User Endpoint", description = "/users")
public class UserEndpoint {
  private final HttpServletRequest httpServletRequest;
  private final UserService userService;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get User By ID")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid parameters"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public User getById(
      @PathVariable @Parameter(name = "id", description = "id") Integer id
  ) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    return this.userService.getById(id);
  }

  @GetMapping
  @Operation(summary = "Get User(s) By Filter")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "206", description = "Partial Content"),
          @ApiResponse(responseCode = "400", description = "Invalid parameters"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          // @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public ResponseEntity<PageableEntity<User>> getByFilter(
      @RequestParam(required = false) @Parameter(name = "id", description = "id") Integer id,
      @RequestParam(required = false) @Parameter(name = "name", description = "name") String name,
      @RequestParam(required = false) @Parameter(name = "cpf", description = "cpf") String cpf,
      @RequestParam(required = false) @Parameter(name = "email", description = "email") String email,
      @RequestParam(required = false) @Parameter(name = "birthDate", description = "birth date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
      @RequestParam(required = false, defaultValue = "1") @Parameter(name = "pageNumber", description = "page number") @Min(1) Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") @Parameter(name = "pageSize", description = "page size") @Min(1) Integer pageSize
  ) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    PageableEntity<User> users =
        this.userService.getByFilter(id, name, cpf, email, birthDate, pageNumber, pageSize);

    return ResponseEntity
        .status(users.isShowingAll() ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT)
        .body(users);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create User")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Created"),
          @ApiResponse(responseCode = "400", description = "Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "409", description = "User already exists by CPF/Email"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public User create(@RequestBody @Valid WriteUserRequest request) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    return this.userService.create(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Update User")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid Parameters | Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "409", description = "Another user already exists by CPF/Email"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public User update(
      @PathVariable @Parameter(name = "id", description = "id") Integer id,
      @RequestBody @Valid WriteUserRequest request
  ) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    return this.userService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete User")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "No Content"),
          @ApiResponse(responseCode = "400", description = "Invalid Parameters"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "User not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public void delete(
      @PathVariable @Parameter(name = "id", description = "id") Integer id
  ) {
    UserAuthStorage.validateToken(this.httpServletRequest.getHeader("Authorization"));

    this.userService.delete(id);
  }
}
