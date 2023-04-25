package com.example.producttdd.product;

import com.example.producttdd.ApiTest;
import com.example.producttdd.product.application.service.AddProductRequest;
import com.example.producttdd.product.domain.DiscountPolicy;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ProductServiceTest extends ApiTest {

    @Test
    void 상품등록_테스트() throws Exception {
        final var request = 상품등록_요청_생성();

        // API request
        final var response = 상품등록_요청(request);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> 상품등록_요청(AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log()
                .all()
                .extract();
    }

    private AddProductRequest 상품등록_요청_생성() {
        String name = "상품명";
        int price = 1000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }

}
