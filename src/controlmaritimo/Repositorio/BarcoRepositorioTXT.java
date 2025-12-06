/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Repositorio;

import controlmaritimo.abstracciones.Barco;
import controlmaritimo.Utilidades.AlmacenamientoArchivoTXT;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author DELL
 */

/**
 * Implementación del repositorio de barcos usando archivos TXT.
 */
public class BarcoRepositorioTXT implements BarcoRepositorio {
    private static final String NOMBRE_ARCHIVO = "Barcos.txt";
    
    @Override
    public boolean guardar(Barco barco) {
        return AlmacenamientoArchivoTXT.guardarRegistro(NOMBRE_ARCHIVO, barco.toCSV());
    }
    
    @Override
    public Barco buscarPorMatricula(String matricula) {
        List<String> datos = AlmacenamientoArchivoTXT.leerDatos(NOMBRE_ARCHIVO);
        if (datos != null && !datos.isEmpty()) {
            for (String registro : datos) {
                Barco barco = Barco.fromString(registro);
                if (barco != null && barco.getMatricula().equalsIgnoreCase(matricula)) {
                    return barco;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Barco> obtenerTodos() {
        List<String> datos = AlmacenamientoArchivoTXT.leerDatos(NOMBRE_ARCHIVO);
        return datos.stream()
                .map(Barco::fromString)
                .filter(b -> b != null)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean actualizar(int indice, Barco barco) {
        return AlmacenamientoArchivoTXT.actualizarRegistro(NOMBRE_ARCHIVO, indice, barco.toCSV());
    }
    
    @Override
    public boolean eliminar(int indice) {
        return AlmacenamientoArchivoTXT.borrarRegistro(NOMBRE_ARCHIVO, indice);
    }
}