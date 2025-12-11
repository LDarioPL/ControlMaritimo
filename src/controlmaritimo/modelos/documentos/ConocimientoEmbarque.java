/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.documentos;

import controlmaritimo.modelos.barcos.BarcoPesquero;
import controlmaritimo.modelos.lugares.ZonaPesca;
import java.time.LocalDateTime;

/**
 * Clase de declara los atributos propios de un documento de Conocimiento de
 * Embarque, en el se almacena el barco pesquero que arrivo a la zona de pesca,
 * así como a la misma zona, se incluye una fecha de hora y sálida.
 * 
 * @version 1.4 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class ConocimientoEmbarque {
    
    // Atributos propios del documento Conocimiento de Embarque
    private BarcoPesquero barco;
    private ZonaPesca zona;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    
    // Constructor sobrecargado
    public ConocimientoEmbarque(BarcoPesquero barco, ZonaPesca zona,
            LocalDateTime fechaHoraEntrada) {
        this.barco = barco;
        this.zona = zona;
        this.fechaHoraEntrada = fechaHoraEntrada;
        this.fechaHoraSalida = null;
    }
    
    // Metodos getters y setters
    public BarcoPesquero getBarco() {
        return barco;
    }

    public ZonaPesca getZona() {
        return zona;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void registrarSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }
    
    /**
     * Método que retorna en un mensaje el contenido del documento.
     * 
     * @return String con la información del documento.
     */
    public String obtenerInfo() {
        String salida = (fechaHoraSalida != null) ? fechaHoraSalida.toString() : "En curso";
        return String.format(
                "Barco: %s (%s)\nZona: %s\nEntrada: %s\nSalida: %s",
                barco.getNombre(), barco.getMatricula(),
                zona.getClaveGeolocalizacion(), fechaHoraEntrada, salida
        );
    }

}
