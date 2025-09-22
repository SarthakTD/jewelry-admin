package com.rokadeshwar.jewels.products.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ProductDto(
  Long id,
  String title,
  String baseType,
  BigDecimal price,
  String imageUrl,
  List<String> images,
  Map<String, String> specs,
  List<Map<String, String>> highlights,
  Boolean bestSeller
) {}
