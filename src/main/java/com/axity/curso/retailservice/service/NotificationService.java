package com.axity.curso.retailservice.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendOrderConfirmationAsync(String orderNumber) {
        System.out.println("⏳ [HILO ASÍNCRONO] Enviando correo de confirmación para la orden: " + orderNumber);
    }

    @Async
    public void notifyAuditLogAsync(String action, String identifier) {
        System.out.println("📊 [HILO ASÍNCRONO - AUDITORÍA] Registrando evento [" + action + "] para: " + identifier);
    }

    @Async
    public void notifyProductActionAsync(String action, String productName) {
        System.out.println("📦 [HILO ASÍNCRONO - PRODUCTO] Acción [" + action + "] procesada para el producto: " + productName);
    }
}