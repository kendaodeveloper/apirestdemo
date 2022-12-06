package com.example.apirestdemo.storage.user;

import com.example.apirestdemo.entity.user.User;
import com.example.apirestdemo.storage.product.ProductStorage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserStorage {
  public static ArrayList<User> users = new ArrayList<>() {{
    super.addAll(List.of(
        new User(
            1,
            "João",
            "12312312312",
            "joao@gmail.com",
            LocalDate.of(1991, 10, 5),
            new ArrayList<>() {{
              super.add(
                  ProductStorage.products.get(0)
              );
            }}
        ),
        new User(
            2,
            "Maria",
            "12345678912",
            "maria@gmail.com",
            LocalDate.of(1992, 2, 1),
            new ArrayList<>() {{
              super.addAll(List.of(
                  ProductStorage.products.get(1),
                  ProductStorage.products.get(2)
              ));
            }}
        ),
        new User(
            3,
            "José",
            "11122233345",
            "jose@gmail.com",
            LocalDate.of(1998, 12, 20),
            new ArrayList<>()
        ),
        new User(
            4,
            "Ricardo",
            "00100100101",
            "ricardo@gmail.com",
            LocalDate.of(1980, 8, 15),
            new ArrayList<>() {{
              super.add(
                  ProductStorage.products.get(3)
              );
            }}
        ),
        new User(
            5,
            "Joana",
            "10010010010",
            "joana@gmail.com",
            LocalDate.of(1985, 8, 12),
            new ArrayList<>() {{
              super.add(
                  ProductStorage.products.get(4)
              );
            }}
        )
    ));
  }};
}
