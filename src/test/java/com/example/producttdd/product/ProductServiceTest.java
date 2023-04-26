package com.example.producttdd.product;

import com.example.producttdd.product.application.service.GetProductResponse;
import com.example.producttdd.product.application.service.ProductService;
import com.example.producttdd.product.application.service.UpdateProductRequest;
import com.example.producttdd.product.domain.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품수정_테스트() throws Exception {
        productService.addProduct(ProductSteps.상품등록_요청_생성());
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품 수정", 2000, DiscountPolicy.NONE);

        productService.updateProduct(productId, request);

        ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
        GetProductResponse body = response.getBody();

        Assertions.assertThat(body.name()).isEqualTo("상품 수정");
        Assertions.assertThat(body.price()).isEqualTo(2000);
    }

}
