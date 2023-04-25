package com.example.producttdd.product;

import com.example.producttdd.product.adapter.ProductAdapter;
import com.example.producttdd.product.adapter.ProductRepository;
import com.example.producttdd.product.application.port.ProductPort;
import com.example.producttdd.product.application.service.AddProductRequest;
import com.example.producttdd.product.application.service.ProductService;
import com.example.producttdd.product.domain.DiscountPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(productPort);
    }

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
