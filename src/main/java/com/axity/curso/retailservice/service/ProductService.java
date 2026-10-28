package com.axity.curso.retailservice.service;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.mapper.ProductMapper;
import com.axity.curso.retailservice.model.Product;
import com.axity.curso.retailservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    // Las dependencias deben ser 'final' para que Lombok genere el constructor
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        // Usamos la API Stream que acabas de dominar para transformar la lista completa
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return productMapper.toDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        // 1. Convertimos el DTO que llega a Entidad
        Product product = productMapper.toEntity(productDTO);
        
        // 2. Guardamos en base de datos
        Product savedProduct = productRepository.save(product);
        
        // 3. Devolvemos la Entidad guardada convertida de nuevo a DTO
        return productMapper.toDTO(savedProduct);
    }
}