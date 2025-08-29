/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.Map;
import java.util.HashMap;

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

// SIA1.3 - Clase Parque con atributos privados
class Parque {
    private String nombre;
    private String ubicacion;
    private int capacidadMaxima;
    private List<Reserva> reservas; // SIA1.5 - Segunda colección (anidada)
    
    public Parque(String nombre, String ubicacion, int capacidadMaxima) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.reservas = new ArrayList<>(); // Colección anidada
    }
    
    // Getters & Setters
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public String getUbicacion() { 
        return ubicacion; 
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion; 
    }
    public int getCapacidadMaxima() { 
        return capacidadMaxima; 
    }
    public void setCapacidadMaxima(int capacidadMaxima) { 
        this.capacidadMaxima = capacidadMaxima; 
    }
    public List<Reserva> getReservas() { 
        return reservas; 
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas; 
    }
    
    //SIA1.6 primera sobrecarga
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
    
    public void agregarReserva(String fecha, String tipoReserva, Persona persona, float tarifa) {
        String id = "R" + (reservas.size() + 1);
        Reserva nuevaReserva = new Reserva(id, fecha, tipoReserva, persona, tarifa);
        reservas.add(nuevaReserva);
    }
    
    public void agregarReserva(String fecha, String tipoReserva, Persona persona) {
        String id = "R" + (reservas.size() + 1);
        float tarifaDefault = 25000f; // Tarifa por defecto
        Reserva nuevaReserva = new Reserva(id, fecha, tipoReserva, persona, tarifaDefault);
        reservas.add(nuevaReserva);
    }
    
    public String toString() {
        return nombre + " - " + ubicacion + " (Capacidad: " + capacidadMaxima + ", Reservas: " + reservas.size() + ")";
    }
}
