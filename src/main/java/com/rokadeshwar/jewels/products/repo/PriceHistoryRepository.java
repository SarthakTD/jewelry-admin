package com.rokadeshwar.jewels.products.repo;

import com.rokadeshwar.jewels.products.domain.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {}
