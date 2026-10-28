package com.axity.curso.retailservice.model;

/**
 * Clase Contenedora (Outer Class). Representa la cabecera del pedido.
 */
public class OrdenCompra {
    
    // Atributos de la clase externa
    private String orderId;
    private EstadoPedido estado;

    public OrdenCompra(String orderId, EstadoPedido estado) {
        this.orderId = orderId;
        this.estado = estado;
    }

    public String getOrderId() {
        return orderId;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Clase Anidada No Estática (Inner Class). Representa un detalle del pedido.
     * Al no ser 'static', cada DetalleLinea PERTENECE a una instancia específica de OrdenCompra.
     */
    public class DetalleLinea {
        private String sku;
        private int cantidad;

        public DetalleLinea(String sku, int cantidad) {
            this.sku = sku;
            this.cantidad = cantidad;
        }

        // Sobreescritura requerida para demostrar el acoplamiento
        @Override
        public String toString() {
            // MAGIA DE LA INNER CLASS:
            // Puede acceder directamente al atributo privado 'orderId' de la clase OrdenCompra 
            // que la contiene, usando la sintaxis 'ClaseExterna.this.atributo'.
            return "Línea: [SKU=" + this.sku + ", Cantidad=" + this.cantidad + 
                   "] -> Pertenece a la Orden: " + OrdenCompra.this.orderId;
        }
    }
}
