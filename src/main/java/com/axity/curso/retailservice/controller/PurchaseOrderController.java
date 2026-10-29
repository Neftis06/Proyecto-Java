package com.axity.curso.retailservice.controller;

import com.axity.curso.retailservice.dto.PurchaseOrderDTO;
import com.axity.curso.retailservice.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAllOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createOrder(@RequestBody PurchaseOrderDTO orderDTO) {
        PurchaseOrderDTO createdOrder = purchaseOrderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> updateOrder(@PathVariable Long id, @RequestBody PurchaseOrderDTO orderDTO) {
        return ResponseEntity.ok(purchaseOrderService.updateOrder(id, orderDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> patchOrder(@PathVariable Long id, @RequestBody PurchaseOrderDTO orderDTO) {
        return ResponseEntity.ok(purchaseOrderService.patchOrder(id, orderDTO));
    }

    @DeleteMapping("/{id}/soft")
    public ResponseEntity<Void> softDeleteOrder(@PathVariable Long id) {
        purchaseOrderService.softDeleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<Void> hardDeleteOrder(@PathVariable Long id) {
        purchaseOrderService.hardDeleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}