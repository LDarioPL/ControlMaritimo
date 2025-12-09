/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.abstracciones.abstracciones.OperacionesComunesBarco;
import controlmaritimo.modelos.barcos.BarcoPasajeros;
import controlmaritimo.utilidades.ValidarFecha;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author yazid
 */
public class OperacionesBarcoPasajeros extends OperacionesComunesBarco {
    
    public OperacionesBarcoPasajeros(List<Barco> barcos) {
        super(barcos);
    }

    @Override
    public void registrarBarco() {
        System.out.println("\n--- REGISTRO DE BARCO DE PASAJEROS ---");
        System.out.print("Matrícula: ");
        String matricula = leer.nextLine().trim(); //trim() elimina los espacios en blanco de la matricula

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

        System.out.print("Límite Pasajeros: ");
        int limite = Integer.parseInt(leer.nextLine());
        System.out.print("Cantidad Actual: ");
        int cantidad = Integer.parseInt(leer.nextLine());
        System.out.print("Pisos: ");
        int pisos = Integer.parseInt(leer.nextLine());
        System.out.print("Tipo Viaje: ");
        String viaje = leer.nextLine();

        Barco nuevo = new BarcoPasajeros(matricula, bandera, nombre, peso, fecha, limite, cantidad, pisos, viaje);
        this.listaBarcos.add(nuevo);
        System.out.println("Registro exitoso.");

    }

    @Override
    public void editarDatosBarco() {
        System.out.println("\n--- EDICIÓN DE BARCO DE PASAJEROS ---");
        System.out.print("Ingresa la matrícula del barco a editar: ");
        String matricula = leer.nextLine();

        // 1. Buscamos usando el método del padre
        Barco barcoEncontrado = buscarBarcoPorMatricula(matricula);

        // 2. Verificamos que exista y que sea del tipo correcto
        if (barcoEncontrado != null && esTipoValido(barcoEncontrado)) {

            // HACEMOS CASTING: Convertimos la referencia genérica 'Barco' a 'BarcoPasajeros'
            // Esto es necesario para acceder a los métodos setNumeroPisos, setTipoViaje, etc.
            BarcoPasajeros barcoEditar = (BarcoPasajeros) barcoEncontrado;

            System.out.println("Barco encontrado: " + barcoEditar.getNombre());
            System.out.println("Selecciona el dato que deseas actualizar:");
            System.out.println("1. Nombre");
            System.out.println("2. Bandera");
            System.out.println("3. Peso");
            System.out.println("4. Fecha de Botadura");
            System.out.println("5. Límite de Pasajeros");
            System.out.println("6. Cantidad de Pasajeros");
            System.out.println("7. Número de Pisos");
            System.out.println("8. Tipo de Viaje");
            System.out.println("9. Cancelar");
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
                        System.out.println("Error: Debes ingresar un número válido.");
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
                case 5 -> {
                    System.out.println("Límite actual: " + barcoEditar.getLimitePasajeros());
                    System.out.print("Nuevo límite: ");
                    try {
                        barcoEditar.setLimitePasajeros(Integer.parseInt(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 6 -> {
                    System.out.println("Cantidad actual: " + barcoEditar.getCantidadPasajeros());
                    System.out.print("Nueva cantidad: ");
                    try {
                        barcoEditar.setCantidadPasajeros(Integer.parseInt(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 7 -> {
                    System.out.println("Pisos actuales: " + barcoEditar.getNumeroPisos());
                    System.out.print("Nuevos pisos: ");
                    try {
                        barcoEditar.setNumeroPisos(Integer.parseInt(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 8 -> {
                    System.out.println("Tipo viaje actual: " + barcoEditar.getTipoViaje());
                    System.out.print("Nuevo tipo de viaje: ");
                    barcoEditar.setTipoViaje(leer.nextLine());
                    System.out.println("Dato actualizado.");
                }
                case 9 ->
                    System.out.println("Operación cancelada.");
                default ->
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("No se encontró un Barco de Pasajeros con la matrícula: " + matricula);
        }
    }

    @Override
    protected boolean esTipoValido(Barco b) {
        return b instanceof BarcoPasajeros;
    }
    
}
