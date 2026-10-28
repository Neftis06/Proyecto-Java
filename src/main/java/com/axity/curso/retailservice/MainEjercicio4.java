package com.axity.curso.retailservice;

import com.axity.curso.retailservice.model.FacturaPersonaFisica;

public class MainEjercicio4 {
    public static void main(String[] args) {
        
        // Creamos una factura por un subtotal de $1,000.00
        FacturaPersonaFisica factura = new FacturaPersonaFisica("FAC-999888", 1000.0, "CUPG020806XXXXXX");
        
        System.out.println("--- Procesamiento de Factura ---");
        // Ejecuta el método concreto heredado del padre
        System.out.println("Total a pagar (con IVA): $" + factura.calcularTotalConIVA());
        
        // Ejecuta el método abstracto implementado en el hijo
        System.out.println("\nEstructura XML Generada:");
        System.out.println(factura.generarEstructuraXML());
    }
}