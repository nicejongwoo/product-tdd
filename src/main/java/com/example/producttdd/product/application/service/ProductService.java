package com.example.producttdd.product.application.service;

import com.example.producttdd.product.application.port.ProductPort;
import com.example.producttdd.product.domain.Product;

public class ProductService {
    private final ProductPort productPort;

    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }


    public void addProduct(AddProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
    }
}
