package Controlador;

import Modelo.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
/**
 * Controlador para gestionar las operaciones relacionadas con los equipos.
 */
public class EquipoController {

    /**
     * Inscribe un equipo en la base de datos con el nombre y la fecha proporcionados.
     * Si la inscripción es exitosa, se añaden los roles predeterminados al equipo.
     *
     * @param nombre Nombre del equipo a inscribir.
     * @param fecha Fecha de inscripción del equipo.
     * @return true si el equipo fue inscrito correctamente, false en caso contrario.
     */
    public static boolean inscribirEquipo(String nombre, LocalDate fecha){
        boolean insertado = false;
        insertado = EquipoDAO.inscribirEquipo(nombre,fecha);

        if (insertado){
            EquipoRolesDAO.añadirRolesDefaultEquipo(nombre);
        }
        return insertado;
    }
    /**
     * Busca un equipo en la base de datos por su nombre.
     *
     * @param nombre Nombre del equipo a buscar.
     * @return true si el equipo existe, false en caso contrario.
     */
    public static boolean buscarEquipo(String nombre){
        return EquipoDAO.buscarEquipo(nombre);
    }
    /**
     * Obtiene una lista de nombres de todos los equipos registrados en la base de datos.
     *
     * @return Una lista de cadenas con los nombres de los equipos.
     */
    public static List<String> listaEquipos(){
        return EquipoDAO.listaEquipos();
    }
    /**
     * Elimina un equipo de la base de datos por su nombre.
     *
     * @param equipoSeleccionado Nombre del equipo a eliminar.
     * @return true si el equipo fue eliminado correctamente, false en caso contrario.
     */
    public static boolean eliminarEquipo(String equipoSeleccionado) {
        return EquipoDAO.eliminarEquipo(equipoSeleccionado);
    }

    /**
     * Obtiene una lista de equipos con sus respectivas fechas de inscripción.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene el nombre del equipo y su fecha de inscripción.
     */
    public static List<String[]> obtenerEquiposConFechas(){
        return EquipoDAO.obtenerEquiposConFechas();
    }
    /**
     * Modifica el nombre y la fecha de inscripción de un equipo en la base de datos.
     *
     * @param nuevoNombre Nuevo nombre del equipo.
     * @param nuevaFecha Nueva fecha de inscripción del equipo.
     * @return true si la modificación fue exitosa, false en caso contrario.
     */
    public static boolean modificarEquipo(String nuevoNombre, LocalDate nuevaFecha, Boolean duplicado,String nombre){
        return EquipoDAO.modificarEquipo(nuevoNombre,nuevaFecha,duplicado,nombre);
    }

    /**
     * Obtiene el identificador único (PK) de un equipo en la base de datos por su nombre.
     *
     * @param nombre Nombre del equipo.
     * @return El identificador del equipo si se encuentra, 0 en caso contrario.
     */
    public static int obtenerPKequipo(String nombre){
        return EquipoDAO.obtenerPKequipo(nombre);
    }
    //Comprobacion para cerrar Competicion(Equipos)
    /**
     * Verifica si hay más de dos equipos registrados en la base de datos.
     *
     * @return true si hay más de dos equipos, false en caso contrario.
     */
    public static boolean hayMasDeDosEquipos() {
        return EquipoDAO.hayMasDeDosEquipos();
    }
    /**
     * Verifica si la cantidad de equipos registrados en la base de datos es par.
     *
     * @return true si la cantidad de equipos es par, false en caso contrario.
     */
    public static boolean hayCantidadParDeEquipos() {
        return EquipoDAO.hayCantidadParDeEquipos();
    }


}
