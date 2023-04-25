package com.example.producttdd.product;

import com.example.producttdd.product.application.service.AddProductRequest;
import com.example.producttdd.product.application.service.ProductService;
import com.example.producttdd.product.domain.DiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품등록_테스트() throws Exception {
        final AddProductRequest request = 상품등록_요청_생성();
        productService.addProduct(request);
    }

    private AddProductRequest 상품등록_요청_생성() {
        String name = "상품명";
        int price = 1000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }

}
