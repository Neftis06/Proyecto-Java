package com.axity.curso.retailservice.service;

import com.axity.curso.retailservice.dto.PurchaseOrderDTO;
import com.axity.curso.retailservice.mapper.PurchaseOrderMapper;
import com.axity.curso.retailservice.model.PurchaseOrder;
import com.axity.curso.retailservice.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    // Tus variables originales
    private final PurchaseOrderRepository orderRepository;
    private final PurchaseOrderMapper orderMapper;

    public List<PurchaseOrderDTO> getAllOrders() {
        // Usamos findByDeletedFalse() para respetar el borrado lógico
        return orderRepository.findByDeletedFalse().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Faltaba este método para el GET por ID
    public PurchaseOrderDTO getOrderById(Long id) {
        PurchaseOrder order = orderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada o eliminada con ID: " + id));
        return orderMapper.toDTO(order);
    }

    public PurchaseOrderDTO createOrder(PurchaseOrderDTO orderDTO) {
        PurchaseOrder order = orderMapper.toEntity(orderDTO);
        
        // Si no viene estado desde el frontend, le asignamos uno por defecto
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("CREADA");
        }

        PurchaseOrder savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    // --- ACTUALIZAR COMPLETO (PUT) ---
    public PurchaseOrderDTO updateOrder(Long id, PurchaseOrderDTO orderDetails) {
        PurchaseOrder existingOrder = orderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada o eliminada con ID: " + id));

        existingOrder.setOrderNumber(orderDetails.getOrderNumber());
        existingOrder.setStatus(orderDetails.getStatus());
        existingOrder.setTotalAmount(orderDetails.getTotalAmount()); 

        PurchaseOrder updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(updatedOrder);
    }

    // --- ACTUALIZACIÓN PARCIAL (PATCH) ---
    public PurchaseOrderDTO patchOrder(Long id, PurchaseOrderDTO orderDetails) {
        PurchaseOrder existingOrder = orderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada o eliminada con ID: " + id));

        if (orderDetails.getOrderNumber() != null) {
            existingOrder.setOrderNumber(orderDetails.getOrderNumber());
        }
        if (orderDetails.getStatus() != null) {
            existingOrder.setStatus(orderDetails.getStatus());
        }
        if (orderDetails.getTotalAmount() != null) {
            existingOrder.setTotalAmount(orderDetails.getTotalAmount());
        }

        PurchaseOrder updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(updatedOrder);
    }

    // --- BORRADO LÓGICO (SOFT DELETE) ---
    public void softDeleteOrder(Long id) {
        PurchaseOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
        
        order.setDeleted(true);
        orderRepository.save(order);
    }

    // --- BORRADO FÍSICO (HARD DELETE) ---
    public void hardDeleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Orden no encontrada con ID: " + id);
        }
        orderRepository.deleteById(id);
    }
}