package com.example.producttdd.order;

import com.example.producttdd.ApiTest;
import com.example.producttdd.product.ProductSteps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class OrderApiTest extends ApiTest {

    @Test
    void 상품주문_테스트() throws Exception {

        ProductSteps.상품등록_요청(ProductSteps.상품등록_요청_생성());

        var request = OrderSteps.상품주문_요청_생성();

        var response = OrderSteps.상품주문_요청(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}
