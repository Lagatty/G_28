/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.ArrayList;

public class Parque {
    private String nombreParque;
    private ArrayList<Zona> reservasZonas = new ArrayList<>();
    
    //SIA 1.8
    void agregarReserva(int capacidad, float tarifa, String nombreZona){
        //crear reserva
        Zona nuevaReserva = new Zona(capacidad, tarifa, nombreZona);
        //Agregar
        reservas.add(nuevaReserva);
    }
}
