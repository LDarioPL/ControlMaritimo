/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.abstracciones.interfaces;

/**
 * Esta interfaz declara las operaciones que se pueden aplicar a cualquier tipo
 * de documento en el contexto del control maritimo, por ejemplo: Documento de
 * Conocimiento de Embarque, Documento de atraque, Documento de una Transacción
 * Mercantil, etc.
 *
 * @version 1.0 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 *
 */
public interface IOperacionesDocumentos {

    public void crearDocumento();

    public void editarDocumento();

    public void eliminarDocumento();

    public void consultarDocumento();

}
