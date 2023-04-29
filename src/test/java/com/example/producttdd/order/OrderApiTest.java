package com.example.producttdd.order;

import com.example.producttdd.ApiTest;
import com.example.producttdd.product.ProductSteps;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class OrderApiTest extends ApiTest {

    @Test
    void 상품주문_테스트() throws Exception {

        ProductSteps.상품등록_요청(ProductSteps.상품등록_요청_생성());

        CreateOrderRequest request = 상품주문_요청_생성();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/orders")
                .then()
                .log().all()
                .extract();

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


    private CreateOrderRequest 상품주문_요청_생성() {
        Long productId = 1L;
        int quantity = 2;
        return new CreateOrderRequest(productId, quantity);
    }

}
