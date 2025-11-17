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

public class BarcoCarga extends Barco {
    private int cantidadContenedores;
    
    public BarcoCarga(String matricula, String bandera, String nombre,
                     double pesoToneladas, LocalDate fechaBotadura,
                     int cantidadContenedores) {
        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.cantidadContenedores = cantidadContenedores;
    }
    
    public int getCantidadContenedores() { return cantidadContenedores; }
    public void setCantidadContenedores(int cantidadContenedores) { 
        this.cantidadContenedores = cantidadContenedores; 
    }
    
    @Override
    public String getTipoBarco() { return "CARGA"; }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + String.format(
            "\nCapacidad: %d contenedores",
            cantidadContenedores
        );
    }
    
    @Override
    public String toCSV() {
        return String.format("%s,%d",
            super.toCSV(), cantidadContenedores);
    }
}