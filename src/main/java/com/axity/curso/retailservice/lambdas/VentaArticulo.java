package com.axity.curso.retailservice.lambdas;

/**
 * Clase que representa el reporte de unidades desplazadas de un artículo.
 */
public class VentaArticulo {
    private String articulo;
    private int unidadesVendidas;

    public VentaArticulo(String articulo, int unidadesVendidas) {
        this.articulo = articulo;
        this.unidadesVendidas = unidadesVendidas;
    }

    public String getArticulo() {
        return articulo;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }
}