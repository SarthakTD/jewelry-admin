package com.rokadeshwar.jewels.products.service;

import com.rokadeshwar.jewels.products.domain.PriceHistory;
import com.rokadeshwar.jewels.products.domain.Product;
import com.rokadeshwar.jewels.products.dto.ProductDto;
import com.rokadeshwar.jewels.products.mapper.ProductMapper;
import com.rokadeshwar.jewels.products.repo.PriceHistoryRepository;
import com.rokadeshwar.jewels.products.repo.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.NoSuchElementException;

@Service
public class ProductService {
  private final ProductRepository repo;
  private final PriceHistoryRepository historyRepo;
  private final ProductMapper mapper;

  public ProductService(ProductRepository repo, PriceHistoryRepository historyRepo, ProductMapper mapper) {
    this.repo = repo;
    this.historyRepo = historyRepo;
    this.mapper = mapper;
  }

  public Page<ProductDto> list(String q, Pageable pageable) {
    return repo.search(q, pageable).map(mapper::toDto);
  }

  public ProductDto get(Long id) {
    return mapper.toDto(getEntity(id));
  }

  public ProductDto updatePrice(Long id, BigDecimal newPrice, String adminEmail) {
    Product p = getEntity(id);
    var old = p.getPrice();
    p.setPrice(newPrice);
    p.setUpdatedAt(Instant.now());
    repo.save(p);

    PriceHistory h = new PriceHistory();
    h.setProductId(id);
    h.setOldPrice(old);
    h.setNewPrice(newPrice);
    h.setChangedBy(adminEmail);
    historyRepo.save(h);

    return mapper.toDto(p);
  }

  private Product getEntity(Long id) {
    return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
  }
}
