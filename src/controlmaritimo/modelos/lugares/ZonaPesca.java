/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.lugares;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase declara todos los atributos propios de una zona de pesca,
 * incluyendo datos como la clave de geolocalización, latitud, longitud, el
 * radio en kilometros así como una lista de especies que habitan en la zona.
 *
 * @version 1.3 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class ZonaPesca {

    // Atributos propios de la zona de pesca
    private String claveGeolocalizacion;
    private Double latitud;
    private Double longitud;
    private Double radioKm;
    private List<String> especiesPez;

    // Método constructor sobrecargado
    public ZonaPesca(String claveGeolocalizacion, double latitud, double longitud,
            double radioKm, List<String> especiesPez) {

        this.claveGeolocalizacion = claveGeolocalizacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radioKm = radioKm;
        this.especiesPez = new ArrayList<>(especiesPez);
    }

    // Metodos getters y setters
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

    /**
     * Método que retorna un mensaje con la información de la zona de pesca.
     *
     * @return String con la información de la zona de pesca
     */
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
