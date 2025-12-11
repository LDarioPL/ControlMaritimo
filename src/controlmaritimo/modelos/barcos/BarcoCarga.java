/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import java.time.LocalDate;

/**
 * Esta clase hereda de la clase Barco y declara los atributos únicos y
 * pertenecientes a un barco de carga.
 *
 * @version 1.7 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class BarcoCarga extends Barco {

    // Atributos propios de un barco de carga
    private int limiteContenedores;
    private int cantidadContenedores;
    
    // Constructor sobrecargado
    public BarcoCarga(String matricula, String bandera, String nombre,
            double pesoToneladas, LocalDate fechaBotadura,
            int limiteContenedores, int cantidadContenedores) {

        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
        this.cantidadContenedores = cantidadContenedores;
        this.limiteContenedores = limiteContenedores;
    }

    // Metodos getters y setters
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

    /**
     * Método que retorna el tipo de barco.
     *
     * @return String refiriendose al tipo de barco.
     */
    @Override
    public String getTipoBarco() {
        return "Barco de carga";
    }

    /**
     * Método que retorna la información (atributos) propia de un barco de
     * carga.
     *
     * @return String que contiene la información de este tipo de barco.
     */
    @Override
    public String obtenerInformacionBarco() {
        return super.obtenerInformacionBarco() + String.format(
                "\nMaxima capacidad: %d contenedores \nCantidad actual: %d contenedores",
                limiteContenedores,
                cantidadContenedores);
    }

}
