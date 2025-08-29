/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author franc
 */
public class CalculadoraTarifas {
     private Map<String, Float> tarifasBase; // SIA1.7 - Mapa del JCF
    
    public CalculadoraTarifas() {
        this.tarifasBase = new HashMap<>();
        // Inicializar tarifas base
        tarifasBase.put("CAMPING", 15000f);
        tarifasBase.put("CABANA", 45000f);
        tarifasBase.put("GUIA", 20000f);
    }
    
    // Sobrecarga de métodos para calcular tarifas
    public float calcularTarifa(String tipo) {
        return tarifasBase.getOrDefault(tipo.toUpperCase(), 0f);
    }
    
    public float calcularTarifa(String tipo, boolean conGuia) {
        float tarifa = tarifasBase.getOrDefault(tipo.toUpperCase(), 0f);
        if (conGuia) {
            tarifa += tarifasBase.get("GUIA");
        }
        return tarifa;
    }
    
    public float calcularTarifa(List<String> servicios) {
        float total = 0f;
        for (String servicio : servicios) {
            total += tarifasBase.getOrDefault(servicio.toUpperCase(), 0f);
        }
        return total;
    }
    
    // Getters & Setters
    public Map<String, Float> getTarifasBase() { return tarifasBase; }
    public void setTarifasBase(Map<String, Float> tarifasBase) { this.tarifasBase = tarifasBase; }
}
