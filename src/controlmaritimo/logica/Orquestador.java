/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.logica;

import controlmaritimo.abstracciones.abstracciones.Barco;
import controlmaritimo.abstracciones.abstracciones.OperacionesComunesBarco;
import controlmaritimo.abstracciones.interfaces.IOperacionesDocumentos;
import controlmaritimo.abstracciones.interfaces.IOperacionesZonaPesca;
import controlmaritimo.abstracciones.interfaces.IOrquestadorMenus;
import controlmaritimo.modelos.documentos.ConocimientoEmbarque;
import controlmaritimo.modelos.lugares.ZonaPesca;
import controlmaritimo.operaciones.barcos.OperacionesBarcoCarga;
import controlmaritimo.operaciones.barcos.OperacionesBarcoPasajeros;
import controlmaritimo.operaciones.barcos.OperacionesBarcoPesquero;
import controlmaritimo.operaciones.documentos.OperacionesConocimientoEmbarque;
import controlmaritimo.operaciones.lugares.OperacionesZonaPesca;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Clase que orquesta a todas las clases, se encarga de llamar a todos los
 * metodos necesarios para hacer funcionar el programa, esta clase contiene el
 * menu principal de ejecución.
 *
 * @version 2.0, 06/12/2025
 * @author yazid
 * @author
 * @author
 * @author
 *
 */
public class Orquestador {
    
    private List<Barco> barcos;
    private List<ZonaPesca> zonasPesca;
    private List<ConocimientoEmbarque> conocimientosEmbarque;
    private Scanner leer;
    private SimpleDateFormat sdf;

