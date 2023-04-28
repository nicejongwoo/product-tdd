package com.example.producttdd.order;

import com.example.producttdd.product.adapter.ProductRepository;
import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

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
        Long productId = 1L;
        int quantity = 2;
        CreateOrderRequest request = new CreateOrderRequest(productId, quantity);
        orderService.createOrder(request);
    }

    private class OrderService {
        private final OrderPort orderPort;

        private OrderService(OrderPort orderPort) {
            this.orderPort = orderPort;
        }

        public void createOrder(CreateOrderRequest request) {
            Product product = orderPort.getProduct(request.productId());
            final Order order = new Order(product, request.quantity());
            orderPort.save(order);
        }
    }

    private record CreateOrderRequest(Long productId, int quantity) {
        private CreateOrderRequest(Long productId, int quantity) {
            Assert.notNull(productId, "상품 ID는 필수입니다.");
            Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
            this.productId = productId;
            this.quantity = quantity;
        }

    }

    private class OrderAdapter implements OrderPort{
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

    private class Order {
        private Long id;
        private final Product product;
        private final int quantity;

        public Order(Product product, int quantity) {
            Assert.notNull(product, "상품은 필수입니다.");
            Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
            this.product = product;
            this.quantity = quantity;
        }

        public void assignId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private class OrderRepository {

        private Map<Long, Order> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(Order order) {
            order.assignId(++sequence);
            persistence.put(order.getId(), order);
        }
    }

    private interface OrderPort {
        Product getProduct(Long productId);

        void save(Order order);
    }
}