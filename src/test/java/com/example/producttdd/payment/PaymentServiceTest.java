package com.example.producttdd.payment;

import com.example.producttdd.order.OrderSteps;
import com.example.producttdd.order.application.service.OrderService;
import com.example.producttdd.payment.application.service.PaymentRequest;
import com.example.producttdd.payment.application.service.PaymentService;
import com.example.producttdd.product.ProductSteps;
import com.example.producttdd.product.application.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Test
    void 상품주문_테스트() throws Exception {
        productService.addProduct(ProductSteps.상품등록_요청_생성());
        orderService.createOrder(OrderSteps.상품주문_요청_생성());
        PaymentRequest request = PaymentSteps.주문결제_요청_생성();

        paymentService.payment(request);
    }

}
