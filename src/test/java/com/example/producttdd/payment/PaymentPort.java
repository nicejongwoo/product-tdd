package com.example.producttdd.payment;

import com.example.producttdd.order.domain.Order;

interface PaymentPort {
    Order getOrder(Long orderId);

    void pay(Payment payment);

    void save(Payment payment);
}
