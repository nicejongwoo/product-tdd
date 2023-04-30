package com.example.producttdd.payment.adapter;

import com.example.producttdd.payment.domain.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    Map<Long, Payment> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(Payment payment) {
        payment.assignId(++sequence);
        persistence.put(payment.getId(), payment);
    }
}
