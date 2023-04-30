package com.example.producttdd.payment.adapter;

public interface PaymentGateway {
    void execute(int totalPrice, String cardNumber);
}
