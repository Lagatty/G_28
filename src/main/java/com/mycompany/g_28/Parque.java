/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.ArrayList;
import java.util.List;

//SIA1.3 Creacion de Clase Parque
class Parque {
    private String nombre;
    private String ubicacion;
    private int capacidadMaxima;
    private List<Reserva> reservas; //SIA1.5 segunda colección 
    
    public Parque(String nombre, String ubicacion, int capacidadMaxima) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.reservas = new ArrayList<>(); //coleccion anidada
    }
    
    public Parque(){
        
    }
    
    //Getters & Setters
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
    public int getCapacidadMaxima(){ 
        return capacidadMaxima; 
    }
    public void setCapacidadMaxima(int capacidadMaxima){ 
        this.capacidadMaxima = capacidadMaxima; 
    }
    public List<Reserva> getReservas() { 
        return reservas; 
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas; 
    }
    
    // SIA1.6 sobrecarga de metodos
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
        float tarifaDefault = 25000f; //creamos una tarifa por defecto.
        Reserva nuevaReserva = new Reserva(id, fecha, tipoReserva, persona, tarifaDefault);
        reservas.add(nuevaReserva);
    }
    
    
    public String toString() {
        return nombre + " - " + ubicacion + " (Capacidad: " + capacidadMaxima + ", Reservas: " + reservas.size() + ")";
    }
}
