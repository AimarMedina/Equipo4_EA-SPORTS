package Modelo;

import Controlador.JugadorController;
import Controlador.RolesController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase EquipoRolesDAO que gestiona las operaciones relacionadas con los roles de los equipos en la base de datos.
 */
public class EquipoRolesDAO {

    /**
     * Obtiene la lista de roles asociados a un equipo específico.
     *
     * @param nombreEquipo Nombre del equipo del cual se desean obtener los roles.
     * @return Una lista de nombres de roles asociados al equipo.
     */
    public static List<String> obtenerRoles(String nombreEquipo){
        List<String> listaRoles = new ArrayList<>();
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT idRol FROM equipoRoles Where idEquipo = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,EquipoDAO.obtenerPKequipo(nombreEquipo));

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String nombreRol = RolesDAO.obtenerRolPK(rs.getInt("idRol"));
                listaRoles.add(nombreRol);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }

        return listaRoles;
    }

    /**
     * Añade los roles predeterminados a un equipo en la base de datos.
     *
     * @param nombre Nombre del equipo al cual se le asignarán los roles predeterminados.
     */
    public static void añadirRolesDefaultEquipo(String nombre){
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "INSERT INTO equipoRoles (idEquipo,idRol) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,EquipoDAO.obtenerPKequipo(nombre));

            for (int i = 1; i<7; i++){
                ps.setInt(2, i);
                ps.executeUpdate();
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
    }

    /**
     * Elimina un rol específico de todos los equipos en la base de datos.
     *
     * @param rol Nombre del rol que se desea eliminar.
     */
    public static void eliminarRolEquipo(String rol,String equipo){
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "DELETE FROM equipoRoles Where idRol=? and idEquipo=?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,RolesController.obtenerPKRol(rol));
            ps.setInt(2,EquipoDAO.obtenerPKequipo(equipo));

            ps.executeUpdate();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
    }

    /**
     * Inserta un rol asociado a un jugador eliminado en la base de datos.
     * Este método obtiene el identificador del equipo al que pertenece el jugador,
     * así como el identificador del rol asociado al jugador, y los inserta en la tabla
     * `equipoRoles`.
     * @param jugador Nombre del jugador cuyo rol se desea insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public static boolean insertarRolJugadorEliminado(String jugador){
        boolean insertado = false;
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            int idEquipo = JugadorController.obtenerEquipoJugador(JugadorController.obtenerPKjugadorNick(jugador));
            int idRol = RolesController.obtenerPKRol(JugadorController.obtenerRolJugador(jugador));

            String plantilla = "INSERT INTO equipoRoles (idEquipo,idRol) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1, idEquipo);
            ps.setInt(2, idRol);

            if (ps.executeUpdate()>0){
                insertado = true;
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return insertado;
    }
}
