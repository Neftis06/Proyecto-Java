package com.axity.curso.retailservice;

import java.util.Arrays;
import java.util.List;
import com.axity.curso.retailservice.lambdas.Producto;

public class MainEjercicio1Stream {
    public static void main(String[] args) {
        
        // 1. Creamos el catálogo inicial con al menos 4 productos
        List<Producto> catalogo = Arrays.asList(
            new Producto("Refresco 600ml", 18.50),
            new Producto("Detergente Líquido", 85.00),
            new Producto("Papas Fritas", 22.00),
            new Producto("Café Soluble 200g", 115.50),
            new Producto("Galletas", 15.00)
        );

        System.out.println("--- Productos con precio superior a $50.00 MXN ---");

        // 2. Uso explícito de la API Stream y Expresiones Lambda
        catalogo.stream()
                // La operación intermedia 'filter' evalúa una condición booleana.
                // Lambda: "Dado un producto 'p', deja pasar solo si su precio es > 50.0"
                .filter(p -> p.getPrecio() > 50.00)
                // La operación terminal 'forEach' consume los elementos que pasaron el filtro.
                // Lambda: "Por cada producto filtrado 'p', imprime su nombre y precio"
                .forEach(p -> System.out.println(p.getNombre() + " - $" + p.getPrecio()));
    }
}