/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;

/**
 *
 * @author franc
 */
public class CapacidadExcedidaException extends ReservaException {
    private String nombreParque;
    private int capacidadMaxima;
    private int reservasActuales;
    
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
    
    public CapacidadExcedidaException(String mensaje, String nombreParque, int capacidadMaxima, int reservasActuales) {
        super(mensaje);
        this.nombreParque = nombreParque;
        this.capacidadMaxima = capacidadMaxima;
        this.reservasActuales = reservasActuales;
    }
    
    public String getNombreParque() {
        return nombreParque;
    }
    
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    public int getReservasActuales() {
        return reservasActuales;
    }
    
    public int getCuposDisponibles() {
        return Math.max(0, capacidadMaxima - reservasActuales);
    }
}
