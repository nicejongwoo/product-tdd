package com.example.producttdd.payment;

interface PaymentGateway {
    void execute(int totalPrice, String cardNumber);
}
