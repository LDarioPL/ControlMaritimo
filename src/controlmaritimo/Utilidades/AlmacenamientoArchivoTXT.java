/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.Utilidades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */

/**
 * Clase utilitaria para gestionar archivos de texto.
 * Adaptado del sistema de control vehicular.
 */
public class AlmacenamientoArchivoTXT {
    
    private static String getRutaBase() {
        String rutabase = System.getProperty("user.dir");
        rutabase = rutabase.replaceAll("\\\\", "/");
        return rutabase;
    }
    
    public static void inicializarRutas() {
        String rutabase = getRutaBase();
        String rutacompleta = String.format("%s/datos/", rutabase);
        File directorio = new File(rutacompleta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }
    
    public static List<String> leerDatos(String nombreArchivo) {
        inicializarRutas();
        List<String> list = new ArrayList<>();
        String rutabase = getRutaBase();
        String rutaarchivo = String.format("%s/datos/%s", rutabase, nombreArchivo);
        try (FileReader fr = new FileReader(rutaarchivo);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException ex) {
            // Archivo no existe o error al leer
        }
        return list;
    }
    
    public static boolean guardarRegistro(String nombreArchivo, String registro) {
        inicializarRutas();
        String rutabase = getRutaBase();
        String rutaarchivo = String.format("%s/datos/%s", rutabase, nombreArchivo);
        try (FileWriter fw = new FileWriter(rutaarchivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.append(registro);
            pw.append("\n");
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public static boolean actualizarRegistro(String nombreArchivo, int numerolineaactualizar, String registroactualizar) {
        inicializarRutas();
        String rutabase = getRutaBase();
        String rutaarchivo = String.format("%s/datos/%s", rutabase, nombreArchivo);
        try {
            List<String> list = new ArrayList<>();
            try (FileReader fr = new FileReader(rutaarchivo);
                 BufferedReader br = new BufferedReader(fr)) {
                int nlinea = 1;
                String line;
                while ((line = br.readLine()) != null) {
                    if (nlinea != numerolineaactualizar) {
                        list.add(line);
                    } else {
                        list.add(registroactualizar);
                    }
                    nlinea++;
                }
            }
            borrarArchivo(nombreArchivo);
            try (FileWriter fw = new FileWriter(rutaarchivo, true);
                 PrintWriter pw = new PrintWriter(fw)) {
                for (String registro : list) {
                    pw.append(registro);
                    pw.append("\n");
                }
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public static boolean borrarArchivo(String nombreArchivo) {
        inicializarRutas();
        String rutabase = getRutaBase();
        String rutaarchivo = String.format("%s/datos/%s", rutabase, nombreArchivo);
        File f = new File(rutaarchivo);
        if (f.exists()) {
            f.delete();
        }
        File f2 = new File(rutaarchivo);
        return !f2.exists();
    }
    
    public static boolean borrarRegistro(String nombreArchivo, int numerolineaeliminar) {
        inicializarRutas();
        String rutabase = getRutaBase();
        String rutaarchivo = String.format("%s/datos/%s", rutabase, nombreArchivo);
        try {
            List<String> list = new ArrayList<>();
            try (FileReader fr = new FileReader(rutaarchivo);
                 BufferedReader br = new BufferedReader(fr)) {
                int nlinea = 1;
                String line;
                while ((line = br.readLine()) != null) {
                    if (nlinea != numerolineaeliminar) {
                        list.add(line);
                    }
                    nlinea++;
                }
            }
            borrarArchivo(nombreArchivo);
            try (FileWriter fw = new FileWriter(rutaarchivo, true);
                 PrintWriter pw = new PrintWriter(fw)) {
                for (String registro : list) {
                    pw.append(registro);
                    pw.append("\n");
                }
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}