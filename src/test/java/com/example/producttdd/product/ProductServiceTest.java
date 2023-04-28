package com.example.producttdd.product;

import com.example.producttdd.product.application.service.GetProductResponse;
import com.example.producttdd.product.application.service.ProductService;
import com.example.producttdd.product.application.service.UpdateProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static com.example.producttdd.product.ProductSteps.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품수정_테스트() throws Exception {
        productService.addProduct(상품등록_요청_생성());
        final Long productId = 1L;
        final UpdateProductRequest request = 상품수정_요청_생성();

        productService.updateProduct(productId, request);

        ResponseEntity<GetProductResponse> response = productService.getProduct(productId);
        GetProductResponse body = response.getBody();

        Assertions.assertThat(body.name()).isEqualTo("상품 수정");
        Assertions.assertThat(body.price()).isEqualTo(2000);
    }

}
