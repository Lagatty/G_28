/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;


public class Persona {
    private String rut;
    private String nombre;
    
    public Persona(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }
    
    public Persona(){
        
    }
    //Getters & Setters
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString() {
        return nombre + " (RUT: " + rut + ")";
    }
    
}
