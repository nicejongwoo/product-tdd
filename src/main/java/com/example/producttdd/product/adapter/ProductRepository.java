package com.example.producttdd.product.adapter;

import com.example.producttdd.product.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void save(Product product) {
        product.assigned(++sequence);
        persistence.put(product.getId(), product);
    }
}
