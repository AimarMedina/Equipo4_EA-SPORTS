package Modelo;

import javax.sound.midi.Track;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Clase UsuarioDAO que gestiona las operaciones relacionadas con los usuarios en la base de datos.
 */
public class UsuarioDAO {
    /**
     * Inicia sesión verificando las credenciales del usuario en la base de datos.
     *
     * @param nombre Nombre del usuario.
     * @param contra Contraseña del usuario.
     * @param usr Tipo de usuario (por ejemplo, administrador, cliente, etc.).
     * @return true si las credenciales son correctas y el usuario existe, false en caso contrario.
     */
    public static boolean iniciarSesion(String nombre, String contra, String usr){
        boolean encontrado = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT nombre FROM USUARIOS WHERE nombre = ? AND contrasena = ? AND tipousuario = ?";

            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,nombre);
            ps.setString(2,contra);
            ps.setString(3,usr.toLowerCase());

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                encontrado = true;
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos","ERROR",-1,null);
        }
        return encontrado;

    }
}
