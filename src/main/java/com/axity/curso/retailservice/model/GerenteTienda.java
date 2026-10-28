package com.axity.curso.retailservice.model;

/**
 * Subclase (Clase Hija). 
 * Un GerenteTienda ES UN EmpleadoRetail.
 */
public class GerenteTienda extends EmpleadoRetail {
    
    // Atributo privado y exclusivo del Gerente
    private double bonoMetasVenta;

    public GerenteTienda(String numNomina, String nombre, double sueldoBase, double bonoMetasVenta) {
        // Invoca al constructor de la clase padre
        super(numNomina, nombre, sueldoBase);
        this.bonoMetasVenta = bonoMetasVenta;
    }

    @Override
    public double calcularSueldoNeto() {
        // Sumamos el bono ejecutivo al sueldo base
        return super.calcularSueldoNeto() + this.bonoMetasVenta;
    }
}