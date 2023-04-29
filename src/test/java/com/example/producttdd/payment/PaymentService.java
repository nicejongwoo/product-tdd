package com.example.producttdd.payment;

import com.example.producttdd.order.domain.Order;

class PaymentService {
    private final PaymentPort paymentPort;

    PaymentService(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    public void payment(PaymentRequest request) {
        Order order = paymentPort.getOrder(request.orderId());

        Payment payment = new Payment(order, request.cardNumber());
        paymentPort.pay(payment);
        paymentPort.save(payment);
    }
}
