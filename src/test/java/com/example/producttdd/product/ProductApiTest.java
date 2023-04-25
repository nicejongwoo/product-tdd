package com.example.producttdd.product;

import com.example.producttdd.ApiTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.example.producttdd.product.ProductSteps.상품등록_요청;
import static com.example.producttdd.product.ProductSteps.상품등록_요청_생성;

public class ProductApiTest extends ApiTest {

    @Test
    void 상품등록_테스트() throws Exception {
        final var request = 상품등록_요청_생성();

        // API request
        final var response = 상품등록_요청(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }



}
