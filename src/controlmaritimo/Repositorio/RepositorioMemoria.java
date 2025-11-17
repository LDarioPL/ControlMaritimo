/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Repositorio;

/**
 *
 * @author DELL
 */

import java.util.*;

/**
 * Implementación en memoria del repositorio usando HashMap.
 */
public class RepositorioMemoria<T, K> implements IRepositorio<T, K> {
    private Map<K, T> datos;
    
    public RepositorioMemoria() {
        this.datos = new HashMap<>();
    }
    
    @Override
    public boolean guardar(T entidad) {
        // Este método requiere la clave, se debe sobrecargar en subclases
        return false;
    }
    
    public boolean guardar(K clave, T entidad) {
        if (datos.containsKey(clave)) {
            return false;
        }
        datos.put(clave, entidad);
        return true;
    }
    
    @Override
    public T buscarPorClave(K clave) {
        return datos.get(clave);
    }
    
    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(datos.values());
    }
    
    @Override
    public boolean actualizar(K clave, T entidad) {
        if (!datos.containsKey(clave)) {
            return false;
        }
        datos.put(clave, entidad);
        return true;
    }
    
    @Override
    public boolean eliminar(K clave) {
        return datos.remove(clave) != null;
    }
    
    @Override
    public boolean existe(K clave) {
        return datos.containsKey(clave);
    }
    
    @Override
    public int contar() {
        return datos.size();
    }
    
    @Override
    public void limpiar() {
        datos.clear();
    }
    
    protected Map<K, T> getDatos() {
        return datos;
    }
}

