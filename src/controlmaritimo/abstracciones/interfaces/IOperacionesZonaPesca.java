/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.abstracciones.interfaces;

/**
 * Esta interfaz declara las operaciones generales que tendra una zona de pesca,
 * las operaciones son de registro, eliminación, edición y consulta de datos de
 * una zona de pesca.
 *
 * @version 1.2 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 */
public interface IOperacionesZonaPesca {

    public void registrarZonaPesca();

    public void eliminarZonaPesca();

    public void editarDatosZonaPesca();

    public void consultarDatosZonaPesca();

}
