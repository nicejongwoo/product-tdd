package com.example.producttdd.payment.application.port;

import com.example.producttdd.order.domain.Order;
import com.example.producttdd.payment.domain.Payment;

public interface PaymentPort {
    Order getOrder(Long orderId);

    void save(Payment payment);

    void pay(int price, String cardNumber);
}
