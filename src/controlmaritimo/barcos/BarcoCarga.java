/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.barcos;

import controlmaritimo.abstracciones.Barco;
import java.time.LocalDate;

/**
 *
 * @author yazid
 */
public class BarcoCarga extends Barco {
    
    private int limiteContenedores;
    private int cantidadContenedores;

    public BarcoCarga(String matricula, String bandera, String nombre,
                      double pesoToneladas, LocalDate fechaBotadura,
                      int limiteContenedores, int cantidadContenedores) {

        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.cantidadContenedores = cantidadContenedores;
        this.limiteContenedores = limiteContenedores;
    }

    public int getLimiteContenedores() {
        return limiteContenedores;
    }

    public void setLimiteContenedores(int limiteContenedores) {
        this.limiteContenedores = limiteContenedores;
    }

    public int getCantidadContenedores() {
        return cantidadContenedores;
    }

    public void setCantidadContenedores(int cantidadContenedores) {
        this.cantidadContenedores = cantidadContenedores;
    }

    @Override
    public String getTipoBarco() {
        return "CARGA";
    }

    @Override
    public String obtenerInformacionBarco() {
        return super.obtenerInformacionBarco() + String.format(
                "\nCapacidad: %d contenedores",
                cantidadContenedores
        );
    }
}
