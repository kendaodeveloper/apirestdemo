package com.example.apirestdemo.service.product;

import com.example.apirestdemo.entity.base.PageableEntity;
import com.example.apirestdemo.entity.product.Product;
import com.example.apirestdemo.exception.NotFoundException;
import com.example.apirestdemo.storage.product.ProductStorage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
  public Product getById(Integer id) {
    return ProductStorage.products.stream()
        .filter(x -> x.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new NotFoundException("Product not found"));
  }

  public PageableEntity<Product> getByFilter(
      Integer id, String description, BigDecimal price, Integer pageNumber, Integer pageSize
  ) {
    List<Product> products = ProductStorage.products.stream()
        .filter(x -> (id == null || id.equals(x.getId())) &&
            (description == null || x.getDescription().toLowerCase().contains(description.toLowerCase())) &&
            (price == null || price.equals(x.getPrice()))
        ).collect(Collectors.toList());

    return new PageableEntity<>(products, pageNumber, pageSize).doFilter();
  }
}
