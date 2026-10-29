package com.axity.curso.retailservice.service;

import com.axity.curso.retailservice.dto.ProductDTO;
import com.axity.curso.retailservice.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
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

    // --- ACTUALIZAR (PUT) ---
    public ProductDTO updateProduct(Long id, ProductDTO productDetails) {
        // Buscamos el producto asegurándonos de que exista y no esté borrado lógicamente
        Product existingProduct = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado o está eliminado con ID: " + id));

        // Actualizamos los campos permitidos (usualmente el SKU no se actualiza)
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        
        // CORRECCIÓN: Convertimos el Double del DTO a BigDecimal para la Entidad
        existingProduct.setPrice(java.math.BigDecimal.valueOf(productDetails.getPrice()));
        
        existingProduct.setStock(productDetails.getStock());
        existingProduct.setCategory(productDetails.getCategory());

        // Guardamos y retornamos el DTO actualizado
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    // --- BORRADO LÓGICO (SOFT DELETE) ---
    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        
        product.setDeleted(true); // Cambiamos la bandera en lugar de borrar
        productRepository.save(product);
    }

    // --- BORRADO FÍSICO (HARD DELETE) ---
    public void hardDeleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id); // Elimina el registro por completo de la BD
    }

    // --- ACTUALIZACIÓN PARCIAL (PATCH) ---
    public ProductDTO patchProduct(Long id, ProductDTO productDetails) {
        Product existingProduct = productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado o está eliminado con ID: " + id));

        // Solo actualizamos los campos que el usuario sí envió en el JSON (que no son nulos)
        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null) {
            existingProduct.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() != null) {
            // Convertimos el Double a BigDecimal como hicimos en el PUT
            existingProduct.setPrice(java.math.BigDecimal.valueOf(productDetails.getPrice()));
        }
        if (productDetails.getStock() != null) {
            existingProduct.setStock(productDetails.getStock());
        }
        if (productDetails.getCategory() != null) {
            existingProduct.setCategory(productDetails.getCategory());
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }
}