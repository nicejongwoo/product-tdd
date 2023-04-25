package com.example.producttdd.product;

import java.util.HashMap;
import java.util.Map;

class ProductRepository {
    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void save(Product product) {
        product.assigned(++sequence);
        persistence.put(product.getId(), product);
    }
}
