/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.Repositorio;

import controlmaritimo.Modelos.ZonaPesca;
import java.util.List;

/**
 *
 * @author DELL
 */


/**
 * Interfaz para el repositorio de zonas de pesca.
 */
public interface ZonaPescaRepositorio {
    boolean guardar(ZonaPesca zona);
    ZonaPesca buscarPorClave(String clave);
    List<ZonaPesca> obtenerTodos();
    boolean actualizar(int indice, ZonaPesca zona);
    boolean eliminar(int indice);
}