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
 * @version 1.0, 5/12/2025
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 *
 */
public class Ejecutor {

    public static void main(String args[]) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        new Orquestador().ejecutar();
    }

}
