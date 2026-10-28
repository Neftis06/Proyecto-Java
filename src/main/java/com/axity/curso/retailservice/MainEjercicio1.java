package com.axity.curso.retailservice;

import com.axity.curso.retailservice.model.ProductoRetail;

public class MainEjercicio1 {
    public static void main(String[] args) {
        
        // 1. Instanciación: Usamos la 'plantilla' ProductoRetail para crear dos objetos en memoria.
        // Aunque los datos (SKU, Descripción, Precio) son idénticos, son entes separados.
        ProductoRetail producto1 = new ProductoRetail("SKU123", "Laptop Dell", 15000.0, 10);
        ProductoRetail producto2 = new ProductoRetail("SKU123", "Laptop Dell", 15000.0, 10);

        // 2. Modificación del estado local
        // Aplicamos descuento SOLO al producto1. Esto demuestra que su estado no afecta al producto2.
        producto1.aplicarDescuento(10.0); 

        System.out.println("--- Demostración de Estado Local ---");
        System.out.println("Precio P1 (con descuento): $" + producto1.getPrecioUnitario()); // Imprime 13500.0
        System.out.println("Precio P2 (sin descuento): $" + producto2.getPrecioUnitario()); // Imprime 15000.0

        // 3. Demostración de Identidad en Memoria
        // Usamos System.identityHashCode para ver la dirección real en memoria RAM (la JVM) donde vive cada objeto.
        System.out.println("\n--- Demostración de Identidad en Memoria ---");
        System.out.println("ID Memoria P1: " + System.identityHashCode(producto1));
        System.out.println("ID Memoria P2: " + System.identityHashCode(producto2));
        
        // El operador '==' compara las direcciones de memoria, NO el contenido de los objetos.
        System.out.println("¿Son el mismo objeto en memoria? " + (producto1 == producto2)); // Imprime 'false'
    }
}
