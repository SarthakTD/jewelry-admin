package com.rokadeshwar.jewels.products.web;

import com.rokadeshwar.jewels.products.dto.ProductDto;
import com.rokadeshwar.jewels.products.service.ProductService;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public Page<ProductDto> list(@RequestParam(required = false) String q, Pageable pageable) {
    return service.list(q, pageable);
  }

  @GetMapping("/{id}")
  public ProductDto get(@PathVariable Long id) {
    return service.get(id);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}/price")
  public ProductDto updatePrice(@PathVariable Long id,
                                @RequestBody Map<String, @Min(0) BigDecimal> body,
                                Authentication auth) {
    BigDecimal price = body.get("price");
    if (price == null) throw new IllegalArgumentException("price is required");
    String admin = (auth != null) ? auth.getName() : "admin";
    return service.updatePrice(id, price, admin);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String,String> notFound(NoSuchElementException e) {
    return Map.of("error", e.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String,String> bad(IllegalArgumentException e) {
    return Map.of("error", e.getMessage());
  }
}
