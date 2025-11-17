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

public class BarcoPesca extends Barco {
    private List<String> tiposPescado;
    private double capacidadToneladas;
    private List<TrabajoPesca> trabajosPesca;
    
    public BarcoPesca(String matricula, String bandera, String nombre,
                     double pesoToneladas, LocalDate fechaBotadura,
                     List<String> tiposPescado, double capacidadToneladas) {
        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.tiposPescado = new ArrayList<>(tiposPescado);
        this.capacidadToneladas = capacidadToneladas;
        this.trabajosPesca = new ArrayList<>();
    }
    
    public List<String> getTiposPescado() { return new ArrayList<>(tiposPescado); }
    public void setTiposPescado(List<String> tiposPescado) { 
        this.tiposPescado = new ArrayList<>(tiposPescado); 
    }
    
    public double getCapacidadToneladas() { return capacidadToneladas; }
    public void setCapacidadToneladas(double capacidadToneladas) { 
        this.capacidadToneladas = capacidadToneladas; 
    }
    
    public void agregarTrabajoPesca(TrabajoPesca trabajo) {
        this.trabajosPesca.add(trabajo);
    }
    
    public List<TrabajoPesca> obtenerTrabajos() {
        return new ArrayList<>(trabajosPesca);
    }
    
    @Override
    public String getTipoBarco() { return "PESCA"; }
    
    @Override
    public String obtenerInfo() {
        String tipos = String.join(", ", tiposPescado);
        return super.obtenerInfo() + String.format(
            "\nTipos de pescado: %s\nCapacidad: %.2f toneladas\nZonas trabajadas: %d",
            tipos, capacidadToneladas, trabajosPesca.size()
        );
    }
    
    @Override
    public String toCSV() {
        String tipos = String.join(";", tiposPescado);
        return String.format("%s,%s,%.2f",
            super.toCSV(), tipos, capacidadToneladas);
    }
}