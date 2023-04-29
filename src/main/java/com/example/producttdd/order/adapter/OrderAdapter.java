package com.example.producttdd.order.adapter;

import com.example.producttdd.order.application.port.OrderPort;
import com.example.producttdd.order.domain.Order;
import com.example.producttdd.product.adapter.ProductRepository;
import com.example.producttdd.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
class OrderAdapter implements OrderPort {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderAdapter(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 없습니다."));
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
