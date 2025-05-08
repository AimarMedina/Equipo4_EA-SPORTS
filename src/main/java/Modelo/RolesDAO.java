package Modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Clase RolesDAO que gestiona las operaciones relacionadas con la tabla de roles en la base de datos.
 */
public class RolesDAO {
    /**
     * Obtiene el nombre del rol a partir de su identificador único (idRol).
     *
     * @param idRol Identificador único del rol.
     * @return El nombre del rol correspondiente al identificador proporcionado.
     */
    public static String obtenerRolPK(int idRol){
        String rol = "";
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT rol FROM roles WHERE idRol = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,idRol);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                rol = rs.getString("rol");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return rol;
    }
    /**
     * Obtiene el identificador único (idRol) de un rol a partir de su nombre.
     *
     * @param Rol Nombre del rol.
     * @return El identificador único del rol correspondiente al nombre proporcionado.
     */
    public static int obtenerPKRol(String Rol){
        int idrol = 0;
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT idRol FROM roles WHERE rol = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,Rol);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                idrol = rs.getInt("idRol");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return idrol;
    }
}
