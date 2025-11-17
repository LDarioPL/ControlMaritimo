/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Modelos;

/**
 *
 * @author DELL
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base abstracta para todos los tipos de barcos.
 */
public abstract class Barco {
    protected String matricula;
    protected String bandera;
    protected String nombre;
    protected double pesoToneladas;
    protected LocalDate fechaBotadura;
    protected List<Atraque> atraques;
    
    public Barco(String matricula, String bandera, String nombre, 
                 double pesoToneladas, LocalDate fechaBotadura) {
        this.matricula = matricula;
        this.bandera = bandera;
        this.nombre = nombre;
        this.pesoToneladas = pesoToneladas;
        this.fechaBotadura = fechaBotadura;
        this.atraques = new ArrayList<>();
    }
    
    // Getters y Setters
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    public String getBandera() { return bandera; }
    public void setBandera(String bandera) { this.bandera = bandera; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getPesoToneladas() { return pesoToneladas; }
    public void setPesoToneladas(double pesoToneladas) { this.pesoToneladas = pesoToneladas; }
    
    public LocalDate getFechaBotadura() { return fechaBotadura; }
    public void setFechaBotadura(LocalDate fechaBotadura) { this.fechaBotadura = fechaBotadura; }
    
    public void agregarAtraque(Atraque atraque) {
        this.atraques.add(atraque);
    }
    
    public List<Atraque> obtenerAtraques() {
        return new ArrayList<>(atraques);
    }
    
    public abstract String getTipoBarco();
    
    public String obtenerInfo() {
        return String.format(
            "Matrícula: %s\nNombre: %s\nBandera: %s\n" +
            "Peso: %.2f toneladas\nFecha de botadura: %s\n" +
            "Atraques registrados: %d\nTipo: %s",
            matricula, nombre, bandera, pesoToneladas, 
            fechaBotadura, atraques.size(), getTipoBarco()
        );
    }
    
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%.2f,%s",
            getTipoBarco(), matricula, bandera, nombre, pesoToneladas, fechaBotadura);
    }
    
    @Override
    public String toString() {
        return String.format("Barco %s (%s)", nombre, matricula);
    }
}
