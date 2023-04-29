package com.example.producttdd.order;

import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderPort orderPort;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        orderPort = new OrderPort() {
            @Override
            public Product getProduct(Long productId) {
                return new Product("상품명", 10000, DiscountPolicy.NONE);
            }

            @Override
            public void save(Order order) {
                orderRepository.save(order);
            }
        };
        orderService = new OrderService(orderPort);
    }

    @Test
    void 상품주문_테스트() throws Exception {
        CreateOrderRequest request = 상품주문_요청_생성();
        orderService.createOrder(request);
    }

    private CreateOrderRequest 상품주문_요청_생성() {
        Long productId = 1L;
        int quantity = 2;
        return new CreateOrderRequest(productId, quantity);
    }

}
