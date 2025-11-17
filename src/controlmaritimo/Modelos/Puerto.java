/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Modelos;

/**
 *
 * @author DELL
 */

import java.util.ArrayList;
import java.util.List;

public class Puerto {
    private String codigo;
    private String nombre;
    private int capacidadBarcos;
    private String ciudad;
    private String pais;
    private List<Atraque> atraques;
    
    public Puerto(String codigo, String nombre, int capacidadBarcos,
                 String ciudad, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidadBarcos = capacidadBarcos;
        this.ciudad = ciudad;
        this.pais = pais;
        this.atraques = new ArrayList<>();
    }
    
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public int getCapacidadBarcos() { return capacidadBarcos; }
    public void setCapacidadBarcos(int capacidadBarcos) { 
        this.capacidadBarcos = capacidadBarcos; 
    }
    
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    
    public void agregarAtraque(Atraque atraque) {
        this.atraques.add(atraque);
    }
    
    public List<Atraque> obtenerAtraques() {
        return new ArrayList<>(atraques);
    }
    
    public String obtenerInfo() {
        return String.format(
            "Código: %s\nNombre: %s\nUbicación: %s, %s\n" +
            "Capacidad: %d barcos\nAtraques registrados: %d",
            codigo, nombre, ciudad, pais, capacidadBarcos, atraques.size()
        );
    }
    
    public String toCSV() {
        return String.format("%s,%s,%d,%s,%s",
            codigo, nombre, capacidadBarcos, ciudad, pais);
    }
    
    @Override
    public String toString() {
        return String.format("Puerto %s (%s)", nombre, codigo);
    }
}