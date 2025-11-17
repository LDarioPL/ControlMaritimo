/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.Repositorio;

/**
 *
 * @author DELL
 */

import java.util.List;

/**
 * Interfaz genérica para el patrón Repository.
 * @param <T> Tipo de entidad
 * @param <K> Tipo de clave primaria
 */
public interface IRepositorio<T, K> {
    boolean guardar(T entidad);
    T buscarPorClave(K clave);
    List<T> listarTodos();
    boolean actualizar(K clave, T entidad);
    boolean eliminar(K clave);
    boolean existe(K clave);
    int contar();
    void limpiar();
}
