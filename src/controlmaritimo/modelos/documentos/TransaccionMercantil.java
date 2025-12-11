/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.documentos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.modelos.barcos.BarcoCarga;
import controlmaritimo.modelos.barcos.BarcoPasajeros;
import controlmaritimo.modelos.barcos.BarcoPesquero;
import java.time.LocalDateTime;

/**
 * Esta clase declara los atributos propios de un documento de Transacción
 * Mercantil, incluyendo atributos como una clave de transaccion, fecha en la
 * que se realizo, etc.
 *
 * @version 1.3
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class TransaccionMercantil {
    
    // Atributos propios del documento Transacción Mercantil
    private String claveTransaccion;
    private LocalDateTime fechaHora;
    private String tipoTransaccion;
    private double cantidad;
    private DocumentoAtraque atraque;

    // Constructor sobrecargado
    public TransaccionMercantil(String claveTransaccion, LocalDateTime fechaHora,
            String tipoTransaccion, double cantidad, DocumentoAtraque atraque) {
        this.claveTransaccion = claveTransaccion;
        this.fechaHora = fechaHora;
        this.tipoTransaccion = tipoTransaccion.toUpperCase();
        this.cantidad = cantidad;
        this.atraque = atraque;
    }
    
    // Metodos getters y setters
    public String getClaveTransaccion() {
        return claveTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public DocumentoAtraque getAtraque() {
        return atraque;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion.toUpperCase();
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Método que retorna la unidad que carga el barco según su tipo, ejemplo:
     * pasajeros, toneladas (de pescado), contenedores.
     * 
     * @return String que muestra la unidad que carga el barco según su tipo.
     */
    private String obtenerUnidad() {
        Barco barco = atraque.getBarco();
        if (barco instanceof BarcoPasajeros) {
            return "pasajeros";
        } else if (barco instanceof BarcoPesquero) {
            return "toneladas";
        } else if (barco instanceof BarcoCarga) {
            return "contenedores";
        }
        return "unidades";
    }
    
    /**
     * Método que retorna en un mensaje el contenido del documento.
     * 
     * @return String con la información del documento.
     */
    public String obtenerInfo() {
        return String.format(
                "Clave: %s\nTipo: %s\nFecha/Hora: %s\nCantidad: %.2f %s\n"
                + "Barco: %s\nPuerto: %s",
                claveTransaccion, tipoTransaccion, fechaHora, cantidad, obtenerUnidad(),
                atraque.getBarco().getNombre(), atraque.getPuerto().getNombre()
        );
    }

}
