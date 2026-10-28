package com.axity.curso.retailservice;

import com.axity.curso.retailservice.dto.ClienteBean;

public class MainEjercicio2 {
    public static void main(String[] args) {
        
        // Creamos el Cliente A usando el estándar JavaBean (Constructor vacío + setters)
        ClienteBean clienteA = new ClienteBean();
        clienteA.setIdCliente(1050);
        clienteA.setNombre("Gerardo Casas");
        clienteA.setTarjetaFidelidad("VIP-001");
        clienteA.setSaldoPuntos(500);

        // Creamos el Cliente B con el MISMO ID, pero diferente nombre y puntos (ej. un error de captura o actualización)
        ClienteBean clienteB = new ClienteBean();
        clienteB.setIdCliente(1050); 
        clienteB.setNombre("G. Casas"); // Nombre diferente
        clienteB.setTarjetaFidelidad("VIP-001");
        clienteB.setSaldoPuntos(1200);  // Puntos diferentes

        System.out.println("Datos del Cliente A:");
        System.out.println(clienteA.toString()); // Java llama al toString() implícitamente aquí también

        System.out.println("\nDatos del Cliente B:");
        System.out.println(clienteB.toString());

        // Comprobación de la regla de negocio (equals)
        System.out.println("\n--- Prueba de Identidad Lógica ---");
        System.out.println("¿Son el mismo cliente para el negocio? " + clienteA.equals(clienteB));
        
        // Demostramos que el hashCode es el mismo porque el ID es el mismo
        System.out.println("Hash de A: " + clienteA.hashCode());
        System.out.println("Hash de B: " + clienteB.hashCode());
    }
}
