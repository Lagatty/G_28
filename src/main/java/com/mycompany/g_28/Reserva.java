/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.ArrayList;
import java.util.List;

//Las reservas son del dia
class Reserva {
    private String id;
    private float tarifa;
    private String fecha;
    private String tipoReserva;
    private Persona persona;
    
    public Reserva(String id, String fecha, String tipoReserva, Persona persona, float tarifa) {
        this.id = id;
        this.fecha = fecha;
        this.tipoReserva = tipoReserva;
        this.persona = persona;
        this.tarifa = tarifa;
    }
    
    // Getters & Setters completos
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTipoReserva() {
        return tipoReserva;
    }
    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }
    public Persona getPersona() {
        return persona;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public float getTarifa() {
        return tarifa;
    }
    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + persona.getNombre() + " | " + tipoReserva + " | " + fecha + " | $" + tarifa;
    }
}