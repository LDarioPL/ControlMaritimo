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

        // 1. Pedimos datos COMUNES (Usamos 'leer' que viene del padre)
        System.out.print("Matrícula: ");
        String matricula = leer.nextLine();
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

        // 2. Datos especificos del barco pesquero
        System.out.print("Límite de capacidad (toneladas): ");
        double limiteToneladas = Double.parseDouble(leer.nextLine());

        System.out.print("Carga actual (toneladas): ");
        double numToneladas = Double.parseDouble(leer.nextLine());

        // 3. Lógica para pedir la lista de peces
        List<String> listaPecesTemporales = new ArrayList<>();
        String respuesta;

        System.out.println("--- Registro de Tipos de Pescado ---");
        do {
            System.out.print("Ingrese el tipo de pescado que captura (ej. Atún): ");
            String tipoPez = leer.nextLine();

            // Validamos que no metan vacíos
            if (!tipoPez.trim().isEmpty()) {
                listaPecesTemporales.add(tipoPez);
            }

            System.out.print("¿Desea agregar otro tipo de pescado? (s/n): ");
            respuesta = leer.nextLine();

        } while (respuesta.equalsIgnoreCase("s")); // Repetir mientras diga "s" o "S"

        // 4. Construimos el objeto pasando la lista llena
        Barco nuevoBarco = new BarcoPesquero(
                matricula,
                bandera,
                nombre,
                peso,
                fecha,
                limiteToneladas,
                numToneladas,
                listaPecesTemporales // Aquí pasamos la lista que acabamos de llenar
        );

        this.listaBarcos.add(nuevoBarco);
        System.out.println("Registro exitoso");
    }

    @Override
    public void editarDatosBarco() {
        
        System.out.println("\n--- EDICIÓN DE BARCO PESQUERO ---");
        System.out.print("Ingresa la matrícula del barco a editar: ");
        String matricula = leer.nextLine();

        Barco barcoEncontrado = buscarBarcoPorMatricula(matricula);

        if (barcoEncontrado != null && esTipoValido(barcoEncontrado)) {
             
             // CASTING
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
             } catch(NumberFormatException e) {
                 System.out.println("Opción no válida.");
                 return;
             }
             
             switch(opcion) {
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
                     } catch(NumberFormatException e){ System.out.println("Error: Ingrese un número."); }
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
                     System.out.println("Límite actual: " + barcoEditar.getLimiteToneladas());
                     System.out.print("Nuevo límite: ");
                     try {
                        barcoEditar.setLimiteToneladas(Double.parseDouble(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                     } catch(NumberFormatException e){ System.out.println("Error: Ingrese un número."); }
                 }
                 case 6 -> {
                     System.out.println("Carga actual: " + barcoEditar.getNumToneladas());
                     System.out.print("Nueva carga actual: ");
                     try {
                        barcoEditar.setNumToneladas(Double.parseDouble(leer.nextLine()));
                        System.out.println("Dato actualizado.");
                     } catch(NumberFormatException e){ System.out.println("Error: Ingrese un número."); }
                 }
                 case 7 -> { // --- SUB-MENÚ PARA LISTA DE PECES ---
                     // Obtenemos la lista actual (NOTA: getTiposPescado devuelve una COPIA en tu modelo, así que hay que tener cuidado)
                     List<String> pecesActuales = barcoEditar.getTiposPescado();
                     
                     System.out.println("\n--- GESTIÓN DE PECES ---");
                     System.out.println("Lista actual: " + pecesActuales);
                     System.out.println("1. Agregar nuevo tipo");
                     System.out.println("2. Eliminar un tipo existente");
                     System.out.println("3. Limpiar lista completa");
                     System.out.println("4. Volver");
                     System.out.print("Elige una acción: ");
                     
                     int subOpcion = Integer.parseInt(leer.nextLine());
                     
                     switch(subOpcion) {
                         case 1 -> {
                             System.out.print("Ingresa el nuevo tipo de pez: ");
                             String nuevoPez = leer.nextLine();
                             pecesActuales.add(nuevoPez);
                             barcoEditar.setTiposPescado(pecesActuales); // Guardamos la lista modificada
                             System.out.println("Pez agregado.");
                         }
                         case 2 -> {
                             System.out.print("Ingresa el nombre del pez a eliminar: ");
                             String pezBorrar = leer.nextLine();
                             if(pecesActuales.remove(pezBorrar)) { // remove retorna true si lo borró
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
                         default -> System.out.println("Regresando...");
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
