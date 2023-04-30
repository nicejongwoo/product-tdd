package com.example.producttdd.payment;

import com.example.producttdd.payment.application.service.PaymentRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class PaymentSteps {
    public static PaymentRequest 주문결제_요청_생성() {
        String cardNumber = "1234-1234-1234-1234";
        Long orderId = 1L;
        return new PaymentRequest(orderId, cardNumber);
    }

    public static ExtractableResponse<Response> 주문결제_요청(PaymentRequest request) {
        return RestAssured.given()
                .log()
                .all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/payments")
                .then()
                .log().all().extract();
    }
}
