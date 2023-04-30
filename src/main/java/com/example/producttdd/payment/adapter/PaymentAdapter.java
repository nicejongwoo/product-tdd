package com.example.producttdd.payment.adapter;

import com.example.producttdd.order.domain.Order;
import com.example.producttdd.payment.adapter.PaymentRepository;
import com.example.producttdd.payment.application.port.PaymentPort;
import com.example.producttdd.payment.domain.Payment;
import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class PaymentAdapter implements PaymentPort {
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    public PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Order getOrder(Long orderId) {
        return new Order(new Product("상품명", 10000, DiscountPolicy.NONE), 2);
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
