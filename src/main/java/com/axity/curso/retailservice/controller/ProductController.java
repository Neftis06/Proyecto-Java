package com.axity.curso.retailservice.controller;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // --- NUEVO: ACTUALIZAR (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct); // Retorna 200 OK
    }

    // --- NUEVO: BORRADO LÓGICO (SOFT DELETE) ---
    @DeleteMapping("/{id}/soft")
    public ResponseEntity<Void> softDeleteProduct(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    // --- NUEVO: BORRADO FÍSICO (HARD DELETE) ---
    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDeleteProduct(@PathVariable Long id) {
        productService.hardDeleteProduct(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

    // --- NUEVO: ACTUALIZACIÓN PARCIAL (PATCH) ---
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> patchProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.patchProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }
}