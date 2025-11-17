/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Modelos;

/**
 *
 * @author DELL
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Atraque {
    private Barco barco;
    private Puerto puerto;
    private LocalDate fechaAtraque;
    private List<Transaccion> transacciones;
    
    public Atraque(Barco barco, Puerto puerto, LocalDate fechaAtraque) {
        this.barco = barco;
        this.puerto = puerto;
        this.fechaAtraque = fechaAtraque;
        this.transacciones = new ArrayList<>();
    }
    
    public Barco getBarco() { return barco; }
    public Puerto getPuerto() { return puerto; }
    public LocalDate getFechaAtraque() { return fechaAtraque; }
    
    public void agregarTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }
    
    public List<Transaccion> obtenerTransacciones() {
        return new ArrayList<>(transacciones);
    }
    
    public String obtenerInfo() {
        return String.format(
            "Barco: %s (%s)\nPuerto: %s (%s)\nFecha: %s\nTransacciones: %d",
            barco.getNombre(), barco.getMatricula(),
            puerto.getNombre(), puerto.getCodigo(),
            fechaAtraque, transacciones.size()
        );
    }
    
    public String toCSV() {
        return String.format("%s,%s,%s",
            barco.getMatricula(), puerto.getCodigo(), fechaAtraque);
    }
}