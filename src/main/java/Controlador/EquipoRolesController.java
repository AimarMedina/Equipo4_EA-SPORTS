package Controlador;

import Modelo.EquipoRolesDAO;

import java.util.List;
/**
 * Controlador para gestionar las operaciones relacionadas con los roles de los equipos.
 */
public class EquipoRolesController {

    /**
     * Obtiene una lista de roles asociados a un equipo específico.
     *
     * @param equipoSeleccionado Nombre del equipo del cual se desean obtener los roles.
     * @return Una lista de cadenas que representan los roles asociados al equipo.
     */
    public static List<String> obtenerRoles(String equipoSeleccionado){
        return EquipoRolesDAO.obtenerRoles(equipoSeleccionado);
    }
    /**
     * Inserta un rol asociado a un jugador eliminado en la base de datos.
     *
     * @param jugador Nombre del jugador cuyo rol se desea insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public static boolean insertarRolJugadorEliminado(String jugador){
        return EquipoRolesDAO.insertarRolJugadorEliminado(jugador);
    }
}
