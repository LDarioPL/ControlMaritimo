/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import java.time.LocalDate;

/**
 * Esta clase hereda de la clase Barco y declara los atributos únicos y
 * pertenecientes a un barco de pasajeros.
 *
 * @version 1.8 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class BarcoPasajeros extends Barco {

    // Atriburos propios de un barco de pasajeros
    private int limitePasajeros;
    private int cantidadPasajeros;
    private int numeroPisos;
    private String tipoViaje;

    //Constructro sobrecargado
    public BarcoPasajeros(String matricula, String bandera, String nombre,
            double pesoToneladas, LocalDate fechaBotadura,
            int limitePasajeros, int cantidadPasajeros,
            int numeroPisos, String tipoViaje) {

        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.limitePasajeros = limitePasajeros;
        this.cantidadPasajeros = cantidadPasajeros;
        this.numeroPisos = numeroPisos;
        this.tipoViaje = tipoViaje;
    }

    // Metodos getters y setters
    public int getLimitePasajeros() {
        return limitePasajeros;
    }

    public void setLimitePasajeros(int limitePasajeros) {
        this.limitePasajeros = limitePasajeros;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public int getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    /**
     * Método que retorna el tipo de barco.
     *
     * @return String refiriendose al tipo de barco.
     */
    @Override
    public String getTipoBarco() {
        return "Barco de pasajeros";
    }

    /**
     * Método que retorna la información (atributos) propia de un barco de
     * pasajeros.
     *
     * @return String que contiene la información de este tipo de barco.
     */
    @Override
    public String obtenerInformacionBarco() {
        return super.obtenerInformacionBarco() + String.format(
                "\nCapacidad: %d pasajeros\nNúmero de pisos: %d\nTipo de viajes: %s",
                cantidadPasajeros, numeroPisos, tipoViaje
        );
    }

}
