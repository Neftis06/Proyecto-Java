package com.axity.curso.retailservice.service;

import org.springframework.stereotype.Service;

@Service // Anotación de Spring (ya que estamos en integración nativa)
public class ProcesadorPagosService {
    
    // DEMOSTRACIÓN DE POLIMORFISMO:
    // Fíjate que el parámetro NO pide una TarjetaCredito ni un ValeElectronico.
    // Pide CUALQUIER COSA que cumpla con el contrato 'MedioPago'.
    public void ejecutarCobro(MedioPago medioPago, double monto) {
        
        // 1. Usa el método default heredado para validar
        if (medioPago.validarMonto(monto)) {
            
            // 2. Ejecuta el cobro. En tiempo de ejecución (Run), Java sabrá 
            // a cuál de los dos (Tarjeta o Vale) debe llamar realmente.
            boolean exito = medioPago.procesarPago(monto);
            
            System.out.println("Resultado de la transacción: " + (exito ? "Aprobado" : "Rechazado"));
        } else {
            System.out.println("Error: El monto a cobrar (" + monto + ") debe ser mayor a cero.");
        }
    }
}