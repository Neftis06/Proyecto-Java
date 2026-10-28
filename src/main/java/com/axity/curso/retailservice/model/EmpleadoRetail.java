package com.axity.curso.retailservice.model;

/**
 * Superclase (Clase Padre).
 * Define las características y comportamientos comunes para cualquier tipo de empleado.
 */
public class EmpleadoRetail {
    
    // 1. Modificador 'protected': Permite que estos atributos sean leídos y modificados 
    // DIRECTAMENTE por las clases hijas (subclases) sin necesidad de getters/setters.
    protected String numNomina;
    protected String nombre;
    protected double sueldoBase;

    // Constructor de la superclase
    public EmpleadoRetail(String numNomina, String nombre, double sueldoBase) {
        this.numNomina = numNomina;
        this.nombre = nombre;
        this.sueldoBase = sueldoBase;
    }

    // 2. Método base que será sobrescrito
    // Por defecto, un empleado normal solo gana su sueldo base.
    public double calcularSueldoNeto() {
        return this.sueldoBase;
    }
    
    public String getNombre() {
        return nombre;
    }
}
