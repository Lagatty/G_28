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
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Arrays;

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
    // SIA 2.8 - Método con manejo de excepciones usando las 2 clases del SIA 2.9
    public void crearReserva(String rut, String nombre, String fecha, int numParque, int tipoAlojamiento, boolean conGuia) 
            throws ReservaException, CapacidadExcedidaException, DatosInvalidosException {
        
        try {
            // Validar datos del cliente usando DatosInvalidosException
            validarDatosCliente(rut, nombre, fecha);
            
            // Validar parque seleccionado
            if (numParque < 0 || numParque >= parques.size()) {
                throw new ReservaException("Número de parque inválido: " + numParque);
            }
            
            Parque parqueSeleccionado = parques.get(numParque);
            
            // Verificar capacidad usando CapacidadExcedidaException
            if (parqueSeleccionado.getReservas().size() >= parqueSeleccionado.getCapacidadMaxima()) {
                throw new CapacidadExcedidaException(
                    "El parque ha alcanzado su capacidad máxima",
                    parqueSeleccionado.getNombre(),
                    parqueSeleccionado.getCapacidadMaxima(),
                    parqueSeleccionado.getReservas().size());
            }
            
            // Crear persona
            Persona persona = new Persona(rut.trim(), nombre.trim());
            
            // Determinar tipo de reserva y servicios
            String tipoReserva;
            List<String> servicios = new ArrayList<>();
            
            if (tipoAlojamiento == 0) { // CABANA
                tipoReserva = "CABANA";
                servicios.add("CABANA");
            } else { // CAMPING
                tipoReserva = "CAMPING";
                servicios.add("CAMPING");
            }
            
            // Agregar guía si se solicita
            if (conGuia) {
                servicios.add("GUIA");
                tipoReserva += " + GUIA";
            }
            
            // Calcular tarifa
            float tarifa = calculadora.calcularTarifa(servicios);
            
            if (tarifa <= 0) {
                throw new ReservaException("Error al calcular la tarifa del servicio");
            }
            
            // Crear y agregar reserva
            parqueSeleccionado.agregarReserva(fecha, tipoReserva, persona, tarifa);
            
            System.out.println("Reserva creada exitosamente en " + parqueSeleccionado.getNombre());
            
        } catch (CapacidadExcedidaException | DatosInvalidosException e) {
            // Re-lanzar las excepciones específicas
            throw e;
        } catch (ReservaException e) {
            // Re-lanzar la excepción de reserva
            throw e;
        } catch (Exception e) {
            // Capturar cualquier otra excepción inesperada
            throw new ReservaException("Error inesperado al crear la reserva: " + e.getMessage(), e);
        }
    }
    
    // Método para validar datos usando DatosInvalidosException
    private void validarDatosCliente(String rut, String nombre, String fecha) throws DatosInvalidosException {
        
        // Validar RUT
        if (rut == null || rut.trim().isEmpty()) {
            throw new DatosInvalidosException("El RUT es obligatorio", "RUT", rut, "12345678-9");
        }
        
        String rutLimpio = rut.trim();
        if (!rutLimpio.matches("\\d{1,8}-[\\dkK]")) {
            throw new DatosInvalidosException("Formato de RUT inválido", "RUT", rut, "12345678-9 o 12345678-K");
        }
        
        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre es obligatorio", "Nombre", nombre, "Solo letras y espacios");
        }
        
        String nombreLimpio = nombre.trim();
        if (nombreLimpio.length() < 2) {
            throw new DatosInvalidosException("El nombre debe tener al menos 2 caracteres", "Nombre", nombre, "Mínimo 2 caracteres");
        }
        
        if (!nombreLimpio.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            throw new DatosInvalidosException("El nombre solo puede contener letras y espacios", "Nombre", nombre, "Solo letras y espacios");
        }
        
        // Validar fecha
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new DatosInvalidosException("La fecha es obligatoria", "Fecha", fecha, "DD/MM/YYYY");
        }
        
        String fechaLimpia = fecha.trim();
        if (!fechaLimpia.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new DatosInvalidosException("Formato de fecha inválido", "Fecha", fecha, "DD/MM/YYYY");
        }
        
        // Validación adicional de fecha
        try {
            String[] partes = fechaLimpia.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int año = Integer.parseInt(partes[2]);
            
            if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
                throw new DatosInvalidosException("Fecha con valores inválidos", "Fecha", fecha, "Día: 1-31, Mes: 1-12");
            }
            
            if (año < 2024 || año > 2030) {
                throw new DatosInvalidosException("Año fuera del rango permitido", "Fecha", fecha, "Año entre 2024-2030");
            }
            
        } catch (NumberFormatException e) {
            throw new DatosInvalidosException("Error al interpretar la fecha", "Fecha", fecha, "DD/MM/YYYY con números válidos");
        }
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
    
    public void guardarReservasEnCSV(String nombreArchivo) {
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("Reservas"); // encabezado
            
            for (Parque parque : parques) {
                
                if (!parque.getReservas().isEmpty()) {

                    for (Reserva reserva : parque.getReservas()) {
                        writer.println(reserva.toString());
                        
                    }

                }
        }
            System.out.println("Archivo CSV guardado: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        //Búsqueda de parques por nombre
    public String buscarParquePorNombre(String nombre) {
        for (Parque parque : parques) {
            if (parque.getNombre().equalsIgnoreCase(nombre)) {
                
                return parque.toString();
            }
        }
        return "No encontrado"; 
    }

    //Búsqueda de reservas por ID
    public Reserva buscarReservaPorId(String id) {
        for (Parque parque : parques) {
            for (Reserva reserva : parque.getReservas()) {
                if (reserva.getId().equalsIgnoreCase(id)) {
                    return reserva;
                }
            }
        }
        return null; 
    }

    //Búsqueda de reservas por RUT del cliente
    public ArrayList<Reserva> buscarReservasPorRut(String rut) {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();

        for (Parque parque : parques) {
            for (Reserva reserva : parque.getReservas()) {
                if (reserva.getPersona().getRut().equalsIgnoreCase(rut)) {
                    reservasEncontradas.add(reserva);
                }
            }
        }
        return reservasEncontradas;
    }

    //Búsqueda de reservas por nombre del cliente
    public ArrayList<Reserva> buscarReservasPorNombre(String nombre) {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();

        for (Parque parque : parques) {
            for (Reserva reserva : parque.getReservas()) {
                if (reserva.getPersona().getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                    reservasEncontradas.add(reserva);
                }
            }
        }
        return reservasEncontradas;
    }
    
    //// SIA 2.5 - Filtrado específico del sistema: Reservas Premium por tarifa alta
    // Considera la selección de un subconjunto filtrado por criterio específico,
    // involucrando 1 o más colecciones, similar a "selección de alumnos con nota final entre 4.0 y 7.0"
    
    public String filtrarReservasPremium() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("🏆 RESERVAS PREMIUM (TARIFA > $50.000):\n");
        resultado.append("═══════════════════════════════════════════\n\n");
        
        boolean encontradas = false;
        double totalIngresosPremium = 0;
        int contadorReservasPremium = 0;
        
        // Iterar sobre la colección de parques y sus colecciones anidadas de reservas
        for (Parque parque : this.getParques()) {
            List<Reserva> reservasPremiumParque = new ArrayList<>();
            
            // Filtrar reservas premium del parque actual
            for (Reserva reserva : parque.getReservas()) {
                if (reserva.getTarifa() > 50000) {
                    reservasPremiumParque.add(reserva);
                    totalIngresosPremium += reserva.getTarifa();
                    contadorReservasPremium++;
                }
            }
            
            // Si encontramos reservas premium en este parque, las mostramos
            if (!reservasPremiumParque.isEmpty()) {
                resultado.append("🏞️ PARQUE: ").append(parque.getNombre().toUpperCase()).append("\n");
                resultado.append("📍 Ubicación: ").append(parque.getUbicacion()).append("\n");
                resultado.append("💎 Reservas Premium: ").append(reservasPremiumParque.size()).append("/").append(parque.getReservas().size()).append("\n\n");
                
                for (Reserva reserva : reservasPremiumParque) {
                    resultado.append("   ✨ ").append(reserva.toString()).append("\n");
                }
                resultado.append("\n");
                encontradas = true;
            }
        }
        
        if (!encontradas) {
            resultado.append("❌ No se encontraron reservas premium en el sistema.\n");
            resultado.append("💡 Las reservas premium son aquellas con tarifa superior a $50.000\n");
        } else {
            // Estadísticas del filtrado
            resultado.append("📊 RESUMEN DE RESERVAS PREMIUM:\n");
            resultado.append("═══════════════════════════════════════\n");
            resultado.append("Total reservas premium: ").append(contadorReservasPremium).append("\n");
            resultado.append("Ingresos totales premium: $").append(String.format("%.0f", totalIngresosPremium)).append("\n");
            resultado.append("Promedio tarifa premium: $").append(String.format("%.0f", totalIngresosPremium / contadorReservasPremium)).append("\n");
            
            // Calcular porcentaje de reservas premium
            int totalReservas = 0;
            for (Parque parque : this.getParques()) {
                totalReservas += parque.getReservas().size();
            }
            if (totalReservas > 0) {
                double porcentajePremium = (double) contadorReservasPremium / totalReservas * 100;
                resultado.append("Porcentaje de reservas premium: ").append(String.format("%.1f%%", porcentajePremium)).append("\n");
            }
        }
        
        return resultado.toString();
    }
    
    public String mostrarEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("ESTADÍSTICAS DEL SISTEMA:\n\n");
        
        int totalReservas = 0;
        double ingresoTotal = 0;
        
        for (Parque parque : this.getParques()) {
            int reservasParque = parque.getReservas().size();
            totalReservas += reservasParque;
            
            double ingresoParque = parque.getReservas().stream()
                .mapToDouble(Reserva::getTarifa)
                .sum();
            ingresoTotal += ingresoParque;
            
            stats.append(parque.getNombre()).append(":\n");
            stats.append("  Reservas: ").append(reservasParque).append("\n");
            stats.append("  Ingresos: $").append(String.format("%.0f", ingresoParque)).append("\n\n");
        }
        
        stats.append("TOTALES:\n");
        stats.append("Total de reservas: ").append(totalReservas).append("\n");
        stats.append("Ingresos totales: $").append(String.format("%.0f", ingresoTotal)).append("\n");
        
        return stats.toString();
    }
}
    
    