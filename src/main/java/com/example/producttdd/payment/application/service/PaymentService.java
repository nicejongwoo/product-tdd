package com.example.producttdd.payment.application.service;

import com.example.producttdd.order.domain.Order;
import com.example.producttdd.payment.application.port.PaymentPort;
import com.example.producttdd.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    private final PaymentPort paymentPort;

    public PaymentService(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    public void payment(PaymentRequest request) {
        Order order = paymentPort.getOrder(request.orderId());

        Payment payment = new Payment(order, request.cardNumber());
        paymentPort.pay(payment.getPrice(), payment.getCardNumber());
        paymentPort.save(payment);
    }
}
