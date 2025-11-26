/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Repositorio;

import controlmaritimo.Modelos.Puerto;
import controlmaritimo.Utilidades.AlmacenamientoArchivoTXT;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author DELL
 */

/**
 * Implementación del repositorio de puertos usando archivos TXT.
 */
public class PuertoRepositorioTXT implements PuertoRepositorio {
    private static final String NOMBRE_ARCHIVO = "Puertos.txt";
    
    @Override
    public boolean guardar(Puerto puerto) {
        return AlmacenamientoArchivoTXT.guardarRegistro(NOMBRE_ARCHIVO, puerto.toCSV());
    }
    
    @Override
    public Puerto buscarPorCodigo(String codigo) {
        List<String> datos = AlmacenamientoArchivoTXT.leerDatos(NOMBRE_ARCHIVO);
        if (datos != null && !datos.isEmpty()) {
            for (String registro : datos) {
                Puerto puerto = Puerto.fromString(registro);
                if (puerto != null && puerto.getCodigo().equalsIgnoreCase(codigo)) {
                    return puerto;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Puerto> obtenerTodos() {
        List<String> datos = AlmacenamientoArchivoTXT.leerDatos(NOMBRE_ARCHIVO);
        return datos.stream()
                .map(Puerto::fromString)
                .filter(p -> p != null)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean actualizar(int indice, Puerto puerto) {
        return AlmacenamientoArchivoTXT.actualizarRegistro(NOMBRE_ARCHIVO, indice, puerto.toCSV());
    }
    
    @Override
    public boolean eliminar(int indice) {
        return AlmacenamientoArchivoTXT.borrarRegistro(NOMBRE_ARCHIVO, indice);
    }
}
