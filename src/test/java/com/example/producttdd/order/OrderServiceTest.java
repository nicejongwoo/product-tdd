package com.example.producttdd.order;

import com.example.producttdd.product.ProductSteps;
import com.example.producttdd.product.application.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    void 상품주문_테스트() throws Exception {
        productService.addProduct(ProductSteps.상품등록_요청_생성());

        CreateOrderRequest request = 상품주문_요청_생성();
        orderService.createOrder(request);
    }

    private CreateOrderRequest 상품주문_요청_생성() {
        Long productId = 1L;
        int quantity = 2;
        return new CreateOrderRequest(productId, quantity);
    }

}
