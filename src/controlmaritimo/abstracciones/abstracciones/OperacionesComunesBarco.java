/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.abstracciones.abstracciones;

import controlmaritimo.abstracciones.interfaces.IOperacionesBarco;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase contiene todas las operaciones comunes para todos los tipos de
 * barcos existentes, incluye operaciones de registro, actualización,
 * eliminación y consulta de datos, esta clase abstracta sirve como base para
 * las operaciones que debe realizar de cada tipo de barco.
 *
 * @version 1.1 06/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 */
public abstract class OperacionesComunesBarco implements IOperacionesBarco {

    // Atributos que usaran las clases que hereden (tipos de barco)
    protected List<Barco> listaBarcos;
    protected Scanner leer;

    // Método constructor sobrecargado
    public OperacionesComunesBarco(List<Barco> listaBarcos) {
        this.listaBarcos = listaBarcos;
        this.leer = new Scanner(System.in);
    }

    /**
     * Método común para consultar los datos de cualquier tipo de barco.
     */
    @Override
    public void consultarDatosBarco() {
        System.out.println("\n--- CONSULTA DE BARCOS ---");

        // 1. Validar si la lista está vacía
        if (listaBarcos.isEmpty()) {
            System.out.println("No hay barcos registrados en el sistema actualmente.");
            return;
        }

        System.out.println("Seleccione una opción de consulta:");
        System.out.println("1. Listar todos los barcos de esta categoría");
        System.out.println("2. Buscar un barco específico por matrícula");

        try {
            int opcion = Integer.parseInt(leer.nextLine());

            switch (opcion) {
                case 1 ->
                    listarTodos();
                case 2 ->
                    buscarYMostrarPorMatricula();
                default ->
                    System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingresa un número válido.");
        }
    }

    /**
     * Método común para eliminar cualquier tipo de barco.
     */
    @Override
    public void eliminarBarco() {
        System.out.println("\n--- ELIMINAR BARCO ---");

        if (listaBarcos.isEmpty()) {
            System.out.println("No hay barcos para eliminar.");
            return;
        }

        System.out.print("Ingresa la matrícula del barco a eliminar: ");
        String matricula = leer.nextLine();

        // Usamos una variable auxiliar para saber si lo borramos
        boolean eliminado = false;

        // Recorremos la lista al reves
        for (int i = 0; i < listaBarcos.size(); i++) {
            Barco b = listaBarcos.get(i);

            // Verificamos la matricula y que sea del tipo correcto
            if ((b.getMatricula().equalsIgnoreCase(matricula)) && (esTipoValido(b))) {
                listaBarcos.remove(i);
                System.out.println("> El barco con matrícula " + matricula + " ha sido eliminado exitosamente.");
                eliminado = true;
                break; // Se rompe el ciclo porque el barco ya ha sido borrado
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró ningún barco con la matrícula: " + matricula);
        }
    }

    /**
     * Método auxiliar para listar todos los barcos.
     */
    private void listarTodos() {
        System.out.println("\n--- LISTADO GENERAL ---");
        boolean hayBarcosDelTipo = false;

        for (Barco barco : listaBarcos) {
            // Aquí usamos el método abstracto para filtrar
            // Si estamos en OperacionesPasajeros, solo mostrará pasajeros.
            if (esTipoValido(barco)) {
                System.out.println("---------------------------------");
                System.out.println(barco.obtenerInformacionBarco());
                hayBarcosDelTipo = true;
            }
        }

        if (!hayBarcosDelTipo) {
            System.out.println("No hay barcos de este tipo registrados aún.");
        } else {
            System.out.println("---------------------------------");
        }
    }

    /**
     * Método auxiliar para buscar y mostrar un barco según una matricula.
     */
    private void buscarYMostrarPorMatricula() {
        System.out.print("Ingresa la matrícula a buscar: ");
        String matriculaBusqueda = leer.nextLine();
        boolean encontrado = false;

        for (Barco barco : listaBarcos) {
            if (barco.getMatricula().equalsIgnoreCase(matriculaBusqueda) && esTipoValido(barco)) {
                System.out.println("\n¡BARCO ENCONTRADO!");
                System.out.println("---------------------------------");
                System.out.println(barco.obtenerInformacionBarco());
                System.out.println("---------------------------------");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún barco con la matrícula: " + matriculaBusqueda);
        }
    }

    /**
     * Método que sirve para buscar un barco según una matricula, pero no
     * muestra la información.
     *
     * @param matricula
     * @return null si no se encontro el barco
     */
    protected Barco buscarBarcoPorMatricula(String matricula) {
        for (Barco b : listaBarcos) {
            if (b.getMatricula().equalsIgnoreCase(matricula)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Método abstracto para registrar un barco, su implementación dependerá de
     * la clase que herede de esta.
     */
    @Override
    public abstract void registrarBarco();

    /**
     * Método abstracto para editar los datos un barco, su implementación
     * dependerá de la clase que herede de esta.
     */
    @Override
    public abstract void editarDatosBarco();
    
    /**
     * Sirve para que el método listarTodos() conozca qué debe imprimir sin
     * utilizar instanceof
     * 
     * @param b
     * @return 
     */
    protected abstract boolean esTipoValido(Barco b);

}
