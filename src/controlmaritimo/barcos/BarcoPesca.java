/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.barcos;

import controlmaritimo.Modelos.TrabajoPesca;
import controlmaritimo.abstracciones.Barco;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazid
 */
public class BarcoPesca extends Barco {

    private List<String> tiposPescado;
    private double limiteToneladas;
    private double numToneladas;

    private List<TrabajoPesca> trabajosPesca;

    public BarcoPesca(String matricula, String bandera, String nombre,
            double pesoToneladas, LocalDate fechaBotadura,
            List<String> tiposPescado, double limiteToneladas,
            double numToneladas) {

        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.tiposPescado = new ArrayList<>(tiposPescado);
        this.limiteToneladas = limiteToneladas;
        this.trabajosPesca = new ArrayList<>();
        this.numToneladas = numToneladas;
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

    public List<TrabajoPesca> getTrabajosPesca() {
        return trabajosPesca;
    }

    public void setTrabajosPesca(List<TrabajoPesca> trabajosPesca) {
        this.trabajosPesca = trabajosPesca;
    }

    public List<String> getTiposPescado() {
        return new ArrayList<>(tiposPescado);
    }

    public void setTiposPescado(List<String> tiposPescado) {
        this.tiposPescado = new ArrayList<>(tiposPescado);
    }

    public void agregarTrabajoPesca(TrabajoPesca trabajo) {
        this.trabajosPesca.add(trabajo);
    }

    public List<TrabajoPesca> obtenerTrabajos() {
        return new ArrayList<>(trabajosPesca);
    }

    @Override
    public String getTipoBarco() {
        return "PESCA";
    }

    @Override
    public String obtenerInformacionBarco() {
        String tipos = String.join(", ", tiposPescado);
        return super.obtenerInformacionBarco() + String.format(
                "\nTipos de pescado: %s\nCapacidad: %.2f toneladas\nZonas trabajadas: %d",
                tipos, limiteToneladas, trabajosPesca.size()
        );
    }

}
