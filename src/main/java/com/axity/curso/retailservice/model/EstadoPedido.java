package com.axity.curso.retailservice.model;

/**
 * Enumeración que define los únicos estados posibles de un pedido.
 */
public enum EstadoPedido {
    
    // 1. Constantes del Enum (Instancias predefinidas)
    PENDIENTE(1, "El pedido está en preparación"),
    EN_TRANSITO(2, "El pedido salió de almacén y está en ruta"),
    ENTREGADO(3, "El pedido fue recibido por el cliente");

    // Atributos internos de cada constante
    private final int id;
    private final String descripcion;

    // Constructor del Enum (siempre es privado por defecto)
    EstadoPedido(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Métodos getters para acceder a los datos de la constante
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
}
