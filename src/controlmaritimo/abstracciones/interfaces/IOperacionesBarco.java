/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.abstracciones.interfaces;

/**
 * Esta interfaz contiene todas las operaciones basicas para cualquier tipo de
 * barco: registro, eliminación, edición y consulta de datos.
 * 
 * @version 1.2 06/12/2025
 * 
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 */
public interface IOperacionesBarco {
    
    public void registrarBarco();
    
    public void eliminarBarco();

    public void editarDatosBarco();

    public void consultarDatosBarco();

}
