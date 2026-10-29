package com.axity.curso.retailservice.service;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.exception.ResourceNotFoundException;
import com.axity.curso.retailservice.mapper.ProductMapper;
import com.axity.curso.retailservice.model.Product;
import com.axity.curso.retailservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    // Las dependencias deben ser 'final' para que Lombok genere el constructor
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final NotificationService notificationService;

    public List<ProductDTO> getAllProducts() {
        // Usamos la API Stream que acabas de dominar para transformar la lista completa
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado o está eliminado con ID: " + id));
        return productMapper.toDTO(product);
    }

    // --- POST (Crear Producto) ---
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Product savedProduct = productRepository.save(product);

        // Tarea asíncrona: Notificar creación de producto en segundo plano
        notificationService.notifyProductActionAsync("CREATE", savedProduct.getName());

        return productMapper.toDTO(savedProduct);
    }

    // --- PUT (Actualización Completa) ---
    public ProductDTO updateProduct(Long id, ProductDTO productDetails) {
        Product existingProduct = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(BigDecimal.valueOf(productDetails.getPrice()));
        existingProduct.setStock(productDetails.getStock());
        existingProduct.setCategory(productDetails.getCategory());

        Product updatedProduct = productRepository.save(existingProduct);

        // Tarea asíncrona: Auditoría de actualización completa
        notificationService.notifyProductActionAsync("PUT_UPDATE", updatedProduct.getName());

        return productMapper.toDTO(updatedProduct);
    }

    // --- PATCH (Actualización Parcial) ---
    public ProductDTO patchProduct(Long id, ProductDTO productDetails) {
        Product existingProduct = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado o está eliminado con ID: " + id));

        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null) {
            existingProduct.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null) {
            existingProduct.setPrice(BigDecimal.valueOf(productDetails.getPrice()));
        }
        if (productDetails.getStock() != null) {
            existingProduct.setStock(productDetails.getStock());
        }
        if (productDetails.getCategory() != null) {
            existingProduct.setCategory(productDetails.getCategory());
        }

        Product updatedProduct = productRepository.save(updatedProduct = productRepository.save(existingProduct));

        // Tarea asíncrona: Auditoría de actualización parcial
        notificationService.notifyProductActionAsync("PATCH_UPDATE", updatedProduct.getName());

        return productMapper.toDTO(updatedProduct);
    }

    // --- DELETE (Borrado Lógico) ---
    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        product.setDeleted(true);
        productRepository.save(product);

        // Tarea asíncrona: Notificar baja lógica
        notificationService.notifyProductActionAsync("SOFT_DELETE", product.getName());
    }

    // --- DELETE (Borrado Físico) ---
    public void hardDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        productRepository.deleteById(id);

        // Tarea asíncrona: Notificar purga definitiva
        notificationService.notifyProductActionAsync("HARD_DELETE_PURGE", product.getName());
    }
}