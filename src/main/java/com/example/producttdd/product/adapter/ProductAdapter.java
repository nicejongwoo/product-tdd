package com.example.producttdd.product.adapter;

import com.example.producttdd.product.application.port.ProductPort;
import com.example.producttdd.product.domain.Product;

public class ProductAdapter implements ProductPort {
    private final ProductRepository productRepository;

    public ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
