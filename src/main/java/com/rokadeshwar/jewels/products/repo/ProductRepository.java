package com.rokadeshwar.jewels.products.repo;

import com.rokadeshwar.jewels.products.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("""
    select p from Product p
    where (:q is null or :q = '' or lower(p.title) like lower(concat('%', :q, '%')))
  """)
  Page<Product> search(String q, Pageable pageable);
}
