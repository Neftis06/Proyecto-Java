package com.axity.curso.retailservice;

import java.util.Arrays;
import java.util.List;
import com.axity.curso.retailservice.lambdas.VentaArticulo;

public class MainEjercicio4Stream {
    public static void main(String[] args) {
        
        // 1. Construimos la lista con el reporte diario (al menos 5 artículos)
        List<VentaArticulo> ventasDelDia = Arrays.asList(
            new VentaArticulo("Refresco 600ml", 150),
            new VentaArticulo("Papas Fritas", 85),
            new VentaArticulo("Galletas de Chocolate", 210),
            new VentaArticulo("Detergente Líquido", 45),
            new VentaArticulo("Agua Purificada 1L", 320)
        );

        System.out.println("--- Top 3 Artículos Más Vendidos del Día ---");

        // 2. Procesamiento con Stream
        ventasDelDia.stream()
            // Operación intermedia 'sorted' con COMPARADOR LAMBDA.
            // Le enseñamos a Java cómo comparar dos objetos VentaArticulo (v1 y v2).
            // Nota: Invertimos el orden (v2 contra v1) para que sea DESCENDENTE (de mayor a menor).
            .sorted((v1, v2) -> Integer.compare(v2.getUnidadesVendidas(), v1.getUnidadesVendidas()))
            
            // Operación intermedia 'limit': Corta el flujo, dejando pasar solo a los primeros N elementos.
            .limit(3)
            
            // Operación terminal 'forEach': Imprime los resultados.
            .forEach(v -> System.out.println(v.getArticulo() + " : " + v.getUnidadesVendidas() + " unidades"));
    }
}
