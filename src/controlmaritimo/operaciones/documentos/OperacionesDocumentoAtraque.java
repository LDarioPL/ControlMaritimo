/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.documentos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.abstracciones.interfaces.IOperacionesDocumentos;
import controlmaritimo.modelos.documentos.DocumentoAtraque;
import controlmaritimo.modelos.lugares.Puerto;
import controlmaritimo.utilidades.ValidarFecha;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que implementa las operaciones CRUD para la administración de
 * documentos de atraque (transacciones de barcos en puertos), implementa la
 * interfaz IOperacionesDocumentos.
 * 
 * @version 1.5 09/12/2025
 * 
 * @author Emilio Álvarez Villalobos
 * @author Nicolás Yazid Cruz Hernández
 * @author Luis Darío Padilla Lopez
 * @author Isaac Ádriano Vazquez Torres
 */
public class OperacionesDocumentoAtraque implements IOperacionesDocumentos {

    private List<DocumentoAtraque> listaAtraques;
    private List<Barco> listaBarcos;
    private List<Puerto> listaPuertos;
    private Scanner leer;

    /**
     * Constructor que inicializa las listas necesarias
     *
     * @param atraques Lista de documentos de atraque
     * @param barcos Lista de barcos disponibles
     * @param puertos Lista de puertos disponibles
     */
    public OperacionesDocumentoAtraque(List<DocumentoAtraque> atraques,
            List<Barco> barcos,
            List<Puerto> puertos) {
        this.listaAtraques = atraques;
        this.listaBarcos = barcos;
        this.listaPuertos = puertos;
        this.leer = new Scanner(System.in);
    }

