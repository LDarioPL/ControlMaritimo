/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.operaciones.barco;

import controlmaritimo.abstracciones.Barco;
import controlmaritimo.abstracciones.IOperacionesBarco;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author yazid
 */
public class OperacionesBarcoCarga extends Barco implements IOperacionesBarco {
    
    Scanner leer = new Scanner(System.in);
    
    public OperacionesBarcoCarga(String matricula, String bandera, String nombre, double pesoToneladas, LocalDate fechaBotadura) {
        super(matricula, bandera, nombre, pesoToneladas, fechaBotadura);
    }

    @Override
    public void registrarBarco() {
        System.out.println("Ingresa el nombre del barco: ");
        super.nombre = leer.nextLine();
        
        System.out.println("Ingresa la matricula única del barco: ");
        super.matricula = leer.nextLine();
        
        System.out.println("Ingresa la bandera del barco: ");
        super.bandera = leer.nextLine();
        
        System.out.println("Ingresa el peso del barco en toneladas: ");
        super.pesoToneladas = leer.nextDouble();
        
        System.out.println("Ingresa la fecha de botadura: ");
        
    }

    @Override
    public void eliminarBarco() {
        
    }

    @Override
    public void editarDatosBarco() {
        
    }

    @Override
    public void consultarDatosBarco() {
        
    }
    
    @Override
    public void carga() {
        
    }
    
    @Override
    public void descarga() {
        
    }
    
    @Override
    public String getTipoBarco() {
        return "Barco de carga";
    }
    
}
