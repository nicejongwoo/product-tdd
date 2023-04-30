package com.example.producttdd.payment;

import com.example.producttdd.order.domain.Order;
import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;

public class PaymentAdapter implements PaymentPort {
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
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
