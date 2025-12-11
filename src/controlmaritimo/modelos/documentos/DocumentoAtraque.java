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
 * Esta clase declara todos los atributos propios de un Documento de Atraque,
 * aquel que se genera cuando un barco llega a un puerto, el documento incluye
 * el barco, el puerto donde se arrivo, una fecha y una lista de transacciones.
 *
 * @version 1.4 10/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class DocumentoAtraque {

    // Atributos propios del documento Documento de Atraque
    private Barco barco;
    private Puerto puerto;
    private LocalDate fechaAtraque;
    private List<TransaccionMercantil> transacciones;

    // Constructor sobrecargado
    public DocumentoAtraque(Barco barco, Puerto puerto, LocalDate fechaAtraque) {
        this.barco = barco;
        this.puerto = puerto;
        this.fechaAtraque = fechaAtraque;
        this.transacciones = new ArrayList<>();
    }
    
    // Metodos setters y getters
    public Barco getBarco() {
        return barco;
    }

    public Puerto getPuerto() {
        return puerto;
    }

    public LocalDate getFechaAtraque() {
        return fechaAtraque;
    }

    public void agregarTransaccion(TransaccionMercantil transaccion) {
        this.transacciones.add(transaccion);
    }

    public List<TransaccionMercantil> obtenerTransacciones() {
        return new ArrayList<>(transacciones);
    }
    
    /**
     * Método que retorna en un mensaje el contenido del documento.
     * 
     * @return String con la información del documento.
     */
    public String obtenerInfo() {
        return String.format(
                "Barco: %s (%s)\nPuerto: %s (%s)\nFecha: %s\nTransacciones: %d",
                barco.getNombre(), barco.getMatricula(),
                puerto.getNombre(), puerto.getCodigo(),
                fechaAtraque, transacciones.size()
        );
    }

}
