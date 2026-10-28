package com.axity.curso.retailservice;

import com.axity.curso.retailservice.service.MedioPago;
import com.axity.curso.retailservice.service.ProcesadorPagosService;
import com.axity.curso.retailservice.service.TarjetaCredito;
import com.axity.curso.retailservice.service.ValeElectronico;

public class MainEjercicio5 {
    public static void main(String[] args) {
        
        // Instanciamos el servicio (el motor que procesa pagos)
        ProcesadorPagosService procesador = new ProcesadorPagosService();

        // Instanciamos nuestras formas de pago usando el Polimorfismo.
        // La variable es de tipo 'MedioPago', pero el objeto real es una 'TarjetaCredito'.
        MedioPago pagoConTarjeta = new TarjetaCredito();
        MedioPago pagoConVales = new ValeElectronico();

        System.out.println("--- Cliente 1 (Pago Válido con Tarjeta) ---");
        // El procesador recibe una tarjeta y 1500 pesos
        procesador.ejecutarCobro(pagoConTarjeta, 1500.0);

        System.out.println("\n--- Cliente 2 (Pago Válido con Vales) ---");
        // El MISMO procesador ahora recibe vales
        procesador.ejecutarCobro(pagoConVales, 850.50);

        System.out.println("\n--- Cliente 3 (Pago Inválido) ---");
        // Demostramos el uso del método 'default' validarMonto()
        procesador.ejecutarCobro(pagoConTarjeta, -50.0);
    }
}