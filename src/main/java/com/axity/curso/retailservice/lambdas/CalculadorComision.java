package com.axity.curso.retailservice.lambdas;

@FunctionalInterface
public interface CalculadorComision {
    
    double calcular(double montoSubtotal, boolean esEnvioDomicilio);
    
}