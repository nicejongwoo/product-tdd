package com.example.producttdd.product;

import com.example.producttdd.product.application.service.GetProductResponse;
import com.example.producttdd.product.application.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품조회_테스트() throws Exception {
        productService.addProduct(ProductSteps.상품등록_요청_생성());
        Long productId = 1L;

        final GetProductResponse response = productService.getProduct(productId);

        Assertions.assertThat(response).isNotNull();

    }

}
