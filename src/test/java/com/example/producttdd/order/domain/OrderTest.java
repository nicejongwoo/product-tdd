package com.example.producttdd.order.domain;

import com.example.producttdd.product.domain.DiscountPolicy;
import com.example.producttdd.product.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getTotalPrice_test() throws Exception {
        Order order = new Order(new Product("상품명", 1000, DiscountPolicy.NONE), 2);

        int totalPrice = order.getTotalPrice();

        Assertions.assertThat(totalPrice).isEqualTo(2000);
    }

}