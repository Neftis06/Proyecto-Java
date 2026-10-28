package com.axity.curso.retailservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axity.curso.retailservice.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    
    // Método personalizado de Spring Data JPA
    // Con solo declararlo, Spring genera la consulta SQL: SELECT * FROM purchase_orders WHERE order_number = ?
    PurchaseOrder findByOrderNumber(String orderNumber);
}