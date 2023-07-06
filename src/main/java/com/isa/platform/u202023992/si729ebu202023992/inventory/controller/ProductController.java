package com.isa.platform.u202023992.si729ebu202023992.inventory.controller;

import com.isa.platform.u202023992.si729ebu202023992.inventory.dto.ProductRequestDto;
import com.isa.platform.u202023992.si729ebu202023992.inventory.model.Product;
import com.isa.platform.u202023992.si729ebu202023992.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    @GetMapping("/products")
    public ResponseEntity<Product> getProductById(@RequestParam(name = "product_id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Transactional
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return new ResponseEntity<>(productService.createProduct(productRequestDto), HttpStatus.CREATED);
    }
}
