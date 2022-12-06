package com.example.apirestdemo.service.userfavoriteproduct;

import com.example.apirestdemo.entity.product.Product;
import com.example.apirestdemo.entity.user.User;
import com.example.apirestdemo.exception.ConflictException;
import com.example.apirestdemo.exception.NotFoundException;
import com.example.apirestdemo.storage.product.ProductStorage;
import com.example.apirestdemo.storage.user.UserStorage;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteProductService {
  public User addFavoriteProduct(Integer userId, Integer productId) {
    User user = UserStorage.users.stream()
        .filter(x -> x.getId().equals(userId))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User not found"));

    Product existingProduct = user.getFavoriteProducts().stream()
        .filter(x -> x.getId().equals(productId))
        .findFirst()
        .orElse(null);

    if (existingProduct != null) {
      throw new ConflictException("Favorite Product already been added");
    }

    Product product = ProductStorage.products.stream()
        .filter(x -> x.getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("Product not found"));

    user.getFavoriteProducts().add(product);

    return user;
  }

  public void removeFavoriteProduct(Integer userId, Integer productId) {
    User user = UserStorage.users.stream()
        .filter(x -> x.getId().equals(userId))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("User not found"));

    Product product = user.getFavoriteProducts().stream()
        .filter(x -> x.getId().equals(productId))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("Favorite Product not found"));

    user.getFavoriteProducts().remove(product);
  }
}
