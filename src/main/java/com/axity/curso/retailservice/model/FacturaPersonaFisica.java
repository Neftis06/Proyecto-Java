package com.axity.curso.retailservice.model;

/**
 * Clase concreta que hereda de AbstractDocumentoFiscal.
 * Al heredar de una clase abstracta, está OBLIGADA a implementar sus métodos abstractos.
 */
public class FacturaPersonaFisica extends AbstractDocumentoFiscal {
    
    // Atributo específico de una persona física
    private String curp;

    public FacturaPersonaFisica(String folioFiscal, double subtotal, String curp) {
        super(folioFiscal, subtotal); // Llama al constructor de AbstractDocumentoFiscal
        this.curp = curp;
    }

    // Implementación OBLIGATORIA del contrato abstracto
    @Override
    public String generarEstructuraXML() {
        // Podemos usar this.folioFiscal directamente porque fue declarado como 'protected' en el padre.
        // También podemos invocar el método concreto calcularTotalConIVA() heredado.
        return "<xml>\n" +
               "  <folio>" + this.folioFiscal + "</folio>\n" +
               "  <curp>" + this.curp + "</curp>\n" +
               "  <total>" + this.calcularTotalConIVA() + "</total>\n" +
               "</xml>";
    }
}