    /**
     * Sobreescritura del método crearDocumento() para adaptarlo a un documento
     * de atraque.
     */
    @Override
    public void crearDocumento() {
        System.out.println("\n--- REGISTRO DE DOCUMENTO DE ATRAQUE ---");

        // Verificar que existan barcos y puertos registrados
        if (listaBarcos.isEmpty()) {
            System.out.println("Error: No hay barcos registrados en el sistema.");
            System.out.println("Primero debes registrar al menos un barco.");
            return;
        }

        if (listaPuertos.isEmpty()) {
            System.out.println("Error: No hay puertos registrados en el sistema.");
            System.out.println("Primero debes registrar al menos un puerto.");
            return;
        }

        // Seleccionar barco
        System.out.println("\n=== BARCOS DISPONIBLES ===");
        for (int i = 0; i < listaBarcos.size(); i++) {
            Barco b = listaBarcos.get(i);
            System.out.printf("%d. %s - Matrícula: %s\n",
                    (i + 1), b.getNombre(), b.getMatricula());
        }

        System.out.print("\nSelecciona el número del barco: ");
        int indiceBarco = -1;
        try {
            indiceBarco = Integer.parseInt(leer.nextLine()) - 1;
            if (indiceBarco < 0 || indiceBarco >= listaBarcos.size()) {
                System.out.println("Error: Número de barco inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        Barco barcoSeleccionado = listaBarcos.get(indiceBarco);

        // Seleccionar puerto
        System.out.println("\n=== PUERTOS DISPONIBLES ===");
        for (int i = 0; i < listaPuertos.size(); i++) {
            Puerto p = listaPuertos.get(i);
            System.out.printf("%d. %s - Código: %s (%s, %s)\n",
                    (i + 1), p.getNombre(), p.getCodigo(),
                    p.getCiudad(), p.getPais());
        }

        System.out.print("\nSelecciona el número del puerto: ");
        int indicePuerto = -1;
        try {
            indicePuerto = Integer.parseInt(leer.nextLine()) - 1;
            if (indicePuerto < 0 || indicePuerto >= listaPuertos.size()) {
                System.out.println("Error: Número de puerto inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        Puerto puertoSeleccionado = listaPuertos.get(indicePuerto);

        // Ingresar fecha de atraque
        LocalDate fechaAtraque = null;
        while (fechaAtraque == null) {
            System.out.print("\nFecha de atraque (dd/MM/yyyy): ");
            fechaAtraque = ValidarFecha.validarFecha(leer.nextLine());
        }

        // Crear el documento de atraque
        DocumentoAtraque nuevoAtraque = new DocumentoAtraque(
                barcoSeleccionado,
                puertoSeleccionado,
                fechaAtraque
        );

        // Agregar el atraque a la lista y al puerto
        this.listaAtraques.add(nuevoAtraque);
        puertoSeleccionado.agregarAtraque(nuevoAtraque);

        System.out.println("\n✓ Documento de atraque registrado exitosamente.");
        System.out.println("\n--- RESUMEN DEL ATRAQUE ---");
        System.out.println(nuevoAtraque.obtenerInfo());
    }

    /**
     * Sobreescritura del método editarDocumento() para adaptarlo a un documento
     * de atraque.
     */
    @Override
    public void editarDocumento() {
        System.out.println("\n--- EDITAR DOCUMENTO DE ATRAQUE ---");

        if (listaAtraques.isEmpty()) {
            System.out.println("No hay documentos de atraque registrados.");
            return;
        }

        // Listar todos los atraques
        System.out.println("\n=== ATRAQUES REGISTRADOS ===");
        for (int i = 0; i < listaAtraques.size(); i++) {
            DocumentoAtraque atraque = listaAtraques.get(i);
            System.out.printf("%d. %s en %s - Fecha: %s\n",
                    (i + 1),
                    atraque.getBarco().getNombre(),
                    atraque.getPuerto().getNombre(),
                    atraque.getFechaAtraque());
        }

        System.out.print("\nSelecciona el número del atraque a editar: ");
        int indice = -1;
        try {
            indice = Integer.parseInt(leer.nextLine()) - 1;
            if (indice < 0 || indice >= listaAtraques.size()) {
                System.out.println("Error: Número de atraque inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        DocumentoAtraque atraqueEditar = listaAtraques.get(indice);

        System.out.println("\nDocumento encontrado:");
        System.out.println(atraqueEditar.obtenerInfo());

        System.out.println("\n¿Qué deseas editar?");
        System.out.println("1. Fecha de atraque");
        System.out.println("2. Cancelar");
        System.out.print("Opción: ");

        int opcion = -1;
        try {
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida.");
            return;
        }

        if (opcion == 1) {
            System.out.println("Fecha actual: " + atraqueEditar.getFechaAtraque());
            LocalDate nuevaFecha = null;
            while (nuevaFecha == null) {
                System.out.print("Nueva fecha de atraque (dd/MM/yyyy): ");
                nuevaFecha = ValidarFecha.validarFecha(leer.nextLine());
            }

            // Nota: Como no hay setter en DocumentoAtraque, informamos al usuario
            System.out.println("\nNota: La fecha de atraque es un dato inmutable.");
            System.out.println("Para cambiarla, debes eliminar este documento y crear uno nuevo.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    /**
     * Sobreescritura del método eliminarDocumento() para adaptarlo a un
     * documento de atraque.
     */
    @Override
    public void eliminarDocumento() {
        System.out.println("\n--- ELIMINAR DOCUMENTO DE ATRAQUE ---");

        if (listaAtraques.isEmpty()) {
            System.out.println("No hay documentos de atraque registrados.");
            return;
        }

        // Listar todos los atraques
        System.out.println("\n=== ATRAQUES REGISTRADOS ===");
        for (int i = 0; i < listaAtraques.size(); i++) {
            DocumentoAtraque atraque = listaAtraques.get(i);
            System.out.printf("%d. %s en %s - Fecha: %s\n",
                    (i + 1),
                    atraque.getBarco().getNombre(),
                    atraque.getPuerto().getNombre(),
                    atraque.getFechaAtraque());
        }

        System.out.print("\nSelecciona el número del atraque a eliminar: ");
        int indice = -1;
        try {
            indice = Integer.parseInt(leer.nextLine()) - 1;
            if (indice < 0 || indice >= listaAtraques.size()) {
                System.out.println("Error: Número de atraque inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        DocumentoAtraque atraqueEliminar = listaAtraques.get(indice);

        System.out.println("\nDocumento a eliminar:");
        System.out.println(atraqueEliminar.obtenerInfo());
        System.out.print("\n¿Estás seguro de eliminar este documento? (S/N): ");
        String confirmacion = leer.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            this.listaAtraques.remove(atraqueEliminar);
            System.out.println("✓ Documento de atraque eliminado exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    /**
     * Sobreescritura del método consultarDocumento() para adaptarlo a un
     * documento de atraque.
     */
    @Override
    public void consultarDocumento() {
        System.out.println("\n--- CONSULTAR DOCUMENTOS DE ATRAQUE ---");

        if (listaAtraques.isEmpty()) {
            System.out.println("No hay documentos de atraque registrados.");
            return;
        }

        System.out.println("Opciones de consulta:");
        System.out.println("1. Consultar por barco");
        System.out.println("2. Consultar por puerto");
        System.out.println("3. Listar todos los atraques");
        System.out.print("Opción: ");

        int opcion = -1;
        try {
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida.");
            return;
        }

        switch (opcion) {
            case 1 ->
                consultarPorBarco();
            case 2 ->
                consultarPorPuerto();
            case 3 ->
                listarTodosAtraques();
            default ->
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Método que consulta atraques por barco.
     */
    private void consultarPorBarco() {
        System.out.print("Ingresa la matrícula del barco: ");
        String matricula = leer.nextLine().trim();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("ATRAQUES DEL BARCO: " + matricula);
        System.out.println("=".repeat(70));

        boolean encontrado = false;
        for (DocumentoAtraque atraque : listaAtraques) {
            if (atraque.getBarco().getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println(atraque.obtenerInfo());
                System.out.println("-".repeat(70));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron atraques para el barco: " + matricula);
        }
    }

    /**
     * Método que consulta atraques por puerto.
     */
    private void consultarPorPuerto() {
        System.out.print("Ingresa el código del puerto: ");
        String codigo = leer.nextLine().trim().toUpperCase();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("ATRAQUES EN EL PUERTO: " + codigo);
        System.out.println("=".repeat(70));

        boolean encontrado = false;
        for (DocumentoAtraque atraque : listaAtraques) {
            if (atraque.getPuerto().getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println(atraque.obtenerInfo());
                System.out.println("-".repeat(70));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron atraques para el puerto: " + codigo);
        }
    }

    /**
     * Método que lista todos los atraques registrados.
     */
    private void listarTodosAtraques() {
        System.out.println("\n" + "=".repeat(90));
        System.out.println("LISTADO DE TODOS LOS ATRAQUES REGISTRADOS");
        System.out.println("=".repeat(90));
        System.out.printf("%-20s %-15s %-25s %-20s %s\n",
                "BARCO", "MATRÍCULA", "PUERTO", "CÓDIGO PUERTO", "FECHA");
        System.out.println("-".repeat(90));

        for (DocumentoAtraque atraque : listaAtraques) {
            System.out.printf("%-20s %-15s %-25s %-20s %s\n",
                    atraque.getBarco().getNombre(),
                    atraque.getBarco().getMatricula(),
                    atraque.getPuerto().getNombre(),
                    atraque.getPuerto().getCodigo(),
                    atraque.getFechaAtraque());
        }

        System.out.println("=".repeat(90));
        System.out.println("Total de atraques: " + listaAtraques.size());
    }

    /**
     * Obtiene la lista de atraques
     *
     * @return Lista de documentos de atraque
     */
    public List<DocumentoAtraque> getListaAtraques() {
        return this.listaAtraques;
    }

}
