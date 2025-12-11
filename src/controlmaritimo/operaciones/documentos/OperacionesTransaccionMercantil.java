/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.documentos;

import controlmaritimo.abstracciones.interfaces.IOperacionesDocumentos;
import controlmaritimo.modelos.documentos.DocumentoAtraque;
import controlmaritimo.modelos.documentos.TransaccionMercantil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que implementa las operaciones CRUD para la administración de
 * transacciones mercantiles
 *
 * @version 1.4 09/12/2025
 *
 * @author Emilio Álvarez Villalobos
 * @author Nicolás Yazid Cruz Hernández
 * @author Luis Darío Padilla Lopez
 * @author Isaac Ádriano Vazquez Torres
 */
public class OperacionesTransaccionMercantil implements IOperacionesDocumentos {

    private List<TransaccionMercantil> listaTransacciones;
    private List<DocumentoAtraque> listaAtraques;
    private Scanner leer;
    private DateTimeFormatter formatter;

    /**
     * Constructor que inicializa las listas necesarias
     *
     * @param transacciones Lista de transacciones mercantiles
     * @param atraques Lista de documentos de atraque disponibles
     */
    public OperacionesTransaccionMercantil(List<TransaccionMercantil> transacciones,
            List<DocumentoAtraque> atraques) {
        this.listaTransacciones = transacciones;
        this.listaAtraques = atraques;
        this.leer = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }
    
