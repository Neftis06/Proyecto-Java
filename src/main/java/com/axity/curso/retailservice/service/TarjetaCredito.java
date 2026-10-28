package com.axity.curso.retailservice.service;

public class TarjetaCredito implements MedioPago {
    
    @Override
    public boolean procesarPago(double monto) {
        // Lógica específica simulada para tarjetas
        System.out.println("Conectando con la terminal bancaria...");
        System.out.println("Aprobando cobro de $" + monto + " a Tarjeta de Crédito.");
        return true; 
    }
}