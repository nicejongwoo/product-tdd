package com.example.producttdd.product.application.service;

import com.example.producttdd.product.application.port.ProductPort;
import com.example.producttdd.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductService {
    private final ProductPort productPort;

    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Void> addProduct(@RequestBody AddProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long productId) {
        Product product = productPort.getProduct(productId);
        GetProductResponse response = new GetProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDiscountPolicy());
        return ResponseEntity.ok(response);
    }
}
