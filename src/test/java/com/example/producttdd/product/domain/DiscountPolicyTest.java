package com.example.producttdd.product.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountPolicyTest {

    @Test
    void NONE_discount_policy_test() throws Exception {
        int price = 1000;
        int discountedPrice = DiscountPolicy.NONE.applyDiscount(price);
        Assertions.assertThat(discountedPrice).isEqualTo(price);
    }

    @Test
    void FIX_1000_AMOUNT_discount_policy_test() throws Exception {
        int price = 2000;
        int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
        Assertions.assertThat(discountedPrice).isEqualTo(1000);
    }

    @Test
    void over_discounted_test() throws Exception {
        int price = 500;
        int discountedPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price);
        Assertions.assertThat(discountedPrice).isEqualTo(0);
    }

}