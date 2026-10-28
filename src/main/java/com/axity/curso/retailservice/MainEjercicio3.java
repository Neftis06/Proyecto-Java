package com.axity.curso.retailservice;

import com.axity.curso.retailservice.model.Cajero;
import com.axity.curso.retailservice.model.GerenteTienda;

public class MainEjercicio3 {
    public static void main(String[] args) {
        
        // Instanciamos un Cajero (Sueldo base: 8000, Comisión: 1500)
        Cajero cajero = new Cajero("N-001", "Ana Pérez", 8000.0, 1500.0);
        
        // Instanciamos un Gerente (Sueldo base: 20000, Bono: 5000)
        GerenteTienda gerente = new GerenteTienda("N-002", "Carlos Gómez", 20000.0, 5000.0);

        System.out.println("--- Reporte de Nómina ---");
        
        // Al llamar a calcularSueldoNeto(), Java sabe automáticamente qué versión ejecutar 
        // dependiendo de si el objeto es un Cajero o un Gerente.
        System.out.println("Sueldo Neto de " + cajero.getNombre() + " (Cajero): $" + cajero.calcularSueldoNeto());
        System.out.println("Sueldo Neto de " + gerente.getNombre() + " (Gerente): $" + gerente.calcularSueldoNeto());
    }
}