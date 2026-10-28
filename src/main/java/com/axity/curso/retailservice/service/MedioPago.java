package com.axity.curso.retailservice.service;

/**
 * Interfaz que define el contrato para cualquier forma de pago.
 */
public interface MedioPago {
    
    // 1. Método abstracto
    // Obliga a cualquier clase que firme este contrato a decir CÓMO va a procesar el pago.
    boolean procesarPago(double monto);

    // 2. Método default (Introducido en Java 8)
    // Permite tener código real dentro de una interfaz. 
    // Todas las clases que implementen esta interfaz heredarán esta validación automáticamente.
    default boolean validarMonto(double monto) {
        return monto > 0.0;
    }
}