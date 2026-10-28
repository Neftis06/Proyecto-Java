package com.axity.curso.retailservice.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que sigue el estándar JavaBean para encapsular datos.
 * Implementa Serializable para que el objeto pueda ser convertido a bytes 
 * (útil para enviarlo por red, guardarlo en disco o en sesiones web).
 */
public class ClienteBean implements Serializable {
    
    // 1. Identificador de versión para la serialización (Requisito JavaBean)
    private static final long serialVersionUID = 1L;

    // 2. Atributos privados (Encapsulamiento estricto)
    private Integer idCliente;
    private String nombre;
    private String tarjetaFidelidad;
    private int saldoPuntos;

    // 3. Constructor sin argumentos (Requisito JavaBean)
    // Permite que frameworks (como Spring o Hibernate) puedan instanciar el objeto vacio por detrás.
    public ClienteBean() {
    }

    // 4. Métodos Getters y Setters para TODOS los atributos (Requisito JavaBean)
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTarjetaFidelidad() { return tarjetaFidelidad; }
    public void setTarjetaFidelidad(String tarjetaFidelidad) { this.tarjetaFidelidad = tarjetaFidelidad; }

    public int getSaldoPuntos() { return saldoPuntos; }
    public void setSaldoPuntos(int saldoPuntos) { this.saldoPuntos = saldoPuntos; }

    // 5. Sobreescritura de equals() basada SOLO en idCliente
    // Define la regla de negocio: "Dos clientes son el mismo si tienen el mismo ID, sin importar el resto de los datos".
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si es la misma dirección de memoria, son iguales
        if (o == null || getClass() != o.getClass()) return false; // Si es nulo o de otra clase, no son iguales
        
        ClienteBean that = (ClienteBean) o;
        // Usamos Objects.equals para evitar un NullPointerException si idCliente es nulo
        return Objects.equals(this.idCliente, that.idCliente);
    }

    // 6. Sobreescritura de hashCode()
    // Si dos objetos son equals(), DEBEN tener el mismo hashCode para que las colecciones (como HashSet o HashMap) funcionen bien.
    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }

    // 7. Representación legible del objeto
    @Override
    public String toString() {
        return "ClienteBean {" +
                "ID=" + idCliente +
                ", Nombre='" + nombre + '\'' +
                ", Tarjeta='" + tarjetaFidelidad + '\'' +
                ", Puntos=" + saldoPuntos +
                '}';
    }
}