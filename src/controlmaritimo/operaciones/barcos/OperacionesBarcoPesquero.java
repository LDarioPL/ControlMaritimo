/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.barcos;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.abstracciones.abstracciones.OperacionesComunesBarco;
import controlmaritimo.modelos.barcos.BarcoPesquero;
import controlmaritimo.utilidades.ValidarFecha;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yazid
 */
public class OperacionesBarcoPesquero extends OperacionesComunesBarco {
    
    public OperacionesBarcoPesquero(List<Barco> barcos) {
        super(barcos);
    }

    @Override
    public void registrarBarco() {
        System.out.println("\n--- REGISTRO DE BARCO PESQUERO ---");

        // 1. Validar Matrícula (Ciclo para asegurar unicidad y no vacíos)
        String matricula;
        do {
            System.out.print("Matrícula: ");
            matricula = leer.nextLine().trim();
            if (matricula.isEmpty()) {
                System.out.println("La matrícula no puede estar vacía.");
            } else if (buscarBarcoPorMatricula(matricula) != null) {
                System.out.println("Error: Ya existe un barco con esa matrícula. Intenta con otra.");
                matricula = ""; // Forzamos repetir el ciclo
            }
        } while (matricula.isEmpty());

        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        System.out.print("Bandera: ");
        String bandera = leer.nextLine();

        // 2. Validar Peso (> 0)
        double peso = -1;
        do {
            try {
                System.out.print("Peso en toneladas: ");
                peso = Double.parseDouble(leer.nextLine());
                if (peso <= 0) System.out.println("El peso debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        } while (peso <= 0);

        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Fecha Botadura (dd/MM/yyyy): ");
            fecha = ValidarFecha.validarFecha(leer.nextLine());
        }

        // 3. Validar Límite de Capacidad (> 0)
        double limiteToneladas = -1;
        do {
            try {
                System.out.print("Límite de capacidad (toneladas): ");
                limiteToneladas = Double.parseDouble(leer.nextLine());
                if (limiteToneladas <= 0) System.out.println("El límite debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        } while (limiteToneladas <= 0);

        // 4. Validar Carga Actual (>= 0 y <= limite)
        double numToneladas = -1;
        boolean cargaValida = false;
        do {
            try {
                System.out.print("Carga actual (toneladas): ");
                numToneladas = Double.parseDouble(leer.nextLine());
                if (numToneladas < 0) {
                    System.out.println("La carga no puede ser negativa.");
                } else if (numToneladas > limiteToneladas) {
                    System.out.println("Error: La carga actual (" + numToneladas + ") supera el límite de capacidad (" + limiteToneladas + ").");
                } else {
                    cargaValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido.");
            }
        } while (!cargaValida);

        // 5. Lista de Peces
        List<String> listaPecesTemporales = new ArrayList<>();
        String respuesta;
        System.out.println("--- Registro de Tipos de Pescado ---");
        do {
            System.out.print("Ingrese el tipo de pescado que captura (ej. Atún): ");
            String tipoPez = leer.nextLine();

            if (!tipoPez.trim().isEmpty()) {
                listaPecesTemporales.add(tipoPez);
            }
            System.out.print("¿Desea agregar otro tipo de pescado? (s/n): ");
            respuesta = leer.nextLine();

        } while (respuesta.equalsIgnoreCase("s"));

        Barco nuevoBarco = new BarcoPesquero(matricula, bandera, nombre, peso, fecha, limiteToneladas, numToneladas, listaPecesTemporales);
        this.listaBarcos.add(nuevoBarco);
        System.out.println("Registro exitoso.");
    }

    @Override
    public void editarDatosBarco() {
        System.out.println("\n--- EDICIÓN DE BARCO PESQUERO ---");
        System.out.print("Ingresa la matrícula del barco a editar: ");
        String matricula = leer.nextLine();

        Barco barcoEncontrado = buscarBarcoPorMatricula(matricula);

        if (barcoEncontrado != null && esTipoValido(barcoEncontrado)) {

            BarcoPesquero barcoEditar = (BarcoPesquero) barcoEncontrado;

            System.out.println("Editando barco: " + barcoEditar.getNombre());
            System.out.println("Selecciona el dato a actualizar:");
            System.out.println("1. Nombre");
            System.out.println("2. Bandera");
            System.out.println("3. Peso");
            System.out.println("4. Fecha de Botadura");
            System.out.println("5. Límite de Toneladas");
            System.out.println("6. Carga Actual");
            System.out.println("7. GESTIONAR TIPOS DE PESCADO (Lista)");
            System.out.println("8. Cancelar");
            System.out.print("Opción: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(leer.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida.");
                return; // Importante para no entrar al switch con -1
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
                        double nuevoPeso = Double.parseDouble(leer.nextLine());
                        if (nuevoPeso > 0) {
                            barcoEditar.setPesoToneladas(nuevoPeso);
                            System.out.println("Dato actualizado.");
                        } else {
                            System.out.println("Error: El peso debe ser positivo.");
                        }
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
                case 5 -> { // Validación Límite vs Carga Actual
                    System.out.println("Límite actual: " + barcoEditar.getLimiteToneladas());
                    System.out.println("Carga actual a bordo: " + barcoEditar.getNumToneladas());
                    System.out.print("Nuevo límite: ");
                    try {
                        double nuevoLimite = Double.parseDouble(leer.nextLine());
                        if (nuevoLimite <= 0) {
                            System.out.println("Error: El límite debe ser positivo.");
                        } else if (nuevoLimite < barcoEditar.getNumToneladas()) {
                            System.out.println("Error: No puedes reducir el límite por debajo de la carga actual.");
                        } else {
                            barcoEditar.setLimiteToneladas(nuevoLimite);
                            System.out.println("Dato actualizado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingresa un número válido.");
                    }
                }
                case 6 -> { // Validación Carga vs Límite
                    System.out.println("Carga actual: " + barcoEditar.getNumToneladas());
                    System.out.println("Límite permitido: " + barcoEditar.getLimiteToneladas());
                    System.out.print("Nueva carga actual: ");
                    try {
                        double nuevaCarga = Double.parseDouble(leer.nextLine());
                        if (nuevaCarga < 0) {
                            System.out.println("Error: La carga no puede ser negativa.");
                        } else if (nuevaCarga > barcoEditar.getLimiteToneladas()) {
                            System.out.println("Error: La carga excede el límite de capacidad del barco.");
                        } else {
                            barcoEditar.setNumToneladas(nuevaCarga);
                            System.out.println("Dato actualizado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingresa un número válido.");
                    }
                }
                case 7 -> { // --- SUB-MENÚ PARA LISTA DE PECES ---
                    List<String> pecesActuales = barcoEditar.getTiposPescado();

                    System.out.println("\n--- GESTIÓN DE PECES ---");
                    System.out.println("Lista actual: " + pecesActuales);
                    System.out.println("1. Agregar nuevo tipo");
                    System.out.println("2. Eliminar un tipo existente");
                    System.out.println("3. Limpiar lista completa");
                    System.out.println("4. Volver");
                    System.out.print("Elige una acción: ");

                    try {
                        int subOpcion = Integer.parseInt(leer.nextLine());

                        switch (subOpcion) {
                            case 1 -> {
                                System.out.print("Ingresa el nuevo tipo de pez: ");
                                String nuevoPez = leer.nextLine();
                                if (!nuevoPez.trim().isEmpty()) {
                                    pecesActuales.add(nuevoPez);
                                    barcoEditar.setTiposPescado(pecesActuales);
                                    System.out.println("Pez agregado.");
                                } else {
                                    System.out.println("No se puede agregar un nombre vacío.");
                                }
                            }
                            case 2 -> {
                                System.out.print("Ingresa el nombre del pez a eliminar: ");
                                String pezBorrar = leer.nextLine();
                                if (pecesActuales.remove(pezBorrar)) {
                                    barcoEditar.setTiposPescado(pecesActuales);
                                    System.out.println("Pez eliminado.");
                                } else {
                                    System.out.println("No se encontró ese pez en la lista.");
                                }
                            }
                            case 3 -> {
                                pecesActuales.clear();
                                barcoEditar.setTiposPescado(pecesActuales);
                                System.out.println("Lista de peces vaciada.");
                            }
                            case 4 -> System.out.println("Regresando...");
                            default -> System.out.println("Opción no válida.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingresa un número válido.");
                    }
                }
                case 8 -> System.out.println("Operación cancelada.");
                default -> System.out.println("Opción no válida.");
            }

        } else {
            System.out.println("No se encontró un barco pesquero con esa matrícula.");
        }
    }

    @Override
    protected boolean esTipoValido(Barco b) {
        return b instanceof BarcoPesquero;
    }
    
}
