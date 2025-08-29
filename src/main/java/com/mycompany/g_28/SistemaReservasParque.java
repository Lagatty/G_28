/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.g_28;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Sistema de Gestión de Reservas en Parques Nacionales: Administración de reservas de
campings, cabañas, y actividades guiadas. Monitoreo de capacidad y manejo de permisos y
tarifas. 
*/
public class SistemaReservasParque {
    
    //Se almacenan los permisos 
    private ArrayList<String> permisos = new ArrayList<>();
    
    //Declarar coleccion de parques
    private ArrayList<Parque> parques = new ArrayList<>();
    //SIA 1.4
    public void crearParques(int cantParques){
        
    }
    
    public void agregarParque(){
        //crear parque
        
    }
    
    //SIA 1.8
    public void listarParques(){
        
    }
    
    public void listarClientes(){
        
    }
    
    //agregar para las excepciones
    public void mostrarMenu(){
        int opcion = 0;
        //declarar lector
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("1. Crear reserva \n 2. Crear parque \n 3. Listar reservas. \n 4. Salir");
        
        while(opcion!=4){
            opcion = Integer.parseInt(lector.readLine());
            switch (opcion) {
                case 1:
                    
                    break; 
                case 2:
                    
                    break;
                case 3:
                    
                default:
                    System.out.println("Ingrese opcion valida");
                    break;
            }
            
        }
    }
    
    public SistemaReservasParque(){
        mostrarMenu();
    }
    
    
}
