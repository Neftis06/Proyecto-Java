package com.axity.curso.retailservice;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class MainEjercicio5Stream {
    public static void main(String[] args) {
        
        // 1. Creamos una lista de montos de tickets cobrados (List<Integer>)
        // Simulamos los cobros de un turno en caja.
        List<Integer> cobrosTurno = Arrays.asList(250, 45, 1200, 85, 310, 620, 15);

        // 2. Procesamiento con Stream Primitivo y extracción de métricas
        // El objeto IntSummaryStatistics almacena todas las métricas matemáticas en un solo paso.
        IntSummaryStatistics estadisticas = cobrosTurno.stream()
            
            // Operación intermedia 'mapToInt': Transforma el Stream genérico en un IntStream (Stream especializado en números enteros).
            // La Lambda toma el Integer 'c' y lo devuelve tal cual (desempaquetado como int primitivo).
            .mapToInt(c -> c)
            
            // Operación terminal 'summaryStatistics': Calcula TODAS las métricas matemáticas de un solo golpe.
            .summaryStatistics();

        // 3. Imprimimos el informe financiero consolidado
        System.out.println("--- CORTE DE CAJA: RESUMEN FINANCIERO ---");
        System.out.println("Total de Transacciones : " + estadisticas.getCount() + " tickets");
        System.out.println("Venta Mínima           : $" + estadisticas.getMin());
        System.out.println("Venta Máxima           : $" + estadisticas.getMax());
        System.out.println("Venta Total Acumulada  : $" + estadisticas.getSum());
        
        // Formateamos el promedio para que no salgan demasiados decimales (ej. %.2f)
        System.out.printf("Ticket Promedio        : $%.2f\n", estadisticas.getAverage());
    }
}