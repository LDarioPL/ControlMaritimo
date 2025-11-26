/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Servicios;

import controlmaritimo.Modelos.ZonaPesca;
import controlmaritimo.Repositorio.ZonaPescaRepositorio;
import java.util.List;

/**
 *
 * @author DELL
 */


/**
 * Servicio para gestionar zonas de pesca.
 */
public class ZonaPescaServicio {
    private final ZonaPescaRepositorio zonaPescaRepositorio;
    
    public ZonaPescaServicio(ZonaPescaRepositorio zonaPescaRepositorio) {
        this.zonaPescaRepositorio = zonaPescaRepositorio;
    }
    
    public boolean guardar(ZonaPesca zona) {
        return zonaPescaRepositorio.guardar(zona);
    }
    
    public ZonaPesca buscarPorClave(String clave) {
        return zonaPescaRepositorio.buscarPorClave(clave);
    }
    
    public List<ZonaPesca> obtenerTodos() {
        return zonaPescaRepositorio.obtenerTodos();
    }
    
    public boolean actualizar(int indice, ZonaPesca zona) {
        return zonaPescaRepositorio.actualizar(indice, zona);
    }
    
    public boolean eliminar(int indice) {
        return zonaPescaRepositorio.eliminar(indice);
    }
    
    public boolean existeZonaPesca(String clave) {
        return buscarPorClave(clave) != null;
    }
}
