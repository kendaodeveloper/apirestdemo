package com.example.apirestdemo.entity.user;

import com.example.apirestdemo.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  private Integer id;
  private String name;
  private String cpf;
  private String email;
  private LocalDate birthDate;

  private ArrayList<Product> favoriteProducts;
}
