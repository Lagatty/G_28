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
        System.out.println("Sistema cargado desde " + archivo);
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
        /*
        try {
            mostrarMenu();
        } catch (IOException e) {
            System.out.println("Error en el sistema: " + e.getMessage());
        }
        */
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
    
    // SIA1.8 - Menú del sistema
    public void mostrarMenu() throws IOException {
        int opcion = 0;
        
        System.out.println("=== SISTEMA DE RESERVAS PARQUES NACIONALES ===");
        
        while(opcion != 6) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Mostrar todas las reservas"); // SIA1.8.2
            System.out.println("3. Listar parques disponibles");
            System.out.println("4. Guardar sistema");
            System.out.println("5. Cargar sistema");
            System.out.println("6. Salir");
            System.out.print("Ingrese opcion: ");
            
            try {
                opcion = Integer.parseInt(lector.readLine());
                
                switch (opcion) {
                    case 1:
                        crearReserva(); // SIA1.8.1 - Inserción manual
                        break; 
                    case 2:
                        listarTodasLasReservas(); // SIA1.8.2 - Mostrar listado
                        break;
                    case 3:
                        listarParques();
                        break;
                    case 4:
                        guardarSistema("sistema.xml");
                        break;
                    case 5:
                        cargarSistema("sistema.xml");
                        break;
                    case 6:
                        guardarSistema("sistema.xml");
                        System.out.println("¡Gracias por usar el sistema!");
                        break;                    
                    default:
                        System.out.println("Ingrese opcion valida (1-6)");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un numero valido");
            }
        }
    }
    
    // SIA1.8.1 - Inserción manual/agregar elemento (para la colección anidada)
    public void crearReserva() throws IOException {
        System.out.println("\n--- CREAR NUEVA RESERVA ---");
        
        // Mostrar parques disponibles
        listarParques();
        System.out.print("Seleccione el numero del parque: ");
        int numParque = Integer.parseInt(lector.readLine()) - 1;
        
        if (numParque < 0 || numParque >= parques.size()) {
            System.out.println("Parque no valido");
            return;
        }
        
        Parque parqueSeleccionado = parques.get(numParque);
        
        // Datos del cliente
        System.out.print("Ingrese su RUT: ");
        String rut = lector.readLine();
        System.out.print("Ingrese su nombre: ");
        String nombre = lector.readLine();
        Persona persona = new Persona(rut, nombre);
        
        // Datos de la reserva
        System.out.print("Ingrese fecha (DD/MM/AAAA): ");
        String fecha = lector.readLine();
        
        System.out.println("Servicios disponibles:");
        System.out.println("1. CAMPING ($15.000)");
        System.out.println("2. CABANA ($45.000)");
        System.out.print("¿Que tipo de alojamiento desea? (1-2): ");
        int tipoAlojamiento = Integer.parseInt(lector.readLine());
        
        String tipoReserva;
        List<String> servicios = new ArrayList<>();
        
        if (tipoAlojamiento == 1) {
            tipoReserva = "CAMPING";
            servicios.add("CAMPING");
        } else {
            tipoReserva = "CABANA";
            servicios.add("CABANA");
        }
        
        System.out.print("¿Desea agregar guia turistico? ($20.000) (s/n): ");
        String conGuia = lector.readLine();
        if (conGuia.toLowerCase().equals("s")) {
            servicios.add("GUIA");
            tipoReserva += " + GUIA";
        }
        
        // Calcular tarifa
        float tarifa = calculadora.calcularTarifa(servicios);
        
        // Crear y agregar reserva a la colección anidada
        parqueSeleccionado.agregarReserva(fecha, tipoReserva, persona, tarifa);
        
        System.out.println("\n¡RESERVA CREADA EXITOSAMENTE!");
        System.out.println("Parque: " + parqueSeleccionado.getNombre());
        System.out.println("Cliente: " + persona.getNombre());
        System.out.println("Servicios: " + tipoReserva);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tarifa total: $" + tarifa);
    }
    
    // SIA1.8.2 - Mostrar listado de elementos (colección anidada)
    public void listarTodasLasReservas() {
        System.out.println("\n--- TODAS LAS RESERVAS ---");
        
        boolean hayReservas = false;
        for (Parque parque : parques) {
            if (!parque.getReservas().isEmpty()) {
                System.out.println("\n️ " + parque.getNombre() + ":");
                for (Reserva reserva : parque.getReservas()) {
                    System.out.println("  " + reserva.toString());
                }
                hayReservas = true;
            }
        }
        
        if (!hayReservas) {
            System.out.println("No hay reservas registradas en el sistema.");
        }
    }
    
    public void listarParques() {
        System.out.println("\n--- PARQUES DISPONIBLES ---");
        for (int i = 0; i < parques.size(); i++) {
            System.out.println((i + 1) + ". " + parques.get(i).toString());
        }
    }
    }