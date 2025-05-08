package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase Equipo que representa un equipo con información básica como su nombre, fecha de fundación,
 * lista de roles y lista de jugadores.
 */
public class Equipo {


    private int idEquipo;
    private String nombre;
    private LocalDate fechaFund;
    private List<String> listaRoles;

    /**
     * Lista de jugadores que forman parte del equipo.
     * Relacion
     */

    private List<Jugador> listaJugadores;


    /**
     * Constructor por defecto que inicializa un equipo sin valores específicos.
     */
    public Equipo() {
    }

    /**
     * Constructor que inicializa un equipo con los valores proporcionados.
     *
     * @param idEquipo Identificador único del equipo.
     * @param nombre Nombre del equipo.
     * @param fechaFund Fecha de fundación del equipo.
     * @param listaJugadores Lista de jugadores que forman parte del equipo.
     */
    public Equipo(int idEquipo, String nombre, LocalDate fechaFund, List<Jugador> listaJugadores) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.fechaFund = fechaFund;
        this.listaJugadores = listaJugadores;
        this.listaRoles = new ArrayList<>(Arrays.asList("Duelista", "Centinela", "Iniciador", "Controlador", "Charcutero"));

    }

    //Getters y setters

    /**
     * Obtiene el identificador único del equipo.
     *
     * @return El identificador del equipo.
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * Establece el identificador único del equipo.
     *
     * @param idEquipo El nuevo identificador del equipo.
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    /**
     * Obtiene el nombre del equipo.
     *
     * @return El nombre del equipo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del equipo.
     *
     * @param nombre El nuevo nombre del equipo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Obtiene la fecha de fundación del equipo.
     *
     * @return La fecha de fundación del equipo.
     */
    public LocalDate getFechaFund() {
        return fechaFund;
    }

    /**
     * Establece la fecha de fundación del equipo.
     *
     * @param fechaFund La nueva fecha de fundación del equipo.
     */
    public void setFechaFund(LocalDate fechaFund) {
        this.fechaFund = fechaFund;
    }

    /**
     * Obtiene la lista de jugadores que forman parte del equipo.
     *
     * @return La lista de jugadores del equipo.
     */
    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }
    /**
     * Establece la lista de jugadores que forman parte del equipo.
     *
     * @param listaJugadores La nueva lista de jugadores del equipo.
     */
    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    /**
     * Devuelve una representación en forma de cadena del equipo.
     *
     * @return Una cadena que representa al equipo.
     */
    @Override
    public String toString() {
        return "Equipo{" +
                "fechaFund=" + fechaFund +
                ", idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", listaRoles=" + listaRoles +
                ", listaJugadores=" + listaJugadores +
                '}';
    }
    /**
     * Obtiene la lista de roles asociados al equipo.
     *
     * @return La lista de roles del equipo.
     */
    public List<String> getListaRoles(){
        return listaRoles;
    }


    /**
     * Establece la lista de roles asociados al equipo.
     *
     * @param listaRoles La nueva lista de roles del equipo.
     */
    public void setListaRoles(List<String> listaRoles) {
        this.listaRoles = listaRoles;
    }
}