package com.example.producttdd.product;

import com.example.producttdd.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.example.producttdd.product.ProductSteps.상품등록_요청;
import static com.example.producttdd.product.ProductSteps.상품등록_요청_생성;
import static org.assertj.core.api.Assertions.*;

public class ProductApiTest extends ApiTest {

    @Test
    void 상품등록_테스트() throws Exception {
        final var request = 상품등록_요청_생성();

        // API request
        final var response = 상품등록_요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void 상품조회_테스트() throws Exception {
        ProductSteps.상품등록_요청(ProductSteps.상품등록_요청_생성());
        Long productId = 1L;

        ExtractableResponse<Response> response = ProductSteps.상품조회_요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }

}
