package com.axity.curso.retailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Data Transfer Object para la entidad Product.
 * @Data genera getters, setters, toString, equals y hashCode automáticamente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String sku;
    private String name;
    private Double price;
    private Boolean active;
}