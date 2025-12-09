/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.lugares;

import controlmaritimo.abstracciones.interfaces.IOperacionesZonaPesca;
import controlmaritimo.modelos.lugares.ZonaPesca;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author yazid
 */
public class OperacionesZonaPesca implements IOperacionesZonaPesca {
    
    private List<ZonaPesca> listaZonas;
    private Scanner leer;

    public OperacionesZonaPesca(List<ZonaPesca> listaZonas) {
        this.listaZonas = listaZonas;
        this.leer = new Scanner(System.in);
    }

    @Override
    public void registrarZonaPesca() {
        System.out.println("\n--- REGISTRO DE ZONA DE PESCA ---");

        System.out.print("Clave de Geolocalización (ID único): ");
        String clave = leer.nextLine().trim();

        // 1. Validar duplicados
        if (buscarZonaPorClave(clave) != null) {
            System.out.println("Error: Ya existe una zona con la clave: " + clave);
            return;
        }

        // 2. Pedir coordenadas y radio con validación de tipo
        double latitud = 0, longitud = 0, radio = 0;
        try {
            System.out.print("Latitud: ");
            latitud = Double.parseDouble(leer.nextLine());

            System.out.print("Longitud: ");
            longitud = Double.parseDouble(leer.nextLine());

            System.out.print("Radio (km): ");
            radio = Double.parseDouble(leer.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar valores numéricos válidos.");
            return;
        }

        // 3. Pedir lista de especies (Ciclo similar al Barco Pesquero)
        List<String> especiesTemporales = new ArrayList<>();
        String respuesta;

        System.out.println("--- Registro de Especies en la Zona ---");
        do {
            System.out.print("Ingrese especie presente (ej. Sardina): ");
            String especie = leer.nextLine();

            if (!especie.trim().isEmpty()) {
                especiesTemporales.add(especie);
            }

            System.out.print("¿Agregar otra especie? (s/n): ");
            respuesta = leer.nextLine();
        } while (respuesta.equalsIgnoreCase("s"));

        // 4. Crear y guardar
        ZonaPesca nuevaZona = new ZonaPesca(clave, latitud, longitud, radio, especiesTemporales);
        listaZonas.add(nuevaZona);
        System.out.println("Zona de pesca registrada exitosamente.");
    }

    @Override
    public void eliminarZonaPesca() {
        System.out.println("\n--- ELIMINAR ZONA DE PESCA ---");
        System.out.print("Ingresa la clave de la zona a eliminar: ");
        String clave = leer.nextLine();

        ZonaPesca zona = buscarZonaPorClave(clave);

        if (zona != null) {
            listaZonas.remove(zona);
            System.out.println("Zona eliminada correctamente.");
        } else {
            System.out.println("No se encontró ninguna zona con esa clave.");
        }
    }

    @Override
    public void editarDatosZonaPesca() {
        System.out.println("\n--- EDITAR ZONA DE PESCA ---");
        System.out.print("Ingresa la clave de la zona a editar: ");
        String clave = leer.nextLine();

        ZonaPesca zona = buscarZonaPorClave(clave);

        if (zona != null) {
            System.out.println("Editando zona: " + zona.getClaveGeolocalizacion());
            System.out.println("1. Latitud");
            System.out.println("2. Longitud");
            System.out.println("3. Radio (km)");
            System.out.println("4. Gestionar Especies (Lista)");
            System.out.println("5. Cancelar");
            System.out.print("Opción: ");

            try {
                int opcion = Integer.parseInt(leer.nextLine());

                switch (opcion) {
                    case 1 -> {
                        System.out.println("Latitud actual: " + zona.getLatitud());
                        System.out.print("Nueva latitud: ");
                        zona.setLatitud(Double.parseDouble(leer.nextLine()));
                        System.out.println("Actualizado.");
                    }
                    case 2 -> {
                        System.out.println("Longitud actual: " + zona.getLongitud());
                        System.out.print("Nueva longitud: ");
                        zona.setLongitud(Double.parseDouble(leer.nextLine()));
                        System.out.println("Actualizado.");
                    }
                    case 3 -> {
                        System.out.println("Radio actual: " + zona.getRadioKm());
                        System.out.print("Nuevo radio: ");
                        zona.setRadioKm(Double.parseDouble(leer.nextLine()));
                        System.out.println("Actualizado.");
                    }
                    case 4 ->
                        gestionarEspecies(zona); // Método auxiliar para no llenar este switch
                    case 5 ->
                        System.out.println("Cancelado.");
                    default ->
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un valor numérico válido.");
            }
        } else {
            System.out.println("No se encontró la zona.");
        }
    }

    @Override
    public void consultarDatosZonaPesca() {
        System.out.println("\n--- CONSULTA DE ZONAS DE PESCA ---");
        if (listaZonas.isEmpty()) {
            System.out.println("No hay zonas registradas.");
            return;
        }

        System.out.println("1. Ver todas las zonas");
        System.out.println("2. Buscar por Clave");

        try {
            int opcion = Integer.parseInt(leer.nextLine());
            if (opcion == 1) {
                for (ZonaPesca z : listaZonas) {
                    System.out.println("-------------------------");
                    System.out.println(z.obtenerInfo());
                }
                System.out.println("-------------------------");
            } else if (opcion == 2) {
                System.out.print("Ingresa la clave a buscar: ");
                String clave = leer.nextLine();
                ZonaPesca z = buscarZonaPorClave(clave);
                if (z != null) {
                    System.out.println("-------------------------");
                    System.out.println(z.obtenerInfo());
                    System.out.println("-------------------------");
                } else {
                    System.out.println("No se encontró la zona.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida.");
        }
    }

    // Busca una zona por su clave única
    private ZonaPesca buscarZonaPorClave(String clave) {
        for (ZonaPesca z : listaZonas) {
            if (z.getClaveGeolocalizacion().equalsIgnoreCase(clave)) {
                return z;
            }
        }
        return null;
    }

    private void gestionarEspecies(ZonaPesca zona) {
        List<String> especies = zona.getEspeciesPeces(); // Obtenemos copia

        System.out.println("\n--- GESTIÓN DE ESPECIES ---");
        System.out.println("Especies actuales: " + especies);
        System.out.println("1. Agregar especie");
        System.out.println("2. Eliminar especie");
        System.out.println("3. Limpiar todo");
        System.out.print("Opción: ");

        try {
            int subOp = Integer.parseInt(leer.nextLine());
            switch (subOp) {
                case 1 -> {
                    System.out.print("Especie a agregar: ");
                    especies.add(leer.nextLine());
                    zona.setEspeciesPeces(especies); // Guardamos cambios
                    System.out.println("Agregado.");
                }
                case 2 -> {
                    System.out.print("Especie a eliminar: ");
                    String borrar = leer.nextLine();
                    if (especies.remove(borrar)) {
                        zona.setEspeciesPeces(especies); // Guardamos cambios
                        System.out.println("Eliminado.");
                    } else {
                        System.out.println("No existía esa especie.");
                    }
                }
                case 3 -> {
                    especies.clear();
                    zona.setEspeciesPeces(especies);
                    System.out.println("Lista vaciada.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de entrada.");
        }
    }
    
}
