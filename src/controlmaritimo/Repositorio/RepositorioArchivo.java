/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Repositorio;

/**
 *
 * @author DELL
 */

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;

/**
 * Implementación del repositorio con persistencia en archivos de texto (CSV).
 */
public class RepositorioArchivo<T, K> implements IRepositorio<T, K> {
    private String rutaArchivo;
    private Function<T, K> extractorClaveFunction;
    private Function<String, T> deserializador;
    private Function<T, String> serializador;
    private Map<K, T> cache;
    
    public RepositorioArchivo(String rutaArchivo,
                             Function<T, K> extractorClave,
                             Function<String, T> deserializador,
                             Function<T, String> serializador) {
        this.rutaArchivo = rutaArchivo;
        this.extractorClaveFunction = extractorClave;
        this.deserializador = deserializador;
        this.serializador = serializador;
        this.cache = new HashMap<>();
        cargarDesdeArchivo();
    }
    
    private void cargarDesdeArchivo() {
        try {
            Path path = Paths.get(rutaArchivo);
            if (Files.exists(path)) {
                List<String> lineas = Files.readAllLines(path);
                for (String linea : lineas) {
                    if (!linea.trim().isEmpty()) {
                        T entidad = deserializador.apply(linea);
                        if (entidad != null) {
                            K clave = extractorClaveFunction.apply(entidad);
                            cache.put(clave, entidad);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar desde archivo: " + e.getMessage());
        }
    }
    
    private void guardarEnArchivo() {
        try {
            Path path = Paths.get(rutaArchivo);
            Files.createDirectories(path.getParent());
            
            List<String> lineas = new ArrayList<>();
            for (T entidad : cache.values()) {
                lineas.add(serializador.apply(entidad));
            }
            
            Files.write(path, lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
    
    @Override
    public boolean guardar(T entidad) {
        K clave = extractorClaveFunction.apply(entidad);
        if (cache.containsKey(clave)) {
            return false;
        }
        cache.put(clave, entidad);
        guardarEnArchivo();
        return true;
    }
    
    @Override
    public T buscarPorClave(K clave) {
        return cache.get(clave);
    }
    
    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(cache.values());
    }
    
    @Override
    public boolean actualizar(K clave, T entidad) {
        if (!cache.containsKey(clave)) {
            return false;
        }
        cache.put(clave, entidad);
        guardarEnArchivo();
        return true;
    }
    
    @Override
    public boolean eliminar(K clave) {
        if (cache.remove(clave) != null) {
            guardarEnArchivo();
            return true;
        }
        return false;
    }
    
    @Override
    public boolean existe(K clave) {
        return cache.containsKey(clave);
    }
    
    @Override
    public int contar() {
        return cache.size();
    }
    
    @Override
    public void limpiar() {
        cache.clear();
        guardarEnArchivo();
    }
}
