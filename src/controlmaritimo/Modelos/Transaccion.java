/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Modelos;

/**
 *
 * @author DELL
 */

import controlmaritimo.abstracciones.Barco;
import controlmaritimo.barcos.BarcoCarga;
import controlmaritimo.barcos.BarcoPasajeros;
import controlmaritimo.barcos.BarcoPesca;
import java.time.LocalDateTime;

public class Transaccion {
    private String claveTransaccion;
    private LocalDateTime fechaHora;
    private String tipoTransaccion;
    private double cantidad;
    private Atraque atraque;
    
    public Transaccion(String claveTransaccion, LocalDateTime fechaHora,
                      String tipoTransaccion, double cantidad, Atraque atraque) {
        this.claveTransaccion = claveTransaccion;
        this.fechaHora = fechaHora;
        this.tipoTransaccion = tipoTransaccion.toUpperCase();
        this.cantidad = cantidad;
        this.atraque = atraque;
    }
    
    public String getClaveTransaccion() { return claveTransaccion; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getTipoTransaccion() { return tipoTransaccion; }
    public double getCantidad() { return cantidad; }
    public Atraque getAtraque() { return atraque; }
    
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public void setTipoTransaccion(String tipoTransaccion) { 
        this.tipoTransaccion = tipoTransaccion.toUpperCase(); 
    }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
    
    private String obtenerUnidad() {
        Barco barco = atraque.getBarco();
        if (barco instanceof BarcoPasajeros) {
            return "pasajeros";
        } else if (barco instanceof BarcoPesca) {
            return "toneladas";
        } else if (barco instanceof BarcoCarga) {
            return "contenedores";
        }
        return "unidades";
    }
    
    public String obtenerInfo() {
        return String.format(
            "Clave: %s\nTipo: %s\nFecha/Hora: %s\nCantidad: %.2f %s\n" +
            "Barco: %s\nPuerto: %s",
            claveTransaccion, tipoTransaccion, fechaHora, cantidad, obtenerUnidad(),
            atraque.getBarco().getNombre(), atraque.getPuerto().getNombre()
        );
    }
    
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%s,%s",
            claveTransaccion, fechaHora, tipoTransaccion, cantidad,
            atraque.getBarco().getMatricula(), atraque.getPuerto().getCodigo());
    }
}
