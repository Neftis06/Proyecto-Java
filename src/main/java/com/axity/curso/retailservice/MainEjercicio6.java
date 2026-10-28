package com.axity.curso.retailservice;

import com.axity.curso.retailservice.model.EstadoPedido;
import com.axity.curso.retailservice.model.OrdenCompra;

public class MainEjercicio6 {
    public static void main(String[] args) {
        
        // 1. Uso del Enum
        // Al asignar el estado, no pasamos un simple String que alguien podría escribir mal.
        // Obligamos a usar una constante segura del enum.
        OrdenCompra orden = new OrdenCompra("ORD-2026-999", EstadoPedido.EN_TRANSITO);

        System.out.println("--- Demostración de Enum ---");
        // Podemos leer la descripción asociada a la constante elegida
        System.out.println("Estado actual de la orden: " + EstadoPedido.EN_TRANSITO.getDescripcion());

        // 2. Instanciación de la Inner Class (Clase Anidada)
        System.out.println("\n--- Demostración de Inner Class ---");
        
        // ¡OJO AQUÍ! Para crear un "DetalleLinea", PRIMERO necesitas una "orden" viva.
        // La sintaxis es: instanciaExterna.new ClaseInterna(...)
        OrdenCompra.DetalleLinea linea1 = orden.new DetalleLinea("SKU-MOUSE", 2);
        OrdenCompra.DetalleLinea linea2 = orden.new DetalleLinea("SKU-TECLADO", 1);

        // Al imprimir las líneas, veremos cómo acceden automáticamente al ID "ORD-2026-999" de su clase padre.
        System.out.println(linea1.toString());
        System.out.println(linea2.toString());
    }
}