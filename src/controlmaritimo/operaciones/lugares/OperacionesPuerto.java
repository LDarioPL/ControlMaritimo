/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.lugares;

import controlmaritimo.modelos.lugares.Puerto;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que se encarga de las operaciones del puerto.
 *
 * @version 1.3 09/12/2025
 *
 * @author Emilio Álvarez Villalobos
 * @author Nicolás Yazid Cruz Hernández
 * @author Luis Darío Padilla Lopez
 * @author Isaac Ádriano Vazquez Torres
 */
public class OperacionesPuerto {

    private List<Puerto> listaPuertos;
    private Scanner leer;

    /**
     * Constructor que inicializa la lista de puertos y el scanner
     *
     * @param puertos Lista de puertos a administrar
     */
    public OperacionesPuerto(List<Puerto> puertos) {
        this.listaPuertos = puertos;
        this.leer = new Scanner(System.in);
    }

    /**
     * Registra un nuevo puerto en el sistema
     */
    public void registrarPuerto() {
        System.out.println("\n--- REGISTRO DE PUERTO ---");
        System.out.print("Código del puerto: ");
        String codigo = leer.nextLine().trim().toUpperCase();

        // Validación para evitar códigos duplicados
        if (buscarPuertoPorCodigo(codigo) != null) {
            System.out.println("Error: Ya existe un puerto con ese código.");
            return;
        }

        System.out.print("Nombre del puerto: ");
        String nombre = leer.nextLine();

        System.out.print("Capacidad de barcos: ");
        int capacidad = 0;
        try {
            capacidad = Integer.parseInt(leer.nextLine());
            if (capacidad <= 0) {
                System.out.println("Error: La capacidad debe ser mayor a 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un número válido.");
            return;
        }

        System.out.print("Ciudad: ");
        String ciudad = leer.nextLine();

        System.out.print("País: ");
        String pais = leer.nextLine();

        Puerto nuevoPuerto = new Puerto(codigo, nombre, capacidad, ciudad, pais);
        this.listaPuertos.add(nuevoPuerto);
        System.out.println("✓ Puerto registrado exitosamente.");
    }

    /**
     * Edita los datos de un puerto existente
     */
    public void editarDatosPuerto() {
        System.out.println("\n--- EDICIÓN DE PUERTO ---");
        System.out.print("Ingresa el código del puerto a editar: ");
        String codigo = leer.nextLine().trim().toUpperCase();

        Puerto puertoEncontrado = buscarPuertoPorCodigo(codigo);

        if (puertoEncontrado != null) {
            System.out.println("Puerto encontrado: " + puertoEncontrado.getNombre());
            System.out.println("\nSelecciona el dato que deseas actualizar:");
            System.out.println("1. Nombre");
            System.out.println("2. Capacidad de barcos");
            System.out.println("3. Ciudad");
            System.out.println("4. País");
            System.out.println("5. Cancelar");
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
                    System.out.println("Nombre actual: " + puertoEncontrado.getNombre());
                    System.out.print("Nuevo nombre: ");
                    puertoEncontrado.setNombre(leer.nextLine());
                    System.out.println("✓ Dato actualizado.");
                }
                case 2 -> {
                    System.out.println("Capacidad actual: " + puertoEncontrado.getCapacidadBarcos());
                    System.out.print("Nueva capacidad: ");
                    try {
                        int nuevaCapacidad = Integer.parseInt(leer.nextLine());
                        if (nuevaCapacidad <= 0) {
                            System.out.println("Error: La capacidad debe ser mayor a 0.");
                        } else {
                            puertoEncontrado.setCapacidadBarcos(nuevaCapacidad);
                            System.out.println("✓ Dato actualizado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debes ingresar un número válido.");
                    }
                }
                case 3 -> {
                    System.out.println("Ciudad actual: " + puertoEncontrado.getCiudad());
                    System.out.print("Nueva ciudad: ");
                    puertoEncontrado.setCiudad(leer.nextLine());
                    System.out.println("✓ Dato actualizado.");
                }
                case 4 -> {
                    System.out.println("País actual: " + puertoEncontrado.getPais());
                    System.out.print("Nuevo país: ");
                    puertoEncontrado.setPais(leer.nextLine());
                    System.out.println("✓ Dato actualizado.");
                }
                case 5 ->
                    System.out.println("Operación cancelada.");
                default ->
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("No se encontró un puerto con el código: " + codigo);
        }
    }

    /**
     * Elimina un puerto del sistema
     */
    public void eliminarPuerto() {
        System.out.println("\n--- ELIMINAR PUERTO ---");
        System.out.print("Ingresa el código del puerto a eliminar: ");
        String codigo = leer.nextLine().trim().toUpperCase();

        Puerto puertoEncontrado = buscarPuertoPorCodigo(codigo);

        if (puertoEncontrado != null) {
            System.out.println("Puerto encontrado: " + puertoEncontrado.getNombre());
            System.out.println("Ubicación: " + puertoEncontrado.getCiudad() + ", " + puertoEncontrado.getPais());
            System.out.print("¿Estás seguro de eliminar este puerto? (S/N): ");
            String confirmacion = leer.nextLine().trim().toUpperCase();

            if (confirmacion.equals("S")) {
                this.listaPuertos.remove(puertoEncontrado);
                System.out.println("✓ Puerto eliminado exitosamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("No se encontró un puerto con el código: " + codigo);
        }
    }

    /**
     * Consulta y muestra los datos de un puerto
     */
    public void consultarDatosPuerto() {
        System.out.println("\n--- CONSULTAR PUERTO ---");

        if (this.listaPuertos.isEmpty()) {
            System.out.println("No hay puertos registrados en el sistema.");
            return;
        }

        System.out.println("Opciones de consulta:");
        System.out.println("1. Buscar puerto por código");
        System.out.println("2. Listar todos los puertos");
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
                consultarPorCodigo();
            case 2 ->
                listarTodosPuertos();
            default ->
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Consulta un puerto específico por su código
     */
    private void consultarPorCodigo() {
        System.out.print("Ingresa el código del puerto: ");
        String codigo = leer.nextLine().trim().toUpperCase();

        Puerto puertoEncontrado = buscarPuertoPorCodigo(codigo);

        if (puertoEncontrado != null) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println(puertoEncontrado.obtenerInfo());
            System.out.println("=".repeat(50));
        } else {
            System.out.println("No se encontró un puerto con el código: " + codigo);
        }
    }

    /**
     * Lista todos los puertos registrados en el sistema
     */
    private void listarTodosPuertos() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("LISTADO DE PUERTOS REGISTRADOS");
        System.out.println("=".repeat(70));
        System.out.printf("%-10s %-25s %-15s %-15s %s\n",
                "CÓDIGO", "NOMBRE", "CIUDAD", "PAÍS", "CAPACIDAD");
        System.out.println("-".repeat(70));

        for (Puerto puerto : this.listaPuertos) {
            System.out.printf("%-10s %-25s %-15s %-15s %d barcos\n",
                    puerto.getCodigo(),
                    puerto.getNombre(),
                    puerto.getCiudad(),
                    puerto.getPais(),
                    puerto.getCapacidadBarcos());
        }

        System.out.println("=".repeat(70));
        System.out.println("Total de puertos: " + this.listaPuertos.size());
    }

    /**
     * Busca un puerto por su código
     *
     * @param codigo Código del puerto a buscar
     * @return Puerto encontrado o null si no existe
     */
    private Puerto buscarPuertoPorCodigo(String codigo) {
        for (Puerto puerto : this.listaPuertos) {
            if (puerto.getCodigo().equalsIgnoreCase(codigo)) {
                return puerto;
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de puertos
     *
     * @return Lista de puertos
     */
    public List<Puerto> getListaPuertos() {
        return this.listaPuertos;
    }
}
