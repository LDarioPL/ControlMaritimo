/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.documentos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.modelos.lugares.Puerto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazid
 */
public class DocumentoAtraque {
    
    private Barco barco;
    private Puerto puerto;
    private LocalDate fechaAtraque;
    private List<TransaccionMercantil> transacciones;
    
    public DocumentoAtraque(Barco barco, Puerto puerto, LocalDate fechaAtraque) {
        this.barco = barco;
        this.puerto = puerto;
        this.fechaAtraque = fechaAtraque;
        this.transacciones = new ArrayList<>();
    }
    
    public Barco getBarco() { return barco; }
    public Puerto getPuerto() { return puerto; }
    public LocalDate getFechaAtraque() { return fechaAtraque; }
    
    public void agregarTransaccion(TransaccionMercantil transaccion) {
        this.transacciones.add(transaccion);
    }
    
    public List<TransaccionMercantil> obtenerTransacciones() {
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
