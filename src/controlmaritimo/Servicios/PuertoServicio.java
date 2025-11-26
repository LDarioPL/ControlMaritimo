/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Servicios;

import controlmaritimo.Modelos.Puerto;
import controlmaritimo.Repositorio.PuertoRepositorio;
import java.util.List;
/**
 *
 * @author DELL
 */

/**
 * Servicio para gestionar puertos.
 */
public class PuertoServicio {
    private final PuertoRepositorio puertoRepositorio;
    
    public PuertoServicio(PuertoRepositorio puertoRepositorio) {
        this.puertoRepositorio = puertoRepositorio;
    }
    
    public boolean guardar(Puerto puerto) {
        return puertoRepositorio.guardar(puerto);
    }
    
    public Puerto buscarPorCodigo(String codigo) {
        return puertoRepositorio.buscarPorCodigo(codigo);
    }
    
    public List<Puerto> obtenerTodos() {
        return puertoRepositorio.obtenerTodos();
    }
    
    public boolean actualizar(int indice, Puerto puerto) {
        return puertoRepositorio.actualizar(indice, puerto);
    }
    
    public boolean eliminar(int indice) {
        return puertoRepositorio.eliminar(indice);
    }
    
    public boolean existePuerto(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }
}

