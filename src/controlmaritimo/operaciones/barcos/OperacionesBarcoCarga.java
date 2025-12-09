/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.abstracciones.abstracciones.OperacionesComunesBarco;
import controlmaritimo.modelos.barcos.BarcoCarga;
import controlmaritimo.utilidades.ValidarFecha;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author yazid
 */
public class OperacionesBarcoCarga extends OperacionesComunesBarco {
    
    public OperacionesBarcoCarga(List<Barco> listaBarcos) {
        super(listaBarcos); // Pasamos la lista al padre
    }

    @Override
    public void registrarBarco() {

        System.out.println("\n--- REGISTRO DE BARCOS DE CARGA ---");

        // 1. Pedimos datos COMUNES (Usamos 'leer' que viene del padre)
        System.out.print("Matrícula: ");
        String matricula = leer.nextLine().trim();
        // (Aquí podrías validar si ya existe usando this.buscarBarcoPorMatricula(matricula))

        // Validación básica para no duplicar matrículas
        if (buscarBarcoPorMatricula(matricula) != null) {
            System.out.println("Error: Ya existe un barco con esa matrícula.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        System.out.print("Bandera: ");
        String bandera = leer.nextLine();

        System.out.print("Peso en toneladas: ");
        double peso = Double.parseDouble(leer.nextLine());

        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Fecha Botadura (dd/MM/yyyy): ");
            fecha = ValidarFecha.validarFecha(leer.nextLine());
        }

        // Datos especificos del barco de carga
        System.out.println("Límite de contenedores: ");
        int limite = Integer.parseInt(leer.nextLine());

        System.out.println("Cantidad actual: ");
        int cantidad = Integer.parseInt(leer.nextLine());

        Barco nuevoBarco = new BarcoCarga(matricula, bandera, nombre, peso, fecha, limite, cantidad);
        this.listaBarcos.add(nuevoBarco);
        System.out.println("Registro exitoso.");
    }

    @Override
    public void editarDatosBarco() {
        System.out.println("\n--- EDICIÓN DE BARCO DE CARGA ---");
        System.out.print("Ingresa la matrícula del barco a editar: ");
        String matricula = leer.nextLine();

        // 1. Buscamos el barco
        Barco barcoEncontrado = buscarBarcoPorMatricula(matricula);

        // 2. Verificamos tipo y existencia
        if (barcoEncontrado != null && esTipoValido(barcoEncontrado)) {

            // CASTING: Convertimos a BarcoCarga para acceder a sus métodos
            BarcoCarga barcoEditar = (BarcoCarga) barcoEncontrado;

            System.out.println("Editando barco: " + barcoEditar.getNombre());
            System.out.println("Selecciona el dato a actualizar:");
            System.out.println("1. Nombre");
            System.out.println("2. Bandera");
            System.out.println("3. Peso");
            System.out.println("4. Fecha de Botadura");
            System.out.println("5. Límite de Contenedores");
            System.out.println("6. Cantidad de Contenedores");
            System.out.println("7. Cancelar");
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
                    System.out.println("Nombre actual: " + barcoEditar.getNombre());
                    System.out.print("Nuevo nombre: ");
                    barcoEditar.setNombre(leer.nextLine());
                    System.out.println("Dato actualizado.");
                }
                case 2 -> {
                    System.out.println("Bandera actual: " + barcoEditar.getBandera());
                    System.out.print("Nueva bandera: ");
                    barcoEditar.setBandera(leer.nextLine());
                    System.out.println("Dato actualizado.");
                }
                case 3 -> {
                    System.out.println("Peso actual: " + barcoEditar.getPesoToneladas());
                    System.out.print("Nuevo peso: ");
                    try {
                        barcoEditar.setPesoToneladas(Double.parseDouble(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingresa un número válido.");
                    }
                }
                case 4 -> {
                    System.out.println("Fecha actual: " + barcoEditar.getFechaBotadura());
                    LocalDate nuevaFecha = null;
                    while (nuevaFecha == null) {
                        System.out.print("Nueva Fecha Botadura (dd/MM/yyyy): ");
                        nuevaFecha = ValidarFecha.validarFecha(leer.nextLine());
                    }
                    barcoEditar.setFechaBotadura(nuevaFecha);
                    System.out.println("Dato actualizado.");
                }
                case 5 -> { // Dato ESPECÍFICO de Carga
                    System.out.println("Límite actual: " + barcoEditar.getLimiteContenedores());
                    System.out.print("Nuevo límite: ");
                    try {
                        barcoEditar.setLimiteContenedores(Integer.parseInt(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 6 -> { // Dato ESPECÍFICO de Carga
                    System.out.println("Cantidad actual: " + barcoEditar.getCantidadContenedores());
                    System.out.print("Nueva cantidad: ");
                    try {
                        barcoEditar.setCantidadContenedores(Integer.parseInt(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 7 ->
                    System.out.println("Operación cancelada.");
                default ->
                    System.out.println("Opción no válida.");
            }

        } else {
            System.out.println("No se encontró un Barco de Carga con la matrícula: " + matricula);
        }
    }

    @Override
    protected boolean esTipoValido(Barco b) {
        return b instanceof BarcoCarga;
    }
    
}
