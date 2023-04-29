package com.example.producttdd.order;

import org.springframework.util.Assert;

record CreateOrderRequest(Long productId, int quantity) {
    CreateOrderRequest(Long productId, int quantity) {
        Assert.notNull(productId, "상품 ID는 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
        this.productId = productId;
        this.quantity = quantity;
    }

}
