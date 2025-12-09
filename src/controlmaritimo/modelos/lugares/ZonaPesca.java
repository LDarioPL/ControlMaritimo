/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.lugares;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazid
 */
public class ZonaPesca {
    
    private String claveGeolocalizacion;
    private Double latitud;
    private Double longitud;
    private Double radioKm;
    private List<String> especiesPez;

    public ZonaPesca(String claveGeolocalizacion, double latitud, double longitud,
                     double radioKm, List<String> especiesPez) {

        this.claveGeolocalizacion = claveGeolocalizacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radioKm = radioKm;
        this.especiesPez = new ArrayList<>(especiesPez);
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
        return new ArrayList<>(especiesPez);
    }

    public void setEspeciesPeces(List<String> especiesPez) {
        this.especiesPez = new ArrayList<>(especiesPez);
    }

    public String obtenerInfo() {
        String especies = String.join(", ", especiesPez);
        return String.format("""
                             Clave: %s
                             Coordenadas: (%.6f, %.6f)
                             Radio: %.2f km
                             Especies: %s""",
                claveGeolocalizacion, latitud, longitud, radioKm,
                especies
        );
    }
    
}
