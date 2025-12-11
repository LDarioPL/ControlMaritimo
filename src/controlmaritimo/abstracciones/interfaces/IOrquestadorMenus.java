/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.abstracciones.interfaces;

/**
 * Esta interfaz declara todos los metodos que deberá implementar la clase
 * OrquestadorMenus para disponer de todos los menus que requerirá la clase
 * Orquestador para poder mostrar al usuario las opciones disponibles de la
 * aplicación.
 *
 * @version 1.5 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 */
public interface IOrquestadorMenus {

    public void mostrarMenuPrincipal();

    public void mostrarMenuTipoBarco();

    public void mostrarMenuOperacionesBarco();

    public void mostrarMenuZonaPesca();

    public void mostrarMenuPuerto();

    public void mostrarMenuDocumentos();

    public void mostrarMenuOperacionesDocumentos();

    public void mostrarMenuTransaccion();

}
