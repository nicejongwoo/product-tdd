package com.example.producttdd.payment;

import com.example.producttdd.order.domain.Order;

interface PaymentPort {
    Order getOrder(Long orderId);

    void save(Payment payment);

    void pay(int price, String cardNumber);
}
