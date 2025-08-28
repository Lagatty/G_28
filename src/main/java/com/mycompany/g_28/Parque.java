/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parque {
    private String nombreParque;
    
    private ArrayList<Reserva> reservasZonas = new ArrayList<>();
    
    //Guarda ruts asociados a reservas
    private Map<String,Reserva> mapaRut_Reserva = new HashMap<>();
    
    //SIA 1.8
    void agregarReserva(){
        //crear reserva
        //Se le piden por consola para la zona
        Reserva nuevaReserva = new Reserva(capacidad, tarifa, nombreZona);
        //Agregar
        reservas.add(nuevaReserva);
    }
    
    void eliminarReserva()
}
