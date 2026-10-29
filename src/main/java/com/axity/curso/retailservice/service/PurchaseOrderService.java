package com.axity.curso.retailservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.axity.curso.retailservice.dto.PurchaseOrderDTO;
import com.axity.curso.retailservice.exception.ResourceNotFoundException;
import com.axity.curso.retailservice.mapper.PurchaseOrderMapper;
import com.axity.curso.retailservice.model.PurchaseOrder;
import com.axity.curso.retailservice.repository.PurchaseOrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository orderRepository;
    private final PurchaseOrderMapper orderMapper;
    private final NotificationService notificationService;

    public List<PurchaseOrderDTO> getAllOrders() {
        return orderRepository.findByDeletedFalse().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PurchaseOrderDTO getOrderById(Long id) {
        PurchaseOrder order = orderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada o eliminada con ID: " + id));
        return orderMapper.toDTO(order);
    }

    // --- POST (Crear) ---
    public PurchaseOrderDTO createOrder(PurchaseOrderDTO orderDTO) {
        PurchaseOrder order = orderMapper.toEntity(orderDTO);
        
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("CREADA");
        }

        PurchaseOrder savedOrder = orderRepository.save(order);
        
        // Tarea asíncrona: Enviar notificación de creación
        notificationService.sendOrderConfirmationAsync(savedOrder.getOrderNumber());

        return orderMapper.toDTO(savedOrder);
    }

    // --- PUT (Actualizar Completo) ---
    public PurchaseOrderDTO updateOrder(Long id, PurchaseOrderDTO orderDetails) {
        PurchaseOrder existingOrder = orderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada o eliminada con ID: " + id));

        existingOrder.setOrderNumber(orderDetails.getOrderNumber());
        existingOrder.setStatus(orderDetails.getStatus());
        existingOrder.setTotalAmount(orderDetails.getTotalAmount()); 

        PurchaseOrder updatedOrder = orderRepository.save(existingOrder);

        // Tarea asíncrona: Auditoría de actualización completa
        notificationService.notifyAuditLogAsync("PUT_UPDATE", updatedOrder.getOrderNumber());

        return orderMapper.toDTO(updatedOrder);
    }

    // --- PATCH (Actualización Parcial) ---
    public PurchaseOrderDTO patchOrder(Long id, PurchaseOrderDTO orderDetails) {
        PurchaseOrder existingOrder = orderRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada o eliminada con ID: " + id));

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

        // Tarea asíncrona: Auditoría de actualización parcial
        notificationService.notifyAuditLogAsync("PATCH_UPDATE", updatedOrder.getOrderNumber());

        return orderMapper.toDTO(updatedOrder);
    }

    // --- DELETE (Borrado Lógico) ---
    public void softDeleteOrder(Long id) {
        PurchaseOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));
        
        order.setDeleted(true);
        orderRepository.save(order);

        // Tarea asíncrona: Notificar baja lógica en segundo plano
        notificationService.notifyAuditLogAsync("SOFT_DELETE", order.getOrderNumber());
    }

// --- DELETE (Borrado Físico) ---
    public void hardDeleteOrder(Long id) {
        PurchaseOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));

        // Borramos usando el objeto o el ID directamente
        orderRepository.deleteById(id);

        // Tarea asíncrona: Usamos el número de orden que recuperamos arriba
        notificationService.notifyAuditLogAsync("HARD_DELETE_PURGE", order.getOrderNumber());
    }
}