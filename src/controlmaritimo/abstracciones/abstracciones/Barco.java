/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlmaritimo.abstracciones.abstracciones;

import controlmaritimo.modelos.documentos.DocumentoAtraque;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase abstracta que contiene todos los atributos comunes de un barco,
 * contiene un constructor sobrecargado y todos los metodos getters y setters.
 *
 * @version 4.0 08/12/2025
 *
 * @author Nicolás Yazid Cruz Hernández
 * @author Emilio Álvarez Villalobos
 * @author Luis Darío Padilla López
 * @author Isaac Adriano Vázquez Torres
 */
public abstract class Barco {

    protected String matricula;
    protected String bandera;
    protected String nombre;
    protected Double pesoToneladas;
    protected LocalDate fechaBotadura;
    protected List<DocumentoAtraque> atraques;

    public Barco(String matricula, String bandera, String nombre,
            Double pesoToneladas, LocalDate fechaBotadura) {

        this.matricula = matricula;
        this.bandera = bandera;
        this.nombre = nombre;
        this.pesoToneladas = pesoToneladas;
        this.fechaBotadura = fechaBotadura;
        this.atraques = new ArrayList<>();
    }
    
    // Métodos getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPesoToneladas() {
        return pesoToneladas;
    }

    public void setPesoToneladas(Double pesoToneladas) {
        this.pesoToneladas = pesoToneladas;
    }

    public LocalDate getFechaBotadura() {
        return fechaBotadura;
    }

    public void setFechaBotadura(LocalDate fechaBotadura) {
        this.fechaBotadura = fechaBotadura;
    }

    public List<DocumentoAtraque> getAtraques() {
        return atraques;
    }
    
    /**
     * Método que muestra la información de un barco (todos sus atributos).
     * 
     * @return String que contiene la información de un barco.
     */
    public String obtenerInformacionBarco() {
        return String.format("""
                             Matrícula: %s
                             Nombre: %s
                             Bandera: %s
                             Peso: %.2f toneladas
                             Fecha de botadura: %s
                             Atraques registrados: %d
                             Tipo: %s""",
                matricula, nombre, bandera, pesoToneladas,
                fechaBotadura, atraques.size(), getTipoBarco()
        );
    }
    
    /**
     * Método abstracto para obtener el tipo de barco según la clase que herede
     * de esta.
     * 
     * @return String que índica el tipo de barco.
     */
    public abstract String getTipoBarco();

}
