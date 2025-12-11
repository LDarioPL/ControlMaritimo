/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlmaritimo.abstracciones.interfaces;

/**
 * Esta interfaz contiene los métodos que las clases que establezcan un contrato
 * con esta interfaz deberan implementar para hacer operaciones de carga y
 * descarga de algo perteneciente a una transacción mercantil, ejemplo:
 * cargar/descargar contenedores de un barco de carga.
 *
 * @version 1.2 06/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 */
public interface ICargaDescarga {
    
    // Método para cargar
    public void carga();
    
    // Método para descargar
    public void descarga();

}
