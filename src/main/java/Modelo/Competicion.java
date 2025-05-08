package Modelo;

import Modelo.Enumeraciones.EstadoCompeticion;
/**
 * Clase Competicion que representa una competición con un identificador y un estado.
 */
public class Competicion {
    private int idCompeticion;
    private EstadoCompeticion estado;


    /**
     * Constructor que inicializa una competición con un identificador y un estado específicos.
     *
     * @param idCompeticion Identificador único de la competición.
     * @param estado Estado actual de la competición.
     */
    public Competicion(int idCompeticion, EstadoCompeticion estado) {
        this.idCompeticion = idCompeticion;
        this.estado = estado;
    }
    /**
     * Constructor por defecto que inicializa la competición con valores predeterminados.
     * El identificador se establece en 0 y el estado en CERRADO.
     */
    public Competicion() {
        this.idCompeticion = 0;
        this.estado = EstadoCompeticion.CERRADO;
    }
    /**
     * Obtiene el identificador único de la competición.
     *
     * @return El identificador de la competición.
     */
    public int getIdCompeticion() {
        return idCompeticion;
    }
    /**
     * Establece el identificador único de la competición.
     *
     * @param idCompeticion El nuevo identificador de la competición.
     */
    public void setIdCompeticion(int idCompeticion) {
        this.idCompeticion = idCompeticion;
    }
    /**
     * Obtiene el estado actual de la competición.
     *
     * @return El estado de la competición.
     */
    public EstadoCompeticion getEstado() {
        return estado;
    }
    /**
     * Establece el estado actual de la competición.
     *
     * @param estado El nuevo estado de la competición.
     */
    public void setEstado(EstadoCompeticion estado) {
        this.estado = estado;
    }

}
