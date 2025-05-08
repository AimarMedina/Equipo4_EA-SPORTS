package Modelo;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase Jornada que representa una jornada con un número, fechas de inicio y fin,
 * y una lista de enfrentamientos asociados.
 */
public class Jornada {
    private int numJornada;  // Número de la jornada
    private LocalDate fechaInicio;// Fecha de inicio de la jornada
    private LocalDate fechaFin;
    private List<Enfrentamiento> enfrentamientos;  // Lista de enfrentamientos en la jornada


    /**
     * Constructor que inicializa una jornada con todos los datos proporcionados.
     *
     * @param numJornada Número de la jornada.
     * @param fechaInicio Fecha de inicio de la jornada.
     * @param fechaFin Fecha de fin de la jornada.
     * @param enfrentamientos Lista de enfrentamientos en la jornada.
     */
    public Jornada(int numJornada, LocalDate fechaInicio,LocalDate fechaFin, List<Enfrentamiento> enfrentamientos) {
        this.numJornada = numJornada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.enfrentamientos = enfrentamientos;
    }
    /**
     * Constructor que inicializa una jornada con el número y las fechas,
     * pero sin enfrentamientos.
     *
     * @param numJornada Número de la jornada.
     * @param fechaInicio Fecha de inicio de la jornada.
     * @param fechaFin Fecha de fin de la jornada.
     */
    public Jornada(int numJornada, LocalDate fechaInicio, LocalDate fechaFin) {
        this.numJornada = numJornada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * Métodos getters y setters
     */
    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Enfrentamiento> getEnfrentamientos() {
        return enfrentamientos;
    }

    public void setEnfrentamientos(List<Enfrentamiento> enfrentamientos) {
        this.enfrentamientos = enfrentamientos;
    }

}