/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.logica;

import controlmaritimo.abstracciones.interfaces.IOrquestadorMenus;

/**
 *
 * @author yazid
 */
public class OrquestadorMenus implements IOrquestadorMenus {

    @Override
    public void mostrarMenuPrincipal() {
        System.out.println("Administración general");
        System.out.println("-------------------------------");
        System.out.println("1. Administrar barcos");
        System.out.println("2. Administrar zonas de pesca");
        System.out.println("3. Administrar puertos");
        System.out.println("4. Administrar movimientos");
        System.out.println("5. Salir de la aplicación");
        System.out.println("-------------------------------");
        System.out.print("> Ingresa una opción: ");
    }

    @Override
    public void mostrarMenuTipoBarco() {
        System.out.println("Elige un tipo de barco:");
        System.out.println("-----------------------");
        System.out.println("1. Barco de pasajeros");
        System.out.println("2. Barco pesquero");
        System.out.println("3. Barco de carga");
        System.out.println("-----------------------");
        System.out.print("> ");
    }

    @Override
    public void mostrarMenuOperacionesBarco() {
        System.out.println("Opciones de administración para los barcos");
        System.out.println("------------------------------------------");
        System.out.println("1. Registrar un barco");
        System.out.println("2. Editar un barco");
        System.out.println("3. Eliminar un barco");
        System.out.println("4. Consultar un barco");
        System.out.println("5. Cancelar operación");
        System.out.println("------------------------------------------");
        System.out.print("> Ingresa una opción: ");
    }

    @Override
    public void mostrarMenuZonaPesca() {
        System.out.println("Opciones de administración para las zonas de pesca");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Registrar una nueva zona de pesca");
        System.out.println("2. Editar los datos de una zona de pesca");
        System.out.println("3. Eliminar una zona de pesca");
        System.out.println("4. Consultar los datos de una zona de pesca");
        System.out.println("5. Cancelar operación");
        System.out.println("----------------------------------------------------");
        System.out.print("> Ingresa una opción: ");
    }

    @Override
    public void mostrarMenuPuerto() {
        System.out.println("Opciones de administración para las zonas portuarias");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Registrar una nueva zona portuaria");
        System.out.println("2. Editar los datos de una zona portuaria");
        System.out.println("3. Eliminar una zona portuaria");
        System.out.println("4. Consultar los datos de una zona portuaria");
        System.out.println("5. Cancelar operación");
        System.out.println("----------------------------------------------------");
        System.out.print("> Ingresa una opción: ");
    }

    @Override
    public void mostrarMenuDocumentos() {
        System.out.println("Documentos de Control Maritimo");
        System.out.println("--------------------------------------");
        System.out.println("1. Conocimiento de embarque");
        System.out.println("2. Documento de atraque");
        System.out.println("3. Documento de transaccion mercantil");
        System.out.println("--------------------------------------");
        System.out.println("> Ingresa una opción: ");
    }

    @Override
    public void mostrarMenuOperacionesDocumentos() {
        System.out.println("Operaciones de documentación");
        System.out.println("-----------------------------");
        System.out.println("1. Crear un documento");
        System.out.println("2. Editar un documento");
        System.out.println("3. Eliminar un documento");
        System.out.println("4. Consultar un documento");
        System.out.println("5. Cancelar operación");
        System.out.println("-----------------------------");
        System.out.println("> Ingresa una opción: ");
    }

    @Override
    public void mostrarMenuTransaccion() {
        System.out.println("Administración...");
    }

}
