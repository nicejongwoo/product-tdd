package com.example.producttdd.product;

import com.example.producttdd.ApiTest;
import com.example.producttdd.product.adapter.ProductRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.example.producttdd.product.ProductSteps.상품등록_요청;
import static com.example.producttdd.product.ProductSteps.상품등록_요청_생성;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends ApiTest {

    @Autowired
    private ProductRepository productRepository;

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

    @Test
    void 상품수정_테스트() throws Exception {
        ProductSteps.상품등록_요청(ProductSteps.상품등록_요청_생성());
        long productId = 1L;

        ExtractableResponse<Response> response = ProductSteps.상품수정_요청(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(productRepository.findById(productId).get().getName()).isEqualTo("상품 수정");
    }

}
