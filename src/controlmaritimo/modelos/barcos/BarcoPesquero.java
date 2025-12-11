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
 * Esta clase hereda de la clase Barco y declara los atributos únicos y
 * pertenecientes a un barco pesquero.
 *
 * @version 1.5 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class BarcoPesquero extends Barco {

    // Atriburos propios de un barco pesquero
    private double limiteToneladas;
    private double numToneladas;
    private List<String> tiposPescado;
    private List<ConocimientoEmbarque> trabajosPesca;

    // Constructor sobrecargado
    public BarcoPesquero(String matricula, String bandera, String nombre,
            double pesoToneladas, LocalDate fechaBotadura, double limiteToneladas,
            double numToneladas, List<String> tiposPescado) {

        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.limiteToneladas = limiteToneladas;
        this.numToneladas = numToneladas;
        this.tiposPescado = new ArrayList<>(tiposPescado);
        this.trabajosPesca = new ArrayList<>();
    }

    //Metodos getters y setters
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

    /**
     * Método que retorna el tipo de barco.
     *
     * @return String refiriendose al tipo de barco.
     */
    @Override
    public String getTipoBarco() {
        return "Barco de pesca";
    }

    /**
     * Método que retorna la información (atributos) propia de un barco
     * pesquero.
     *
     * @return String que contiene la información de este tipo de barco.
     */
    @Override
    public String obtenerInformacionBarco() {
        String tipos = String.join(", ", tiposPescado);
        return super.obtenerInformacionBarco() + String.format(
                "\nTipos de pescado: %s\nCapacidad máxima: %.2f toneladas \nCantidad actual: %.2f toneladas \nZonas trabajadas: %d",
                tipos, limiteToneladas, numToneladas, trabajosPesca.size()
        );
    }

}
