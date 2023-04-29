package com.example.producttdd.payment;

import com.example.producttdd.payment.PaymentRequest;

public class PaymentSteps {
    public static PaymentRequest 주문결제_요청_생성() {
        String cardNumber = "1234-1234-1234-1234";
        Long orderId = 1L;
        return new PaymentRequest(orderId, cardNumber);
    }
}
