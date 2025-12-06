/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.logica;

import controlmaritimo.abstracciones.IOrquestadorMenus;
import java.util.Scanner;

/**
 *
 * @author yazid
 */
public class Orquestador {
    
    public void ejecutar() {
        int opcion;
        IOrquestadorMenus menu = new OrquestadorMenus();
        Scanner leer = new Scanner(System.in);

        do {
            menu.mostrarMenuPrincipal();
            opcion = leer.nextInt();
            switch (opcion) {
                case 1 -> {     // Operaciones de administración de un barco
                    menu.mostrarMenuBarcos();
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1 -> {
                            
                        }
                        case 2 -> {
                            
                        }
                        case 3 -> {
                            
                        }
                        case 4 -> {
                            
                        }
                        case 5 -> {
                            System.out.println("Saliendo de la aplicación...");
                        }
                        default -> {
                            System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                        }
                    }
                }
                case 2 -> {     // Operaciones de administración de una zona de pesca
                    menu.mostrarMenuZonaPesca();
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1 -> {
                            
                        }
                        case 2 -> {
                            
                        }
                        case 3 -> {
                            
                        }
                        case 4 -> {
                            
                        }
                        case 5 -> {
                            System.out.println("Saliendo de la aplicación...");
                        }
                        default -> {
                            System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                        }
                    }
                }
                case 3 -> {     // Operaciones de administración de un puerto
                    menu.mostrarMenuPuerto();
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1 -> {
                            
                        }
                        case 2 -> {
                            
                        }
                        case 3 -> {
                            
                        }
                        case 4 -> {
                            
                        }
                        case 5 -> {
                            System.out.println("Saliendo de la aplicación...");
                        }
                        default -> {
                            System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                        }
                    }
                }
                case 4 -> {     // Operaciones de administración de una transacción
                    menu.mostrarMenuTransaccion();
                    opcion = leer.nextInt();
                    switch (opcion) {
                        case 1 -> {
                            
                        }
                        case 2 -> {
                            
                        }
                        case 3 -> {
                            
                        }
                        case 4 -> {
                            
                        }
                        case 5 -> {
                            System.out.println("Saliendo de la aplicación...");
                        }
                        default -> {
                            System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Saliendo de la aplicación...");
                }
                default -> {
                    System.out.println("Esta opción no esta disponible. Intantelo nuevamente.");
                }
            }
        } while (opcion != 5);
    }
    
}
