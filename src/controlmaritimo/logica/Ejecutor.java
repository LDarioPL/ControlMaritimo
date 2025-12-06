/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.logica;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author yazid
 */
public class Ejecutor {
    
    public static void main(String args[]) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        new Orquestador().ejecutar();
        
    }
    
}
