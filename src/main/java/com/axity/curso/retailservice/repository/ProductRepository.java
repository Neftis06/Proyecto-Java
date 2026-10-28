package com.axity.curso.retailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axity.curso.retailservice.model.Product;


/**
 * Al extender JpaRepository, Spring Boot implementa automáticamente en memoria 
 * todos los métodos CRUD (save, findAll, findById, delete) sin que escribas una sola línea de SQL.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Podemos agregar métodos personalizados usando la convención de nombres de Spring Data
    // Por ejemplo, buscar un producto por su SKU:
    Product findBySku(String sku);
}
