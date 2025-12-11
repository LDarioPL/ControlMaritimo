/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.logica;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * Esta clase es el corazón de todo, se encarga de ejecutar el programa para que
 * el usuario pueda usarlo.
 *
 * @version 1.1, 5/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class Ejecutor {

    /**
     * Este método crea un objeto de tipo Orquestador y llama al único método
     * disponible de esa clase el cual es ejecutar(), por otro lado tambíen se
     * encarga de permitir que el programa acepte caracteres especiales como
     * acentos o el signo de interrogación.
     *
     * @param args
     */
    public static void main(String args[]) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        new Orquestador().ejecutar();
    }

}
