package com.example.apirestdemo.endpoint.product;

import com.example.apirestdemo.entity.base.PageableEntity;
import com.example.apirestdemo.entity.product.Product;
import com.example.apirestdemo.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/products")
@Tag(name = "Product Endpoint", description = "/products")
public class ProductEndpoint {
  private final ProductService productService;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Product By ID")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid parameters"),
          // @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Product not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public Product getById(@PathVariable @Parameter(name = "id", description = "id") Integer id) {
    return this.productService.getById(id);
  }

  @GetMapping
  @Operation(summary = "Get Product(s) By Filter")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "206", description = "Partial Content"),
          @ApiResponse(responseCode = "400", description = "Invalid parameters"),
          // @ApiResponse(responseCode = "401", description = "Unauthorized"),
          // @ApiResponse(responseCode = "404", description = "Product not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public ResponseEntity<PageableEntity<Product>> getByFilter(
      @RequestParam(required = false) @Parameter(name = "id", description = "id") Integer id,
      @RequestParam(required = false) @Parameter(name = "description", description = "description") String description,
      @RequestParam(required = false) @Parameter(name = "price", description = "price") BigDecimal price,
      @RequestParam(required = false, defaultValue = "1") @Parameter(name = "pageNumber", description = "page number") @Min(1) Integer pageNumber,
      @RequestParam(required = false, defaultValue = "10") @Parameter(name = "pageSize", description = "page size") @Min(1) Integer pageSize
  ) {
    PageableEntity<Product> products =
        this.productService.getByFilter(id, description, price, pageNumber, pageSize);

    return ResponseEntity
        .status(products.isShowingAll() ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT)
        .body(products);
  }
}
