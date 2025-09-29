// Archivo: src/main/java/com/mycompany/g_28/ReservaException.java
package com.mycompany.g_28;

/**
 * Excepción base para el sistema de reservas de parques
 * SIA 2.9 - Clase de excepción personalizada base
 */
public class ReservaException extends Exception {
    
    public ReservaException(String mensaje) {
        super(mensaje);
    }
    
    public ReservaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}