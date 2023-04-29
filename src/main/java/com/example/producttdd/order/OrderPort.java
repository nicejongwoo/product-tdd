package com.example.producttdd.order;

import com.example.producttdd.product.domain.Product;

interface OrderPort {
    Product getProduct(Long productId);

    void save(Order order);
}
