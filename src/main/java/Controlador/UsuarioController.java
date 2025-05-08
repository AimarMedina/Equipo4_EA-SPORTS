package Controlador;

import Modelo.UsuarioDAO;
/**
 * Controlador para gestionar las operaciones relacionadas con los usuarios.
 */
public class UsuarioController {
    /**
     * Inicia sesión para un usuario con las credenciales proporcionadas.
     *
     * @param usr Nombre de usuario.
     * @param con Contraseña del usuario.
     * @param tipoUsr Tipo de usuario (por ejemplo, administrador, cliente, etc.).
     * @return true si las credenciales son correctas y el inicio de sesión es exitoso, false en caso contrario.
     */
    public static boolean inciarSesionusUario(String usr,String con, String tipoUsr){
        return UsuarioDAO.iniciarSesion(usr,con,tipoUsr);
    }


}