    public Orquestador() {
        barcos = new ArrayList<>();
        zonasPesca = new ArrayList<>();
        conocimientosEmbarque = new ArrayList<>();
        leer = new Scanner(System.in);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void ejecutar() {
        int opcionGeneral;
        int opcionOperaciones;
        int opcionTipoBarco;
        int opcionTipoDocumento;

        IOrquestadorMenus menu = new OrquestadorMenus();
        IOperacionesZonaPesca operacionesZonaPesca = new OperacionesZonaPesca(this.zonasPesca);

        do {
            menu.mostrarMenuPrincipal();
            opcionGeneral = leer.nextInt();
            leer.nextLine();

            //--- MENU PRINCIPAL DE LA APLICACIÓN ---
            switch (opcionGeneral) {
                case 1 -> {     // Operaciones de administración de un barco
                    boolean banderaTipoBarco = true;
                    OperacionesComunesBarco operaciones = null;

                    do {
                        //--- MENU DE LOS TIPO DE BARCO EXISTENTES ---
                        menu.mostrarMenuTipoBarco();
                        opcionTipoBarco = leer.nextInt();
                        leer.nextLine();

                        // -- Tipo de operación que se creará segun el tipo de barco seleccionado --
                        switch (opcionTipoBarco) {
                            case 1 -> {
                                operaciones = new OperacionesBarcoPasajeros(this.barcos);
                                banderaTipoBarco = false;
                            }
                            case 2 -> {
                                operaciones = new OperacionesBarcoPesquero(this.barcos);
                                banderaTipoBarco = false;
                            }
                            case 3 -> {
                                operaciones = new OperacionesBarcoCarga(this.barcos);
                                banderaTipoBarco = false;
                            }
                            default -> {
                                System.out.println("Error. Tipo de barco no disponible.");
                            }
                        }
                    } while (banderaTipoBarco == true);

                    //--- MENU DE LAS OPERACIONES DE UN BARCO ---
                    do {
                        menu.mostrarMenuOperacionesBarco();
                        opcionOperaciones = leer.nextInt();
                        leer.nextLine();

                        //-- Operaciones disponibles para los barcos --
                        switch (opcionOperaciones) {
                            case 1 -> {
                                operaciones.registrarBarco();
                            }
                            case 2 -> {
                                operaciones.editarDatosBarco();
                            }
                            case 3 -> {
                                operaciones.eliminarBarco();
                            }
                            case 4 -> {
                                operaciones.consultarDatosBarco();
                            }
                            case 5 -> {
                                System.out.println("Regresando...");
                            }
                            default -> {
                                System.out.println("Esta opción no esta disponible. Intentelo nuevamente.");
                            }
                        }
                    } while (opcionOperaciones != 5);
                }
                case 2 -> {     // Operaciones de administración de una zona de pesca
                    do {

                        //--- MENU DE OPERACIONES DE LAS ZONAS DE PESCA ---
                        menu.mostrarMenuZonaPesca();
                        opcionOperaciones = leer.nextInt();
                        leer.nextLine();

                        switch (opcionOperaciones) {
                            case 1 -> {
                                operacionesZonaPesca.registrarZonaPesca();
                            }
                            case 2 -> {
                                operacionesZonaPesca.editarDatosZonaPesca();
                            }
                            case 3 -> {
                                operacionesZonaPesca.eliminarZonaPesca();
                            }
                            case 4 -> {
                                operacionesZonaPesca.consultarDatosZonaPesca();
                            }
                            case 5 -> {
                                System.out.println("Regresando...");
                            }
                            default -> {
                                System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                            }
                        }
                    } while (opcionOperaciones != 5);
                }
                case 3 -> {     // Operaciones de administración de un puerto
                    do {

                        //--- MENU DE OPERACIONES DEL PUERTO ---
                        menu.mostrarMenuPuerto();
                        opcionOperaciones = leer.nextInt();
                        leer.nextLine();

                        switch (opcionOperaciones) {
                            case 1 -> {
                                System.out.println("En desarrollo...");
                            }
                            case 2 -> {
                                System.out.println("En desarrollo...");
                            }
                            case 3 -> {
                                System.out.println("En desarrollo...");
                            }
                            case 4 -> {
                                System.out.println("En desarrollo...");
                            }
                            case 5 -> {
                                System.out.println("Regresando...");
                            }
                            default -> {
                                System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                            }
                        }
                    } while (opcionOperaciones != 5);
                }
                case 4 -> {     // Operaciones de administración de un movimiento maritimo/administrativo
                    boolean banderaTipoDocumento = true;
                    IOperacionesDocumentos operaciones = null;

                    do {

                        //--- MENU DE MOVIMIENTOS ---
                        menu.mostrarMenuDocumentos();
                        opcionTipoDocumento = leer.nextInt();
                        leer.nextLine();

                        switch (opcionTipoDocumento) {
                            case 1 -> {
                                operaciones = new OperacionesConocimientoEmbarque(this.conocimientosEmbarque, this.barcos, this.zonasPesca);
                                banderaTipoDocumento = false;
                            }
                            case 2 -> {
                                System.out.println("En desarrollo... documento no disponible.");
                                banderaTipoDocumento = false;
                            }
                            case 3 -> {
                                System.out.println("En desarrollo... documento no disponible.");
                                banderaTipoDocumento = false;
                            }
                            default -> {
                                System.out.println("Error. Tipo de documento no disponible.");
                            }
                        }

                    } while (banderaTipoDocumento == true);
                    
                    if (operaciones != null) { //Talón de Aquiles
                        do {
                            menu.mostrarMenuOperacionesDocumentos();
                            opcionOperaciones = leer.nextInt();
                            leer.nextLine();

                            switch (opcionOperaciones) {
                                case 1 -> {
                                    operaciones.crearDocumento();
                                }
                                case 2 -> {
                                    operaciones.editarDocumento();
                                }
                                case 3 -> {
                                    operaciones.eliminarDocumento();
                                }
                                case 4 -> {
                                    operaciones.consultarDocumento();
                                }
                                case 5 -> {
                                    System.out.println("Regresando...");
                                }
                                default -> {
                                    System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                                }
                            }
                        } while (opcionOperaciones != 5);
                    }
                }
                case 5 -> {
                    System.out.println("Saliendo de la aplicación...");
                }
                default -> {
                    System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                }
            }
        } while (opcionGeneral != 5);
    }
    
}
