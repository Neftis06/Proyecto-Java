package com.axity.curso.retailservice.controller;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products") // Ruta base para este controlador
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Endpoint GET: http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Endpoint GET por ID: http://localhost:8080/api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Endpoint POST: http://localhost:8080/api/products
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        // Retornamos un código HTTP 201 (Created) indicando que el recurso se creó con éxito
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
}