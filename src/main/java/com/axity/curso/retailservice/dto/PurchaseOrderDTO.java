package com.axity.curso.retailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String orderNumber;
    private String status;
    private Double totalAmount;
}