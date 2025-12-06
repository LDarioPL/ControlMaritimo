/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Servicios;

import controlmaritimo.abstracciones.Barco;
import controlmaritimo.Modelos.*;
import controlmaritimo.Repositorio.*;
import controlmaritimo.barcos.BarcoPesca;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author DELL
 */


/**
 * Clase principal que gestiona el sistema marítimo usando servicios.
 * Implementa el patrón Singleton.
 */
public class SistemaMaritimo {
    private static SistemaMaritimo instancia;
    
    private BarcoServicio barcoServicio;
    private PuertoServicio puertoServicio;
    private ZonaPescaServicio zonaPescaServicio;
    
    private SistemaMaritimo() {
        // Inicializar servicios con repositorios TXT
        this.barcoServicio = new BarcoServicio(new BarcoRepositorioTXT());
        this.puertoServicio = new PuertoServicio(new PuertoRepositorioTXT());
        this.zonaPescaServicio = new ZonaPescaServicio(new ZonaPescaRepositorioTXT());
    }
    
    public static SistemaMaritimo obtenerInstancia() {
        if (instancia == null) {
            instancia = new SistemaMaritimo();
        }
        return instancia;
    }
    
    // ========== MÉTODOS DE BARCOS ==========
    
    public boolean registrarBarco(Barco barco) {
        if (barcoServicio.existeBarco(barco.getMatricula())) {
            return false;
        }
        return barcoServicio.guardar(barco);
    }
    
    public Barco consultarBarco(String matricula) {
        return barcoServicio.buscarPorMatricula(matricula);
    }
    
    public List<Barco> listarBarcos() {
        return barcoServicio.obtenerTodos();
    }
    
    public boolean eliminarBarco(String matricula) {
        List<Barco> barcos = barcoServicio.obtenerTodos();
        for (int i = 0; i < barcos.size(); i++) {
            if (barcos.get(i).getMatricula().equals(matricula)) {
                return barcoServicio.eliminar(i + 1); // índice empieza en 1
            }
        }
        return false;
    }
    
    public boolean existeBarco(String matricula) {
        return barcoServicio.existeBarco(matricula);
    }
    
    // ========== MÉTODOS DE PUERTOS ==========
    
    public boolean registrarPuerto(Puerto puerto) {
        if (puertoServicio.existePuerto(puerto.getCodigo())) {
            return false;
        }
        return puertoServicio.guardar(puerto);
    }
    
    public Puerto consultarPuerto(String codigo) {
        return puertoServicio.buscarPorCodigo(codigo);
    }
    
    public List<Puerto> listarPuertos() {
        return puertoServicio.obtenerTodos();
    }
    
    public boolean eliminarPuerto(String codigo) {
        List<Puerto> puertos = puertoServicio.obtenerTodos();
        for (int i = 0; i < puertos.size(); i++) {
            if (puertos.get(i).getCodigo().equals(codigo)) {
                return puertoServicio.eliminar(i + 1);
            }
        }
        return false;
    }
    
    public boolean existePuerto(String codigo) {
        return puertoServicio.existePuerto(codigo);
    }
    
    // ========== MÉTODOS DE ZONAS DE PESCA ==========
    
    public boolean registrarZonaPesca(ZonaPesca zona) {
        if (zonaPescaServicio.existeZonaPesca(zona.getClaveGeolocalizacion())) {
            return false;
        }
        return zonaPescaServicio.guardar(zona);
    }
    
    public ZonaPesca consultarZonaPesca(String clave) {
        return zonaPescaServicio.buscarPorClave(clave);
    }
    
    public List<ZonaPesca> listarZonasPesca() {
        return zonaPescaServicio.obtenerTodos();
    }
    
    public boolean eliminarZonaPesca(String clave) {
        List<ZonaPesca> zonas = zonaPescaServicio.obtenerTodos();
        for (int i = 0; i < zonas.size(); i++) {
            if (zonas.get(i).getClaveGeolocalizacion().equals(clave)) {
                return zonaPescaServicio.eliminar(i + 1);
            }
        }
        return false;
    }
    
    public boolean existeZonaPesca(String clave) {
        return zonaPescaServicio.existeZonaPesca(clave);
    }
    
    // ========== GESTIÓN DE ATRAQUES ==========
    
    public Atraque registrarAtraque(String matricula, String codigoPuerto, LocalDate fecha) {
        Barco barco = consultarBarco(matricula);
        Puerto puerto = consultarPuerto(codigoPuerto);
        
        if (barco == null || puerto == null) {
            return null;
        }
        
        Atraque atraque = new Atraque(barco, puerto, fecha);
        barco.agregarAtraque(atraque);
        puerto.agregarAtraque(atraque);
        
        return atraque;
    }
    
    // ========== GESTIÓN DE TRABAJOS DE PESCA ==========
    
    public TrabajoPesca registrarTrabajoPesca(String matricula, String claveZona, LocalDateTime entrada) {
        Barco barco = consultarBarco(matricula);
        ZonaPesca zona = consultarZonaPesca(claveZona);
        
        if (!(barco instanceof BarcoPesca) || zona == null) {
            return null;
        }
        
        BarcoPesca barcoPesca = (BarcoPesca) barco;
        TrabajoPesca trabajo = new TrabajoPesca(barcoPesca, zona, entrada);
        
        barcoPesca.agregarTrabajoPesca(trabajo);
        zona.agregarTrabajo(trabajo);
        
        return trabajo;
    }
    
    // ========== ESTADÍSTICAS ==========
    
    public void mostrarEstadisticas() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║      ESTADÍSTICAS DEL SISTEMA          ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Barcos registrados: " + listarBarcos().size());
        System.out.println("Puertos registrados: " + listarPuertos().size());
        System.out.println("Zonas de pesca: " + listarZonasPesca().size());
        System.out.println("Almacenamiento: ARCHIVOS TXT");
    }
}
