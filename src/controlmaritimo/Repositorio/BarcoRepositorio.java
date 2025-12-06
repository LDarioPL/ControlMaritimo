/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Repositorio;

import controlmaritimo.abstracciones.Barco;
import java.util.List;
/**
 *
 * @author DELL
 */

/**
 * Interfaz para el repositorio de barcos.
 */
public interface BarcoRepositorio {
    boolean guardar(Barco barco);
    Barco buscarPorMatricula(String matricula);
    List<Barco> obtenerTodos();
    boolean actualizar(int indice, Barco barco);
    boolean eliminar(int indice);
}