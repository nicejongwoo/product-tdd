package com.example.producttdd.payment.adapter;

import com.example.producttdd.order.adapter.OrderRepository;
import com.example.producttdd.order.domain.Order;
import com.example.producttdd.payment.application.port.PaymentPort;
import com.example.producttdd.payment.domain.Payment;
import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class PaymentAdapter implements PaymentPort {
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void pay(int totalPrice, String cardNumber) {
        paymentGateway.execute(totalPrice, cardNumber);
    }
}
