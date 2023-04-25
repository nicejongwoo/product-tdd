package com.example.producttdd.product.application.service;

import com.example.producttdd.product.domain.DiscountPolicy;
import org.springframework.util.Assert;

public record GetProductResponse(
        Long id,
        String name,
        int price,
        DiscountPolicy discountPolicy
) {
    public GetProductResponse {
        Assert.notNull(id, "상품 ID는 필수입니다.");
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.notNull(discountPolicy, "할인정책은 필수입니다.");
    }
}
