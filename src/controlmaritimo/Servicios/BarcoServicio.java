/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Servicios;

import controlmaritimo.Modelos.Barco;
import controlmaritimo.Repositorio.BarcoRepositorio;
import java.util.List;
/**
 *
 * @author DELL
 */

/**
 * Servicio para gestionar barcos.
 */
public class BarcoServicio {
    private final BarcoRepositorio barcoRepositorio;
    
    public BarcoServicio(BarcoRepositorio barcoRepositorio) {
        this.barcoRepositorio = barcoRepositorio;
    }
    
    public boolean guardar(Barco barco) {
        return barcoRepositorio.guardar(barco);
    }
    
    public Barco buscarPorMatricula(String matricula) {
        return barcoRepositorio.buscarPorMatricula(matricula);
    }
    
    public List<Barco> obtenerTodos() {
        return barcoRepositorio.obtenerTodos();
    }
    
    public boolean actualizar(int indice, Barco barco) {
        return barcoRepositorio.actualizar(indice, barco);
    }
    
    public boolean eliminar(int indice) {
        return barcoRepositorio.eliminar(indice);
    }
    
    public boolean existeBarco(String matricula) {
        return buscarPorMatricula(matricula) != null;
    }
}
