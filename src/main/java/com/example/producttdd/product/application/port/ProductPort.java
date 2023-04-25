package com.example.producttdd.product.application.port;

import com.example.producttdd.product.domain.Product;

public interface ProductPort {
    void save(Product product);
}
