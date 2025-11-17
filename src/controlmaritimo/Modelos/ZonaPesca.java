/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Modelos;

/**
 *
 * @author DELL
 */

import java.util.ArrayList;
import java.util.List;

public class ZonaPesca {
    private String claveGeolocalizacion;
    private double latitud;
    private double longitud;
    private double radioKm;
    private List<String> especiesPeces;
    private List<TrabajoPesca> trabajosPesca;
    
    public ZonaPesca(String claveGeolocalizacion, double latitud, double longitud,
                    double radioKm, List<String> especiesPeces) {
        this.claveGeolocalizacion = claveGeolocalizacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radioKm = radioKm;
        this.especiesPeces = new ArrayList<>(especiesPeces);
        this.trabajosPesca = new ArrayList<>();
    }
    
    public String getClaveGeolocalizacion() { 
        return claveGeolocalizacion; 
    }
    public void setClaveGeolocalizacion(String claveGeolocalizacion) { 
        this.claveGeolocalizacion = claveGeolocalizacion; 
    }
    
    public double getLatitud() { 
        return latitud; 
    }
    public void setLatitud(double latitud) { 
        this.latitud = latitud; 
    }
    
    public double getLongitud() { 
        return longitud; 
    }
    public void setLongitud(double longitud) { 
        this.longitud = longitud; 
    }
    
    public double getRadioKm() { 
        return radioKm; 
    }
    public void setRadioKm(double radioKm) { 
        this.radioKm = radioKm; 
    }
    
    public List<String> getEspeciesPeces() { 
        return new ArrayList<>(especiesPeces); 
    }
    public void setEspeciesPeces(List<String> especiesPeces) { 
        
        this.especiesPeces = new ArrayList<>(especiesPeces); 
    }
    
    public void agregarTrabajo(TrabajoPesca trabajo) {
        this.trabajosPesca.add(trabajo);
    }
    
    public String obtenerInfo() {
        String especies = String.join(", ", especiesPeces);
        return String.format(
            "Clave: %s\nCoordenadas: (%.6f, %.6f)\nRadio: %.2f km\n" +
            "Especies: %s\nBarcos registrados: %d",
            claveGeolocalizacion, latitud, longitud, radioKm, 
            especies, trabajosPesca.size()
        );
    }
    
    public String toCSV() {
        String especies = String.join(";", especiesPeces);
        return String.format("%s,%.6f,%.6f,%.2f,%s",
            claveGeolocalizacion, latitud, longitud, radioKm, especies);
    }
    
    @Override
    public String toString() {
        return String.format("Zona %s", claveGeolocalizacion);
    }
}
