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

    private final PurchaseOrderRepository orderRepository;
    private final PurchaseOrderMapper orderMapper;

    public List<PurchaseOrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PurchaseOrderDTO createOrder(PurchaseOrderDTO orderDTO) {
        // Aquí en el futuro puedes agregar validaciones de negocio complejas
        // Ej: Verificar si hay stock, calcular comisiones, etc.
        
        PurchaseOrder order = orderMapper.toEntity(orderDTO);
        
        // Si no viene estado desde el frontend, le asignamos uno por defecto
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("CREADA");
        }

        PurchaseOrder savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }
}