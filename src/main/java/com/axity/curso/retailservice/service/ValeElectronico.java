package com.axity.curso.retailservice.service;

public class ValeElectronico implements MedioPago {
    
    @Override
    public boolean procesarPago(double monto) {
        // Lógica específica simulada para vales de despensa
        System.out.println("Consultando saldo del monedero...");
        System.out.println("Descontando $" + monto + " vía Vales Electrónicos.");
        return true; 
    }
}