    /**
     * Sobreescritura del método crearDocumento() para adaptarlo a un documento
     * de una transacción mercantil.
     */
    @Override
    public void crearDocumento() {
        System.out.println("\n--- REGISTRO DE TRANSACCIÓN MERCANTIL ---");

        // Verificar que existan atraques registrados
        if (listaAtraques.isEmpty()) {
            System.out.println("Error: No hay documentos de atraque registrados.");
            System.out.println("Primero debes registrar al menos un atraque.");
            return;
        }

        // Generar clave de transacción automática
        String claveTransaccion = generarClaveTransaccion();
        System.out.println("Clave de transacción generada: " + claveTransaccion);

        // Seleccionar atraque
        System.out.println("\n=== ATRAQUES DISPONIBLES ===");
        for (int i = 0; i < listaAtraques.size(); i++) {
            DocumentoAtraque atraque = listaAtraques.get(i);
            System.out.printf("%d. Barco: %s (%s) - Puerto: %s - Fecha: %s\n",
                    (i + 1),
                    atraque.getBarco().getNombre(),
                    atraque.getBarco().getMatricula(),
                    atraque.getPuerto().getNombre(),
                    atraque.getFechaAtraque());
        }

        System.out.print("\nSelecciona el número del atraque: ");
        int indiceAtraque = -1;
        try {
            indiceAtraque = Integer.parseInt(leer.nextLine()) - 1;
            if (indiceAtraque < 0 || indiceAtraque >= listaAtraques.size()) {
                System.out.println("Error: Número de atraque inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        DocumentoAtraque atraqueSeleccionado = listaAtraques.get(indiceAtraque);

        // Seleccionar tipo de transacción
        System.out.println("\n=== TIPOS DE TRANSACCIÓN ===");
        System.out.println("1. CARGA");
        System.out.println("2. DESCARGA");
        System.out.println("3. EMBARQUE");
        System.out.println("4. DESEMBARQUE");
        System.out.println("5. ABASTECIMIENTO");
        System.out.print("\nSelecciona el tipo de transacción: ");

        String tipoTransaccion = "";
        try {
            int opcionTipo = Integer.parseInt(leer.nextLine());
            switch (opcionTipo) {
                case 1 ->
                    tipoTransaccion = "CARGA";
                case 2 ->
                    tipoTransaccion = "DESCARGA";
                case 3 ->
                    tipoTransaccion = "EMBARQUE";
                case 4 ->
                    tipoTransaccion = "DESEMBARQUE";
                case 5 ->
                    tipoTransaccion = "ABASTECIMIENTO";
                default -> {
                    System.out.println("Opción no válida.");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        // Ingresar cantidad
        System.out.print("\nCantidad (según tipo de barco): ");
        double cantidad = 0;
        try {
            cantidad = Double.parseDouble(leer.nextLine());
            if (cantidad <= 0) {
                System.out.println("Error: La cantidad debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        // Ingresar fecha y hora
        LocalDateTime fechaHora = null;
        while (fechaHora == null) {
            System.out.print("Fecha y hora de la transacción (dd/MM/yyyy HH:mm): ");
            String fechaStr = leer.nextLine();
            fechaHora = validarFechaHora(fechaStr);
        }

        // Crear la transacción mercantil
        TransaccionMercantil nuevaTransaccion = new TransaccionMercantil(
                claveTransaccion,
                fechaHora,
                tipoTransaccion,
                cantidad,
                atraqueSeleccionado
        );

        // Agregar la transacción a la lista y al atraque
        this.listaTransacciones.add(nuevaTransaccion);
        atraqueSeleccionado.agregarTransaccion(nuevaTransaccion);

        System.out.println("\n✓ Transacción mercantil registrada exitosamente.");
        System.out.println("\n--- RESUMEN DE LA TRANSACCIÓN ---");
        System.out.println(nuevaTransaccion.obtenerInfo());
    }
    
    /**
     * Sobreescritura del método editarDocumento() para adaptarlo a un documento
     * de transacción mercantil.
     */
    @Override
    public void editarDocumento() {
        System.out.println("\n--- EDITAR TRANSACCIÓN MERCANTIL ---");

        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones mercantiles registradas.");
            return;
        }

        // Listar todas las transacciones
        System.out.println("\n=== TRANSACCIONES REGISTRADAS ===");
        for (int i = 0; i < listaTransacciones.size(); i++) {
            TransaccionMercantil trans = listaTransacciones.get(i);
            System.out.printf("%d. %s - %s - %.2f - %s\n",
                    (i + 1),
                    trans.getClaveTransaccion(),
                    trans.getTipoTransaccion(),
                    trans.getCantidad(),
                    trans.getFechaHora().format(formatter));
        }

        System.out.print("\nSelecciona el número de la transacción a editar: ");
        int indice = -1;
        try {
            indice = Integer.parseInt(leer.nextLine()) - 1;
            if (indice < 0 || indice >= listaTransacciones.size()) {
                System.out.println("Error: Número de transacción inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        TransaccionMercantil transaccionEditar = listaTransacciones.get(indice);

        System.out.println("\nTransacción encontrada:");
        System.out.println(transaccionEditar.obtenerInfo());

        System.out.println("\n¿Qué deseas editar?");
        System.out.println("1. Tipo de transacción");
        System.out.println("2. Cantidad");
        System.out.println("3. Fecha y hora");
        System.out.println("4. Cancelar");
        System.out.print("Opción: ");

        int opcion = -1;
        try {
            opcion = Integer.parseInt(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida.");
            return;
        }

        switch (opcion) {
            case 1 -> {
                System.out.println("Tipo actual: " + transaccionEditar.getTipoTransaccion());
                System.out.println("\n=== TIPOS DE TRANSACCIÓN ===");
                System.out.println("1. CARGA");
                System.out.println("2. DESCARGA");
                System.out.println("3. EMBARQUE");
                System.out.println("4. DESEMBARQUE");
                System.out.println("5. ABASTECIMIENTO");
                System.out.print("\nSelecciona el nuevo tipo: ");

                try {
                    int opcionTipo = Integer.parseInt(leer.nextLine());
                    String nuevoTipo = switch (opcionTipo) {
                        case 1 ->
                            "CARGA";
                        case 2 ->
                            "DESCARGA";
                        case 3 ->
                            "EMBARQUE";
                        case 4 ->
                            "DESEMBARQUE";
                        case 5 ->
                            "ABASTECIMIENTO";
                        default ->
                            null;
                    };

                    if (nuevoTipo != null) {
                        transaccionEditar.setTipoTransaccion(nuevoTipo);
                        System.out.println("✓ Tipo actualizado.");
                    } else {
                        System.out.println("Opción no válida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error al ingresar opción.");
                }
            }
            case 2 -> {
                System.out.println("Cantidad actual: " + transaccionEditar.getCantidad());
                System.out.print("Nueva cantidad: ");
                try {
                    double nuevaCantidad = Double.parseDouble(leer.nextLine());
                    if (nuevaCantidad > 0) {
                        transaccionEditar.setCantidad(nuevaCantidad);
                        System.out.println("✓ Cantidad actualizada.");
                    } else {
                        System.out.println("Error: La cantidad debe ser mayor a 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debes ingresar un número válido.");
                }
            }
            case 3 -> {
                System.out.println("Fecha/hora actual: " + transaccionEditar.getFechaHora().format(formatter));
                LocalDateTime nuevaFecha = null;
                while (nuevaFecha == null) {
                    System.out.print("Nueva fecha y hora (dd/MM/yyyy HH:mm): ");
                    nuevaFecha = validarFechaHora(leer.nextLine());
                }
                transaccionEditar.setFechaHora(nuevaFecha);
                System.out.println("✓ Fecha/hora actualizada.");
            }
            case 4 ->
                System.out.println("Operación cancelada.");
            default ->
                System.out.println("Opción no válida.");
        }
    }
    
    /**
     * Sobreescritura del método eliminarDocumento() para adaptarlo a un
     * documento de transacción mercantil.
     */
    @Override
    public void eliminarDocumento() {
        System.out.println("\n--- ELIMINAR TRANSACCIÓN MERCANTIL ---");

        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones mercantiles registradas.");
            return;
        }

        // Listar todas las transacciones
        System.out.println("\n=== TRANSACCIONES REGISTRADAS ===");
        for (int i = 0; i < listaTransacciones.size(); i++) {
            TransaccionMercantil trans = listaTransacciones.get(i);
            System.out.printf("%d. %s - %s - %.2f - %s\n",
                    (i + 1),
                    trans.getClaveTransaccion(),
                    trans.getTipoTransaccion(),
                    trans.getCantidad(),
                    trans.getFechaHora().format(formatter));
        }

        System.out.print("\nSelecciona el número de la transacción a eliminar: ");
        int indice = -1;
        try {
            indice = Integer.parseInt(leer.nextLine()) - 1;
            if (indice < 0 || indice >= listaTransacciones.size()) {
                System.out.println("Error: Número de transacción inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        TransaccionMercantil transaccionEliminar = listaTransacciones.get(indice);

        System.out.println("\nTransacción a eliminar:");
        System.out.println(transaccionEliminar.obtenerInfo());
        System.out.print("\n¿Estás seguro de eliminar esta transacción? (S/N): ");
        String confirmacion = leer.nextLine().trim().toUpperCase();

        if (confirmacion.equals("S")) {
            this.listaTransacciones.remove(transaccionEliminar);
            System.out.println("✓ Transacción mercantil eliminada exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    /**
     * Sobreescritura del método consultarDocumento() para adaptarlo a un
     * documento de transacción mercantil.
     */
    @Override
    public void consultarDocumento() {
        System.out.println("\n--- CONSULTAR TRANSACCIONES MERCANTILES ---");

        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones mercantiles registradas.");
            return;
        }

        System.out.println("Opciones de consulta:");
        System.out.println("1. Consultar por clave de transacción");
        System.out.println("2. Consultar por tipo de transacción");
        System.out.println("3. Consultar por barco");
        System.out.println("4. Listar todas las transacciones");
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
                consultarPorClave();
            case 2 ->
                consultarPorTipo();
            case 3 ->
                consultarPorBarco();
            case 4 ->
                listarTodasTransacciones();
            default ->
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Método que consulta transacciones por clave.
     */
    private void consultarPorClave() {
        System.out.print("Ingresa la clave de la transacción: ");
        String clave = leer.nextLine().trim().toUpperCase();

        boolean encontrado = false;
        for (TransaccionMercantil trans : listaTransacciones) {
            if (trans.getClaveTransaccion().equalsIgnoreCase(clave)) {
                System.out.println("\n" + "=".repeat(70));
                System.out.println(trans.obtenerInfo());
                System.out.println("=".repeat(70));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró una transacción con la clave: " + clave);
        }
    }

    /**
     * Método que consulta transacciones por tipo.
     */
    private void consultarPorTipo() {
        System.out.println("\n=== TIPOS DE TRANSACCIÓN ===");
        System.out.println("1. CARGA");
        System.out.println("2. DESCARGA");
        System.out.println("3. EMBARQUE");
        System.out.println("4. DESEMBARQUE");
        System.out.println("5. ABASTECIMIENTO");
        System.out.print("\nSelecciona el tipo: ");

        String tipo = "";
        try {
            int opcionTipo = Integer.parseInt(leer.nextLine());
            tipo = switch (opcionTipo) {
                case 1 ->
                    "CARGA";
                case 2 ->
                    "DESCARGA";
                case 3 ->
                    "EMBARQUE";
                case 4 ->
                    "DESEMBARQUE";
                case 5 ->
                    "ABASTECIMIENTO";
                default ->
                    null;
            };
        } catch (NumberFormatException e) {
            System.out.println("Error al ingresar opción.");
            return;
        }

        if (tipo == null) {
            System.out.println("Tipo no válido.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("TRANSACCIONES DE TIPO: " + tipo);
        System.out.println("=".repeat(70));

        boolean encontrado = false;
        for (TransaccionMercantil trans : listaTransacciones) {
            if (trans.getTipoTransaccion().equalsIgnoreCase(tipo)) {
                System.out.println(trans.obtenerInfo());
                System.out.println("-".repeat(70));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron transacciones del tipo: " + tipo);
        }
    }

    /**
     * Método que consulta transacciones por barco.
     */
    private void consultarPorBarco() {
        System.out.print("Ingresa la matrícula del barco: ");
        String matricula = leer.nextLine().trim();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("TRANSACCIONES DEL BARCO: " + matricula);
        System.out.println("=".repeat(70));

        boolean encontrado = false;
        for (TransaccionMercantil trans : listaTransacciones) {
            if (trans.getAtraque().getBarco().getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println(trans.obtenerInfo());
                System.out.println("-".repeat(70));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron transacciones para el barco: " + matricula);
        }
    }

    /**
     * Método que lista todas las transacciones registradas.
     */
    private void listarTodasTransacciones() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("LISTADO DE TODAS LAS TRANSACCIONES MERCANTILES");
        System.out.println("=".repeat(100));
        System.out.printf("%-15s %-20s %-20s %-12s %-15s %s\n",
                "CLAVE", "TIPO", "FECHA/HORA", "CANTIDAD", "BARCO", "PUERTO");
        System.out.println("-".repeat(100));

        for (TransaccionMercantil trans : listaTransacciones) {
            System.out.printf("%-15s %-20s %-20s %-12.2f %-15s %s\n",
                    trans.getClaveTransaccion(),
                    trans.getTipoTransaccion(),
                    trans.getFechaHora().format(formatter),
                    trans.getCantidad(),
                    trans.getAtraque().getBarco().getMatricula(),
                    trans.getAtraque().getPuerto().getCodigo());
        }

        System.out.println("=".repeat(100));
        System.out.println("Total de transacciones: " + listaTransacciones.size());
    }

    /**
     * Método que genera una clave única para la transacción.
     *
     * @return Clave de transacción en formato TM-YYYYMMDD-NNNN
     */
    private String generarClaveTransaccion() {
        LocalDateTime ahora = LocalDateTime.now();
        String fecha = ahora.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int consecutivo = listaTransacciones.size() + 1;
        return String.format("TM-%s-%04d", fecha, consecutivo);
    }

    /**
     * Método que válida y convierte una cadena a LocalDateTime.
     *
     * @param fechaStr Fecha en formato dd/MM/yyyy HH:mm
     * @return LocalDateTime o null si es inválida
     */
    private LocalDateTime validarFechaHora(String fechaStr) {
        try {
            return LocalDateTime.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha/hora inválido. Usa dd/MM/yyyy HH:mm");
            return null;
        }
    }

    /**
     * Método que obtiene la lista de transacciones.
     *
     * @return Lista de transacciones mercantiles
     */
    public List<TransaccionMercantil> getListaTransacciones() {
        return this.listaTransacciones;
    }
}
