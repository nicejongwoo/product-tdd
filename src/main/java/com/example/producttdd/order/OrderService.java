package com.example.producttdd.order;

import com.example.producttdd.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
class OrderService {
    private final OrderPort orderPort;

    OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public void createOrder(CreateOrderRequest request) {
        Product product = orderPort.getProduct(request.productId());
        final Order order = new Order(product, request.quantity());
        orderPort.save(order);
    }
}
