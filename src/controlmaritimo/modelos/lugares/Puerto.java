/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.modelos.lugares;

import controlmaritimo.modelos.documentos.DocumentoAtraque;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase declara los atributos propios del puerto maritimo, conteniendo
 * atributos como: código del puerto, nombre, capacidad de barcos, ciudad y su
 * país.
 *
 * @version 1.5
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class Puerto {

    // Atributos propios del puerto maritimo
    private String codigo;
    private String nombre;
    private int capacidadBarcos;
    private String ciudad;
    private String pais;
    private List<DocumentoAtraque> atraques;

    // Constructor sobrecargado
    public Puerto(String codigo, String nombre, int capacidadBarcos,
            String ciudad, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidadBarcos = capacidadBarcos;
        this.ciudad = ciudad;
        this.pais = pais;
        this.atraques = new ArrayList<>();
    }

    // Metodos setters y getters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadBarcos() {
        return capacidadBarcos;
    }

    public void setCapacidadBarcos(int capacidadBarcos) {
        this.capacidadBarcos = capacidadBarcos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void agregarAtraque(DocumentoAtraque atraque) {
        this.atraques.add(atraque);
    }

    public List<DocumentoAtraque> obtenerAtraques() {
        return new ArrayList<>(atraques);
    }
    
    /**
     * Método que retorna un mensaje con la información del puerto maritimo.
     * 
     * @return String con la información del puerto.
     */
    public String obtenerInfo() {
        return String.format(
                "Código: %s\nNombre: %s\nUbicación: %s, %s\n"
                + "Capacidad: %d barcos\nAtraques registrados: %d",
                codigo, nombre, ciudad, pais, capacidadBarcos, atraques.size()
        );
    }

}
