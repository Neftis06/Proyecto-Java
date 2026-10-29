package com.axity.curso.retailservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.axity.curso.retailservice.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    
    // Traer todas las órdenes que NO han sido borradas
    List<PurchaseOrder> findByDeletedFalse();

    // Buscar una orden por ID solo si NO ha sido borrada
    Optional<PurchaseOrder> findByIdAndDeletedFalse(Long id);
}