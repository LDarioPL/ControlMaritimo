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
import controlmaritimo.modelos.documentos.DocumentoAtraque;
import controlmaritimo.modelos.documentos.TransaccionMercantil;
import controlmaritimo.modelos.lugares.Puerto;
import controlmaritimo.modelos.lugares.ZonaPesca;
import controlmaritimo.operaciones.barcos.OperacionesBarcoCarga;
import controlmaritimo.operaciones.barcos.OperacionesBarcoPasajeros;
import controlmaritimo.operaciones.barcos.OperacionesBarcoPesquero;
import controlmaritimo.operaciones.documentos.OperacionesConocimientoEmbarque;
import controlmaritimo.operaciones.documentos.OperacionesDocumentoAtraque;
import controlmaritimo.operaciones.documentos.OperacionesTransaccionMercantil;
import controlmaritimo.operaciones.lugares.OperacionesPuerto;
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
 *
 * @version 2.7 10/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Isaac Ádriano Vazquez Torres
 * @author Luis Darío Padilla Lopez
 */
public class Orquestador {

    /**
     * Listas de objetos que utilizaremos.
     */
    private List<Barco> barcos;
    private List<ZonaPesca> zonasPesca;
    private List<Puerto> puertos;
    private List<DocumentoAtraque> documentosAtraque;
    private List<TransaccionMercantil> transaccionesMercantiles;
    private List<ConocimientoEmbarque> conocimientosEmbarque;
    private Scanner leer;
    private SimpleDateFormat sdf;

    /**
     * Inicializaremos en el constructor todos los elementos de las demás clases
     * que necesitaremos.
     */
    public Orquestador() {
        barcos = new ArrayList<>();
        zonasPesca = new ArrayList<>();
        puertos = new ArrayList<>();
        documentosAtraque = new ArrayList<>();
        transaccionesMercantiles = new ArrayList<>();
        conocimientosEmbarque = new ArrayList<>();
        leer = new Scanner(System.in);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * Único método de esta clase, se encarga de hacer funcionar el código
     * mediante la utilización de interfaces para acceder a las clases de
     * operaciones de cada clase perteneciente al paquete de modelo y de este
     * modo hacer funcionar el código. Esta clase se encarga de la lógica del
     * programa mediante la creación de un menu que orquesta todas las llamadas
     * a otras clases.
     */
    public void ejecutar() {
        int opcionGeneral;
        int opcionOperaciones;
        int opcionTipoBarco;
        int opcionTipoDocumento;

        /**
         * Interfaces y clase abstracta que utilizaremos para hacer funcionar
         * las operaciones de cada modulo.
         */
        IOrquestadorMenus menu = new OrquestadorMenus();
        IOperacionesZonaPesca operacionesZonaPesca = new OperacionesZonaPesca(this.zonasPesca);
        OperacionesPuerto operacionesPuerto = new OperacionesPuerto(this.puertos);

        /**
         * Ciclo principal que hace sirve al menu principal de la aplicación.
         */
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
                                operacionesPuerto.registrarPuerto();
                            }
                            case 2 -> {
                                operacionesPuerto.editarDatosPuerto();
                            }
                            case 3 -> {
                                operacionesPuerto.eliminarPuerto();
                            }
                            case 4 -> {
                                operacionesPuerto.consultarDatosPuerto();
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
                                operaciones = new OperacionesDocumentoAtraque(this.documentosAtraque, this.barcos, this.puertos);
                                banderaTipoDocumento = false;
                            }
                            case 3 -> {
                                operaciones = new OperacionesTransaccionMercantil(this.transaccionesMercantiles, this.documentosAtraque);
                                banderaTipoDocumento = false;
                            }
                            default -> {
                                System.out.println("Error. Tipo de documento no disponible.");
                            }
                        }

                    } while (banderaTipoDocumento == true);
                    
                    /**
                     * En un caso hipotetico, si uno de los casos del switch de
                     * arriba no crease un objeto entonces la interfaz quedaria
                     * con el valor de null y por lo tanto no se ejecutaria
                     * ninguna de las siguientes instrucciones.
                     */
                    if (operaciones != null) {
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
