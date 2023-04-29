package com.example.producttdd.order.application.port;

import com.example.producttdd.order.domain.Order;
import com.example.producttdd.product.domain.Product;

public interface OrderPort {
    Product getProduct(Long productId);

    void save(Order order);
}
