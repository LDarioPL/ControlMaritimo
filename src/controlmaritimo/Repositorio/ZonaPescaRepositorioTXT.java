/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Repositorio;

import controlmaritimo.Modelos.ZonaPesca;
import controlmaritimo.Utilidades.AlmacenamientoArchivoTXT;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author DELL
 */


/**
 * Implementación del repositorio de zonas de pesca usando archivos TXT.
 */
public class ZonaPescaRepositorioTXT implements ZonaPescaRepositorio {
    private static final String NOMBRE_ARCHIVO = "ZonasPesca.txt";
    
    @Override
    public boolean guardar(ZonaPesca zona) {
        return AlmacenamientoArchivoTXT.guardarRegistro(NOMBRE_ARCHIVO, zona.toCSV());
    }
    
    @Override
    public ZonaPesca buscarPorClave(String clave) {
        List<String> datos = AlmacenamientoArchivoTXT.leerDatos(NOMBRE_ARCHIVO);
        if (datos != null && !datos.isEmpty()) {
            for (String registro : datos) {
                ZonaPesca zona = ZonaPesca.fromString(registro);
                if (zona != null && zona.getClaveGeolocalizacion().equalsIgnoreCase(clave)) {
                    return zona;
                }
            }
        }
        return null;
    }
    
    @Override
    public List<ZonaPesca> obtenerTodos() {
        List<String> datos = AlmacenamientoArchivoTXT.leerDatos(NOMBRE_ARCHIVO);
        return datos.stream()
                .map(ZonaPesca::fromString)
                .filter(z -> z != null)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean actualizar(int indice, ZonaPesca zona) {
        return AlmacenamientoArchivoTXT.actualizarRegistro(NOMBRE_ARCHIVO, indice, zona.toCSV());
    }
    
    @Override
    public boolean eliminar(int indice) {
        return AlmacenamientoArchivoTXT.borrarRegistro(NOMBRE_ARCHIVO, indice);
    }
}
