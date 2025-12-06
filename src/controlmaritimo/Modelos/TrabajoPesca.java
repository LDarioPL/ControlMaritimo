/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Modelos;

/**
 *
 * @author DELL
 */

import controlmaritimo.barcos.BarcoPesca;
import java.time.LocalDateTime;

public class TrabajoPesca {
    private BarcoPesca barco;
    private ZonaPesca zona;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    
    public TrabajoPesca(BarcoPesca barco, ZonaPesca zona, 
                       LocalDateTime fechaHoraEntrada) {
        this.barco = barco;
        this.zona = zona;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.fechaHoraSalida = null;
    }
    
    public BarcoPesca getBarco() { return barco; }
    public ZonaPesca getZona() { return zona; }
    public LocalDateTime getFechaHoraEntrada() { return fechaHoraEntrada; }
    public LocalDateTime getFechaHoraSalida() { return fechaHoraSalida; }
    
    public void registrarSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
    
    public String obtenerInfo() {
        String salida = (fechaHoraSalida != null) ? fechaHoraSalida.toString() : "En curso";
        return String.format(
            "Barco: %s (%s)\nZona: %s\nEntrada: %s\nSalida: %s",
            barco.getNombre(), barco.getMatricula(), 
            zona.getClaveGeolocalizacion(), fechaHoraEntrada, salida
        );
    }
    
    public String toCSV() {
        String salida = (fechaHoraSalida != null) ? fechaHoraSalida.toString() : "NULL";
        return String.format("%s,%s,%s,%s",
            barco.getMatricula(), zona.getClaveGeolocalizacion(), 
            fechaHoraEntrada, salida);
    }
}

