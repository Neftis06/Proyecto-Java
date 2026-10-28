package com.axity.curso.retailservice.model;

/**
 * Subclase (Clase Hija). 
 * La palabra clave 'extends' establece la relación "Es-Un" (Un Cajero ES UN EmpleadoRetail).
 */
public class Cajero extends EmpleadoRetail {
    
    // Atributo privado y exclusivo del Cajero
    private double comisionCaja;

    public Cajero(String numNomina, String nombre, double sueldoBase, double comisionCaja) {
        // 3. super(): Llama al constructor de la clase padre (EmpleadoRetail) para inicializar 
        // los atributos comunes antes de inicializar los atributos propios de esta clase.
        super(numNomina, nombre, sueldoBase);
        this.comisionCaja = comisionCaja;
    }

    // 4. Anotación @Override: Le indica al compilador de Java que estamos sobrescribiendo 
    // el comportamiento del método que heredamos del padre.
    @Override
    public double calcularSueldoNeto() {
        // Aprovechamos la lógica del padre (super.calcularSueldoNeto()) y le sumamos la comisión.
        return super.calcularSueldoNeto() + this.comisionCaja;
    }
}