package com.axity.curso.retailservice.model;

/**
 * Al declarar la clase como 'abstract', le decimos a Java que esta clase 
 * es una plantilla incompleta. NO se pueden crear objetos directos de ella 
 * (ej. no puedes hacer: new AbstractDocumentoFiscal(...)).
 */
public abstract class AbstractDocumentoFiscal {
    
    // Atributos protegidos para que las clases hijas los hereden directamente
    protected String folioFiscal;
    protected double subtotal;

    public AbstractDocumentoFiscal(String folioFiscal, double subtotal) {
        this.folioFiscal = folioFiscal;
        this.subtotal = subtotal;
    }

    // 1. Método Concreto: Tiene cuerpo { ... } y lógica real.
    // Todas las clases hijas heredarán este comportamiento exactamente igual.
    public double calcularTotalConIVA() {
        return this.subtotal * 1.16; // Aplica el 16% de IVA sobre el subtotal
    }

    // 2. Método Abstracto: NO tiene cuerpo, solo la firma terminada en punto y coma.
    // Funciona como un CONTRATO: Obliga a cualquier clase hija a programar este método.
    public abstract String generarEstructuraXML();
}
