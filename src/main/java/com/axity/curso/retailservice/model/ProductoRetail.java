package com.axity.curso.retailservice.model;

/**
 * Clase que representa la abstracción de un producto en el sistema Retail.
 * Aplica el principio de encapsulamiento ocultando sus atributos (private).
 */
public class ProductoRetail {
    
    // 1. Atributos encapsulados que definen el estado del objeto
    private String sku;
    private String descripcion;
    private double precioUnitario;
    private int existencias;

    // 2. Constructor por defecto (sin argumentos)
    // Permite instanciar el objeto vacío para luego llenar sus datos con setters.
    public ProductoRetail() {
    }

    // 3. Constructor parametrizado
    // Permite inicializar el estado completo del objeto en el momento de su creación (operador 'new').
    public ProductoRetail(String sku, String descripcion, double precioUnitario, int existencias) {
        this.sku = sku;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.existencias = existencias;
    }

    // 4. Método de negocio: Aplicar descuento
    // Modifica el estado interno (precioUnitario) aplicando lógica de validación.
    public void aplicarDescuento(double porcentaje) {
        // Validamos que el porcentaje sea lógico (mayor a 0 y hasta 100%)
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = this.precioUnitario * (porcentaje / 100);
            this.precioUnitario -= descuento; // Actualiza el precio restando el descuento
        }
    }

    // 5. Método de negocio: Reabastecer inventario
    // Modifica el estado interno (existencias) sumando la cantidad recibida.
    public void reabastecer(int cantidad) {
        if (cantidad > 0) {
            this.existencias += cantidad;
        }
    }

    // 6. Métodos de acceso (Getters) 
    // Permiten leer el estado encapsulado desde fuera de la clase.
    public String getSku() { return sku; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getExistencias() { return existencias; }
}