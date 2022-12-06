package com.example.apirestdemo.endpoint.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteUserRequest {
  @NotNull
  @NotBlank
  @Schema(required = true, description = "Name", example = "Kendao")
  private String name;
  @NotNull
  @NotBlank
  @Schema(required = true, description = "CPF", example = "12312312312")
  private String cpf;
  @NotNull
  @NotBlank
  @Schema(required = true, description = "E-mail", example = "kendaodeveloper@gmail.com")
  private String email;
  @NotNull
  @Schema(required = true, description = "Birth Date", example = "1996-06-04")
  private LocalDate birthDate;
}
