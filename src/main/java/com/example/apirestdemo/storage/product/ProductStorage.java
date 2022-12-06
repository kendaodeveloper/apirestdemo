package com.example.apirestdemo.storage.product;

import com.example.apirestdemo.entity.product.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductStorage {
  public static ArrayList<Product> products = new ArrayList<>() {{
    super.addAll(List.of(
        new Product(
            1,
            "Notebook",
            new BigDecimal(1200)
        ),
        new Product(
            2,
            "Mouse",
            new BigDecimal(250)
        ),
        new Product(
            3,
            "Teclado",
            new BigDecimal("400.50")
        ),
        new Product(
            4,
            "TV",
            new BigDecimal(2500)
        ),
        new Product(
            5,
            "Celular",
            new BigDecimal(1800)
        )
    ));
  }};
}
