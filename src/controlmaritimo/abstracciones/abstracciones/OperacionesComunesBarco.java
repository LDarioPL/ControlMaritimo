/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.abstracciones.abstracciones;

import controlmaritimo.abstracciones.interfaces.IOperacionesBarco;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase contiene las operaciones comunes para todos los tipos de barco
 * existentes, incluye operaciones de registro, actualización, eliminación y
 * consulta, esta clase abstracta sirve de base para las operaciones de cada
 * tipo de barco.
 *
 * @version 1.1 06/12/2025
 *
 * @author yazid
 * @author 
 * @author 
 * @author 
 */
public abstract class OperacionesComunesBarco implements IOperacionesBarco {
    
    // Atributos protegidos para que las hijas (Pasajeros, Carga, Pesca) los usen
    protected List<Barco> listaBarcos;
    protected Scanner leer;

    public OperacionesComunesBarco(List<Barco> listaBarcos) {
        this.listaBarcos = listaBarcos;
        this.leer = new Scanner(System.in);
    }

    // =========================================================
    // IMPLEMENTACIÓN DE MÉTODOS COMUNES (Consultar y Eliminar)
    // =========================================================

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
                case 1 -> listarTodos();
                case 2 -> buscarYMostrarPorMatricula();
                default -> System.out.println("Opción no válida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingresa un número válido.");
        }
    }

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

        // Recorremos la lista al revés o usamos removeIf, 
        // pero para hacerlo clásico y claro usamos un ciclo simple:
        for (int i = 0; i < listaBarcos.size(); i++) {
            Barco b = listaBarcos.get(i);
            
            // Verificamos matrícula y que sea del tipo correcto
            if (b.getMatricula().equalsIgnoreCase(matricula) && esTipoValido(b)) {
                listaBarcos.remove(i);
                System.out.println("¡El barco con matrícula " + matricula + " ha sido eliminado exitosamente!");
                eliminado = true;
                break; // Rompemos el ciclo porque ya lo borramos
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró ningún barco con la matrícula: " + matricula);
        }
    }

    // =========================================================
    // MÉTODOS AUXILIARES (Privados/Protegidos)
    // =========================================================

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
    
    protected Barco buscarBarcoPorMatricula(String matricula) {
        for (Barco b : listaBarcos) {
            if (b.getMatricula().equalsIgnoreCase(matricula)) {
                return b;
            }
        }
        return null;
    }

    // =========================================================
    // DEFINICIÓN DE MÉTODOS ABSTRACTOS (Responsabilidad de los hijos)
    // =========================================================

    // Estos métodos NO se implementan aquí. 
    // Al declararlos abstractos, OBLIGAS a las clases hijas a tenerlos.
    @Override
    public abstract void registrarBarco();

    @Override
    public abstract void editarDatosBarco();
    
    // ESTE ES NUEVO: Sirve para que 'listarTodos' sepa qué imprimir sin usar instanceof hardcodeado
    protected abstract boolean esTipoValido(Barco b);
    
}
