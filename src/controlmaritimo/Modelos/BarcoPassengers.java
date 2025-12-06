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
import java.time.LocalDate;

public class BarcoPassengers extends Barco {
    private int cantidadPasajeros;
    private int numeroPisos;
    private String tipoViajes;
    
    public BarcoPassengers(String matricula, String bandera, String nombre,
                          double pesoToneladas, LocalDate fechaBotadura,
                          int cantidadPasajeros, int numeroPisos, String tipoViajes) {
        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.cantidadPasajeros = cantidadPasajeros;
        this.numeroPisos = numeroPisos;
        this.tipoViajes = tipoViajes;
    }
    
    public int getCantidadPasajeros() { return cantidadPasajeros; }
    public void setCantidadPasajeros(int cantidadPasajeros) { 
        this.cantidadPasajeros = cantidadPasajeros; 
    }
    
    public int getNumeroPisos() { return numeroPisos; }
    public void setNumeroPisos(int numeroPisos) { this.numeroPisos = numeroPisos; }
    
    public String getTipoViajes() { return tipoViajes; }
    public void setTipoViajes(String tipoViajes) { this.tipoViajes = tipoViajes; }
    
    @Override
    public String getTipoBarco() { return "PASAJEROS"; }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + String.format(
            "\nCapacidad: %d pasajeros\nNúmero de pisos: %d\nTipo de viajes: %s",
            cantidadPasajeros, numeroPisos, tipoViajes
        );
    }
    
    @Override
    public String toCSV() {
        return String.format("%s,%d,%d,%s",
            super.toCSV(), cantidadPasajeros, numeroPisos, tipoViajes);
    }
}

