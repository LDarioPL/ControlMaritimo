/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.documentos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.abstracciones.interfaces.IOperacionesDocumentos;
import controlmaritimo.modelos.barcos.BarcoPesquero;
import controlmaritimo.modelos.documentos.ConocimientoEmbarque;
import controlmaritimo.modelos.lugares.ZonaPesca;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase implementa la interfaz IOperacionesDocumentos y se en encarga de
 * sobreescribir los metodos de creación, edición, consulta y eliminación de un
 * documento de Conocimiento de Embarque.
 *
 * @version 1.8 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class OperacionesConocimientoEmbarque implements IOperacionesDocumentos {

    private List<ConocimientoEmbarque> listaDocumentos;
    private List<Barco> listaBarcos;     // Necesario para validar que el barco existe
    private List<ZonaPesca> listaZonas;  // Necesario para validar que la zona existe
    private Scanner leer;

    //Formato para las fechas
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public OperacionesConocimientoEmbarque(List<ConocimientoEmbarque> listaDocumentos,
            List<Barco> listaBarcos,
            List<ZonaPesca> listaZonas) {
        this.listaDocumentos = listaDocumentos;
        this.listaBarcos = listaBarcos;
        this.listaZonas = listaZonas;
        this.leer = new Scanner(System.in);
    }

    /**
     * Sobreescritura del método crearDocumento() para adaptarlo a un
     * conocimiento de embarque.
     */
    @Override
    public void crearDocumento() {
        System.out.println("\n--- NUEVO CONOCIMIENTO DE EMBARQUE ---");

        // 1. Validar Barco
        System.out.print("Ingrese la Matrícula del Barco Pesquero: ");
        String matricula = leer.nextLine();
        Barco barco = buscarBarco(matricula);

        if (barco == null) {
            System.out.println("Error: Barco no encontrado.");
            return;
        }

        // Validamos que sea específicamente un Barco Pesquero
        if (!(barco instanceof BarcoPesquero)) {
            System.out.println("Error: Este documento es exclusivo para Barcos Pesqueros.");
            System.out.println("El barco ingresado es de tipo: " + barco.getTipoBarco());
            return;
        }

        // 2. Validar Zona
        System.out.print("Ingrese la Clave de la Zona de Pesca: ");
        String claveZona = leer.nextLine();
        ZonaPesca zona = buscarZona(claveZona);

        if (zona == null) {
            System.out.println("Error: Zona de pesca no encontrada.");
            return;
        }

        // 3. Crear Documento (Se registra con la fecha/hora actual del sistema)
        LocalDateTime ahora = LocalDateTime.now();

        // Hacemos el cast seguro porque ya validamos con instanceof
        ConocimientoEmbarque nuevoDoc = new ConocimientoEmbarque((BarcoPesquero) barco, zona, ahora);

        listaDocumentos.add(nuevoDoc);
        System.out.println("Documento creado exitosamente.");
        System.out.println("Fecha de entrada registrada: " + ahora.format(formatter));
    }

    /**
     * Sobreescritura del método editarDocumento() para adaptarlo a un
     * conocimiento de embarque.
     */
    @Override
    public void editarDocumento() {
        // En el contexto de un documento legal, "editar" suele ser "Registrar Salida"
        System.out.println("\n--- REGISTRAR SALIDA (CERRAR DOCUMENTO) ---");
        System.out.print("Ingrese la Matrícula del Barco para buscar su documento activo: ");
        String matricula = leer.nextLine();

        // Buscamos un documento que tenga este barco y NO tenga fecha de salida
        ConocimientoEmbarque docEncontrado = null;
        for (ConocimientoEmbarque doc : listaDocumentos) {
            if (doc.getBarco().getMatricula().equalsIgnoreCase(matricula) && doc.getFechaHoraSalida() == null) {
                docEncontrado = doc;
                break;
            }
        }

        if (docEncontrado != null) {
            System.out.println("Documento encontrado en Zona: " + docEncontrado.getZona().getClaveGeolocalizacion());
            System.out.println("Fecha Entrada: " + docEncontrado.getFechaHoraEntrada().format(formatter));

            System.out.print("¿Desea registrar la salida con la fecha/hora actual? (s/n): ");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                docEncontrado.registrarSalida(LocalDateTime.now());
                System.out.println("Salida registrada. Documento cerrado.");
            }
        } else {
            System.out.println("No se encontró un documento activo (sin salida) para ese barco.");
        }
    }

    /**
     * Sobreescritura del método eliminarDocumento() para adaptarlo a un
     * conocimiento de embarque.
     */
    @Override
    public void eliminarDocumento() {
        System.out.println("\n--- ELIMINAR DOCUMENTO ---");
        // Para simplificar, eliminamos por matrícula de barco asociada
        System.out.print("Ingrese la Matrícula del Barco del documento a borrar: ");
        String matricula = leer.nextLine();

        boolean eliminado = listaDocumentos.removeIf(doc -> doc.getBarco().getMatricula().equalsIgnoreCase(matricula));

        if (eliminado) {
            System.out.println("Documento(s) asociado(s) al barco eliminados.");
        } else {
            System.out.println("️No se encontraron documentos para ese barco.");
        }
    }

    /**
     * Sobreescritura del método consultarDocumento() para adaptarlo a un
     * conocimiento de embarque.
     */
    @Override
    public void consultarDocumento() {
        System.out.println("\n--- CONSULTA DE CONOCIMIENTOS DE EMBARQUE ---");
        if (listaDocumentos.isEmpty()) {
            System.out.println("No hay documentos registrados.");
            return;
        }

        System.out.println("Listado de documentos:");
        for (int i = 0; i < listaDocumentos.size(); i++) {
            ConocimientoEmbarque doc = listaDocumentos.get(i);
            System.out.println("#" + (i + 1) + " ----------------------------------");
            System.out.println(doc.obtenerInfo());
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Método auxiliar para consultar un documento, busca un barco según una
     * matricula ingresada.
     *
     * @param matricula
     * @return
     */
    private Barco buscarBarco(String matricula) {
        for (Barco b : listaBarcos) {
            if (b.getMatricula().equalsIgnoreCase(matricula)) {
                return b;
            }
        }
        return null;
    }
    
    /**
     * Método auxiliar para consultar un documento, busca una zona de pesca
     * según una clave ingresada.
     * 
     * @param clave
     * @return 
     */
    private ZonaPesca buscarZona(String clave) {
        for (ZonaPesca z : listaZonas) {
            if (z.getClaveGeolocalizacion().equalsIgnoreCase(clave)) {
                return z;
            }
        }
        return null;
    }

}
