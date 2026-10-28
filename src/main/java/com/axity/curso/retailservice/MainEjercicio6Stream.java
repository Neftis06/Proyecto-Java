package com.axity.curso.retailservice;

import com.axity.curso.retailservice.lambdas.CalculadorComision;

public class MainEjercicio6Stream {
    public static void main(String[] args) {
        
        System.out.println("--- Sistema de Cálculo de Comisiones ---");

        // IMPLEMENTACIÓN DE LA LAMBDA DE BLOQUE
        // En lugar de crear una clase nueva que implemente la interfaz,
        // se la lógica directamente aquí usando una variable.
        CalculadorComision calculador = (montoSubtotal, esEnvioDomicilio) -> {
            
            double comisionBase = montoSubtotal * 0.03; // 3% de comisión bancaria por defecto
            
            // Lógica condicional interna
            if (esEnvioDomicilio && montoSubtotal < 500.00) {
                System.out.println("[Aviso] Aplica tarifa fija de envío por compra menor a $500.");
                comisionBase += 50.00; // Suma de tarifa de envio
            } else if (esEnvioDomicilio) {
                System.out.println("[Aviso] Envío gratis aplicado por compra superior a $500.");
            }
            
            return comisionBase;
        };

        // CASO DE USO 1: Compra pequeña con envío a domicilio
        double subtotalCompra1 = 300.00;
        double totalComision1 = calculador.calcular(subtotalCompra1, true);
        
        System.out.println("\nResumen Compra 1:");
        System.out.println("Subtotal: $" + subtotalCompra1);
        System.out.println("Total de Cargos/Envío: $" + totalComision1);
        System.out.println("Total a Pagar: $" + (subtotalCompra1 + totalComision1));

        // CASO DE USO 2: Compra grande con envío a domicilio
        double subtotalCompra2 = 1200.00;
        double totalComision2 = calculador.calcular(subtotalCompra2, true);
        
        System.out.println("\nResumen Compra 2:");
        System.out.println("Subtotal: $" + subtotalCompra2);
        System.out.println("Total de Cargos/Envío: $" + totalComision2);
        System.out.println("Total a Pagar: $" + (subtotalCompra2 + totalComision2));
    }
}