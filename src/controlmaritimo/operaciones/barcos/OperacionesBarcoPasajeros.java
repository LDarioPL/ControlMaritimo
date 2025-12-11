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
        
        // 1. Validar Matrícula (Ciclo para asegurar unicidad y no vacíos)
        String matricula;
        do {
            System.out.print("Matrícula: ");
            matricula = leer.nextLine().trim();
            if (matricula.isEmpty()) {
                System.out.println("La matrícula no puede estar vacía.");
            } else if (buscarBarcoPorMatricula(matricula) != null) {
                System.out.println("Error: Ya existe un barco con esa matrícula. Intenta con otra.");
                matricula = ""; // Forzamos repetir
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

        // 3. Validar Límite de Pasajeros (> 0)
        int limite = -1;
        do {
            try {
                System.out.print("Límite Pasajeros: ");
                limite = Integer.parseInt(leer.nextLine());
                if (limite <= 0) System.out.println("El límite debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        } while (limite <= 0);
        
        // 4. Validar Cantidad Actual (>= 0 y <= limite)
        int cantidad = -1;
        boolean cantidadValida = false;
        do {
            try {
                System.out.print("Cantidad Actual de Pasajeros: ");
                cantidad = Integer.parseInt(leer.nextLine());
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa.");
                } else if (cantidad > limite) {
                    System.out.println("Error: La cantidad (" + cantidad + ") supera el límite de pasajeros (" + limite + ").");
                } else {
                    cantidadValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        } while (!cantidadValida);
        
        // 5. Validar Pisos (> 0)
        int pisos = -1;
        do {
            try {
                System.out.print("Pisos: ");
                pisos = Integer.parseInt(leer.nextLine());
                if (pisos <= 0) System.out.println("Debe tener al menos 1 piso.");
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        } while (pisos <= 0);

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

        Barco barcoEncontrado = buscarBarcoPorMatricula(matricula);

        if (barcoEncontrado != null && esTipoValido(barcoEncontrado)) {

            BarcoPasajeros barcoEditar = (BarcoPasajeros) barcoEncontrado;

            System.out.println("Editando barco: " + barcoEditar.getNombre());
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
                case 5 -> { // Validación Límite vs Cantidad Actual
                    System.out.println("Límite actual: " + barcoEditar.getLimitePasajeros());
                    System.out.println("Pasajeros a bordo: " + barcoEditar.getCantidadPasajeros());
                    System.out.print("Nuevo límite: ");
                    try {
                        int nuevoLimite = Integer.parseInt(leer.nextLine());
                        if (nuevoLimite <= 0) {
                            System.out.println("Error: El límite debe ser positivo.");
                        } else if (nuevoLimite < barcoEditar.getCantidadPasajeros()) {
                            System.out.println("Error: No puedes reducir el límite por debajo de la cantidad actual de pasajeros.");
                        } else {
                            barcoEditar.setLimitePasajeros(nuevoLimite);
                            System.out.println("Dato actualizado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 6 -> { // Validación Cantidad vs Límite
                    System.out.println("Cantidad actual: " + barcoEditar.getCantidadPasajeros());
                    System.out.println("Límite permitido: " + barcoEditar.getLimitePasajeros());
                    System.out.print("Nueva cantidad: ");
                    try {
                        int nuevaCantidad = Integer.parseInt(leer.nextLine());
                        if (nuevaCantidad < 0) {
                            System.out.println("Error: La cantidad no puede ser negativa.");
                        } else if (nuevaCantidad > barcoEditar.getLimitePasajeros()) {
                            System.out.println("Error: La cantidad excede el límite de pasajeros del barco.");
                        } else {
                            barcoEditar.setCantidadPasajeros(nuevaCantidad);
                            System.out.println("Dato actualizado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error al ingresar número.");
                    }
                }
                case 7 -> {
                    System.out.println("Pisos actuales: " + barcoEditar.getNumeroPisos());
                    System.out.print("Nuevos pisos: ");
                    try {
                        int nuevosPisos = Integer.parseInt(leer.nextLine());
                        if (nuevosPisos > 0) {
                            barcoEditar.setNumeroPisos(nuevosPisos);
                            System.out.println("Dato actualizado.");
                        } else {
                            System.out.println("Error: Debe tener al menos 1 piso.");
                        }
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
                case 9 -> System.out.println("Operación cancelada.");
                default -> System.out.println("Opción no válida.");
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
