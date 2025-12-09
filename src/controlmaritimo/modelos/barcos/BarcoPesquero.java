/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.modelos.documentos.ConocimientoEmbarque;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazid
 */
public class BarcoPesquero extends Barco {
    
    private double limiteToneladas;
    private double numToneladas;
    private List<String> tiposPescado;
    private List<ConocimientoEmbarque> trabajosPesca;
    
    public BarcoPesquero(String matricula, String bandera, String nombre,
            double pesoToneladas, LocalDate fechaBotadura, double limiteToneladas,
            double numToneladas, List<String> tiposPescado) {

        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.limiteToneladas = limiteToneladas;
        this.numToneladas = numToneladas;        
        this.tiposPescado = new ArrayList<>(tiposPescado);
        this.trabajosPesca = new ArrayList<>();
    }

    public double getLimiteToneladas() {
        return limiteToneladas;
    }

    public void setLimiteToneladas(double limiteToneladas) {
        this.limiteToneladas = limiteToneladas;
    }

    public double getNumToneladas() {
        return numToneladas;
    }

    public void setNumToneladas(double numToneladas) {
        this.numToneladas = numToneladas;
    }

    public List<ConocimientoEmbarque> getTrabajosPesca() {
        return trabajosPesca;
    }

    public void setTrabajosPesca(List<ConocimientoEmbarque> trabajosPesca) {
        this.trabajosPesca = trabajosPesca;
    }

    public List<String> getTiposPescado() {
        return new ArrayList<>(tiposPescado);
    }

    public void setTiposPescado(List<String> tiposPescado) {
        this.tiposPescado = new ArrayList<>(tiposPescado);
    }

    public void agregarTrabajoPesca(ConocimientoEmbarque trabajo) {
        this.trabajosPesca.add(trabajo);
    }

    public List<ConocimientoEmbarque> obtenerTrabajos() {
        return new ArrayList<>(trabajosPesca);
    }

    @Override
    public String getTipoBarco() {
        return "Barco de pesca";
    }

    @Override
    public String obtenerInformacionBarco() {
        String tipos = String.join(", ", tiposPescado);
        return super.obtenerInformacionBarco() + String.format(
                "\nTipos de pescado: %s\nCapacidad máxima: %.2f toneladas \nCantidad actual: %.2f toneladas \nZonas trabajadas: %d",
                tipos, limiteToneladas, numToneladas, trabajosPesca.size()
        );
    }
    
}
