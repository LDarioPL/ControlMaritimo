/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Esta clase se encarga de contener
 * el método que sirve para validar
 * que cualquier fecha ingresada
 * este en el formato correcot (dd/MM/yyyy).
 * 
 * @version 1.0 06/12/2025
 *
 * @author yazid
 */
public class ValidarFecha {
    
    public static LocalDate validarFecha(String fechaString) {
        // Definición del formato
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        try {
            if (fechaString != null && !fechaString.isBlank()) {
                // Parseamos directamente a LocalDate
                return LocalDate.parse(fechaString, formato);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Usa dd/MM/yyyy");
        }
        return null; // Retorna null si falla
    }
    
}
