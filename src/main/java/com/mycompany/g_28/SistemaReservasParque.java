/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;

/*
Sistema de Gestión de Reservas en Parques Nacionales: Administración de reservas de
campings, cabañas, y actividades guiadas. Monitoreo de capacidad y manejo de permisos y
tarifas. 
*/
public class SistemaReservasParque {
    
     // SIA1.5 - Primera colección de objetos
    private ArrayList<Parque> parques;

    public ArrayList<Parque> getParques() {
        return parques;
    }
    private CalculadoraTarifas calculadora;
    private BufferedReader lector;
    
    public void guardarSistema(String archivo) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(archivo), this.parques);
            System.out.println("Sistema guardado en " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cargarSistema(String archivo) {
    XmlMapper xmlMapper = new XmlMapper();
    try {
        List<Parque> cargados = xmlMapper.readValue(new File(archivo),
                xmlMapper.getTypeFactory().constructCollectionType(List.class, Parque.class));
        this.parques = new ArrayList<>(cargados);
        System.out.println("CArgado correctamentel");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public SistemaReservasParque() {
        this.parques = new ArrayList<>();
        this.calculadora = new CalculadoraTarifas();
        this.lector = new BufferedReader(new InputStreamReader(System.in));
        
        // SIA1.4 - Datos iniciales dentro del código
        inicializarDatos();
    }
    
    // SIA1.4 - Crear datos iniciales (solo parques base)
    public void inicializarDatos() {
        // Crear parques iniciales disponibles
        Parque parque1 = new Parque("Torres del Paine", "Patagonia", 200);
        Parque parque2 = new Parque("Conguillio", "Araucanía", 150);
        Parque parque3 = new Parque("Lauca", "Arica", 100);
        
        parques.add(parque1);
        parques.add(parque2);
        parques.add(parque3);
        
        // Las reservas serán ingresadas por el usuario por pantalla
    }

    // SIA1.8.1 - Inserción manual/agregar elemento (para la colección anidada)
    public void crearReserva(String rut, String nombre, String fecha,int numParque, int tipoAlojamiento, boolean conGuia){
        
        //recibir parque     
        Parque parqueSeleccionado = parques.get(numParque);
        
        // Datos del cliente
        Persona persona = new Persona(rut, nombre);
        
        // Datos de la reserva
        String tipoReserva;
        List<String> servicios = new ArrayList<>();
        tipoAlojamiento--;
        if (tipoAlojamiento == 1) {
            tipoReserva = "CAMPING";
            servicios.add("CAMPING");
        } else {
            tipoReserva = "CABANA";
            servicios.add("CABANA");
        }
        
        //contabilizar al guia 
        if (conGuia==true) {
            servicios.add("GUIA");
            tipoReserva += " + GUIA";
        }
        
        // Calcular tarifa
        float tarifa = calculadora.calcularTarifa(servicios);
        
        // Crear y agregar reserva a la colección anidada
        parqueSeleccionado.agregarReserva(fecha, tipoReserva, persona, tarifa);
    }
    
    // SIA1.8.2 - Mostrar listado de elementos (colección anidada)
    public String getTodasLasReservas() {
        
        String reservas = new String();
        boolean hayReservas = false;
        for (Parque parque : parques) {
            if (!parque.getReservas().isEmpty()) {
                reservas = reservas + "\n️ " + parque.getNombre() + ":";
               
                for (Reserva reserva : parque.getReservas()) {
                    
                    reservas = reservas +("\n\n" + reserva.toString());
                }
                hayReservas = true;
            }
        }
        
        if (!hayReservas) {
            return("No hay reservas registradas en el sistema.");
        }
        return reservas;
    }
    
    public String getListaParques() {
        String listaParques = new String();
        
        for (int i = 0; i < parques.size(); i++) {
            listaParques = listaParques + (i + 1) + ". " + parques.get(i).toString() + "\n\n";
            
        }
        return listaParques;
    }
    }