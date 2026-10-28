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

    private final PurchaseOrderService orderService;

    // Endpoint GET: http://localhost:8080/api/orders
    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAllOrders() {
        List<PurchaseOrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Endpoint POST: http://localhost:8080/api/orders
    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createOrder(@RequestBody PurchaseOrderDTO orderDTO) {
        PurchaseOrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}