package com.axity.curso.retailservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainEjercicio2Stream {
    public static void main(String[] args) {
        
        // 1. Definimos la lista inicial de precios de lista (List<Double>)
        List<Double> preciosBase = Arrays.asList(100.0, 200.0, 50.0, 1500.0, 399.99);

        // 2. Procesamiento con Stream
        List<Double> preciosConDescuento = preciosBase.stream()
            // Operación intermedia 'map': TRANSFORMA cada elemento.
            // Lambda: "Toma el precio 'p' y devuélvelo multiplicado por 0.85"
            .map(p -> p * 0.85)
            // Operación terminal 'collect': RECOLECTA los resultados en una nueva Lista.
            .collect(Collectors.toList());

        // 3. Imprimimos ambas listas para verificar que la original quedó intacta 
        // y la nueva tiene la transformación aplicada.
        System.out.println("--- Reporte de Transformación de Precios ---");
        System.out.println("Precios Base Originales : " + preciosBase);
        System.out.println("Precios Finales (-15%)  : " + preciosConDescuento);
    }
}