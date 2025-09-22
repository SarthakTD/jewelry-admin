package com.rokadeshwar.jewels.products.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "price_history")
public class PriceHistory {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long productId;
  private BigDecimal oldPrice;
  private BigDecimal newPrice;
  private String changedBy;
  private Instant changedAt = Instant.now();

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }
  public BigDecimal getOldPrice() { return oldPrice; }
  public void setOldPrice(BigDecimal oldPrice) { this.oldPrice = oldPrice; }
  public BigDecimal getNewPrice() { return newPrice; }
  public void setNewPrice(BigDecimal newPrice) { this.newPrice = newPrice; }
  public String getChangedBy() { return changedBy; }
  public void setChangedBy(String changedBy) { this.changedBy = changedBy; }
  public Instant getChangedAt() { return changedAt; }
  public void setChangedAt(Instant changedAt) { this.changedAt = changedAt; }
}
