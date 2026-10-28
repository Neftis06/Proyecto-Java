package com.axity.curso.retailservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainEjercicio3Stream {
    public static void main(String[] args) {
        
        // 1. Creamos la lista inicial con códigos SKU duplicados y desordenados
        List<String> escaneoAlmacen = Arrays.asList(
            "SKU-902", "SKU-101", "SKU-505", 
            "SKU-101", "SKU-300", "SKU-902", "SKU-505"
        );

        // 2. Procesamiento con Stream para depurar y ordenar
        List<String> skusLimpios = escaneoAlmacen.stream()
            // Operación intermedia 'distinct': Elimina elementos duplicados.
            .distinct()
            // Operación intermedia 'sorted': Ordena los elementos de forma natural (alfabéticamente para Strings).
            .sorted()
            // Operación terminal 'collect': Guarda el resultado final en una nueva lista.
            .collect(Collectors.toList());

        // 3. Imprimimos los resultados antes y después del procesamiento
        System.out.println("--- Depuración de Escáner de Almacén ---");
        System.out.println("Lectura Original (Con duplicados): " + escaneoAlmacen);
        System.out.println("Catálogo Limpio  (Únicos y Ordenados): " + skusLimpios);
    }
}
