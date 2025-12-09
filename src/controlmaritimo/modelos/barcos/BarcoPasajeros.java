/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import java.time.LocalDate;

/**
 *
 * @author yazid
 */
public class BarcoPasajeros extends Barco {

    private int limitePasajeros;
    private int cantidadPasajeros;
    private int numeroPisos;
    private String tipoViaje;

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

    @Override
    public String getTipoBarco() {
        return "Barco de pasajeros";
    }

    @Override
    public String obtenerInformacionBarco() {
        return super.obtenerInformacionBarco() + String.format(
                "\nCapacidad: %d pasajeros\nNúmero de pisos: %d\nTipo de viajes: %s",
                cantidadPasajeros, numeroPisos, tipoViaje
        );
    }

}
