/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.Repositorio;

import controlmaritimo.Modelos.Puerto;
import java.util.List;
/**
 *
 * @author DELL
 */


/**
 * Interfaz para el repositorio de puertos.
 */
public interface PuertoRepositorio {
    boolean guardar(Puerto puerto);
    Puerto buscarPorCodigo(String codigo);
    List<Puerto> obtenerTodos();
    boolean actualizar(int indice, Puerto puerto);
    boolean eliminar(int indice);
}