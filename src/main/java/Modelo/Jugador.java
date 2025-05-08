package Modelo;

import java.time.LocalDate;
/**
 * Clase Jugador que representa a un jugador con información personal y profesional.
 */
public class Jugador {
    private int idJugador;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private LocalDate fechaNac;
    private String nickname;
    private double sueldo;
//Constructores

    /**
     * Constructor que inicializa un jugador con los datos proporcionados.
     *
     * @param apellido Apellido del jugador.
     * @param nombre Nombre del jugador.
     * @param nacionalidad Nacionalidad del jugador.
     * @param nickname Apodo o nickname del jugador.
     * @param fechaNac Fecha de nacimiento del jugador.
     * @param sueldo Sueldo del jugador.
     */
    public Jugador( String apellido, String nombre, String nacionalidad, String nickname, LocalDate fechaNac, double sueldo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.nickname = nickname;
        this.fechaNac = fechaNac;
        this.sueldo = sueldo;
    }

    /**
     * Constructor por defecto que inicializa un jugador sin valores específicos.
     */
    public Jugador(){}

    /**
     * Getters y Setters
     */

    public int getIdJugador() {return idJugador;}

    public void setIdJugador(int idJugador) {this.idJugador = idJugador;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getNacionalidad() {return nacionalidad;}

    public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public LocalDate getFechaNac() {return fechaNac;}

    public void setFechaNac(LocalDate fechaNac) {this.fechaNac = fechaNac;}

    public String getNickname() {return nickname;}

    public void setNickname(String nickname) {this.nickname = nickname;}

    public double getSueldo() {return sueldo;}

    public void setSueldo(double sueldo) {this.sueldo = sueldo;}
}

