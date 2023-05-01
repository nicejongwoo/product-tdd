package com.example.producttdd.payment;

import com.example.producttdd.ApiTest;
import com.example.producttdd.order.OrderSteps;
import com.example.producttdd.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentApiTest extends ApiTest {

    @Test
    void 상품주문_테스트() throws Exception {
        ProductSteps.상품등록_요청(ProductSteps.상품등록_요청_생성());
        OrderSteps.상품주문_요청(OrderSteps.상품주문_요청_생성());
        var request = PaymentSteps.주문결제_요청_생성();

        var response = PaymentSteps.주문결제_요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
