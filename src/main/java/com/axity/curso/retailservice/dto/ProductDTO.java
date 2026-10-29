package com.axity.curso.retailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Data Transfer Object para la entidad Product.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String sku;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private Boolean active;
}