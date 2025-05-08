package Controlador;

import Modelo.RolesDAO;
/**
 * Controlador para gestionar las operaciones relacionadas con los roles.
 */
public class RolesController {
    /**
     * Obtiene la clave primaria (PK) de un rol específico desde la base de datos.
     *
     * @param rol Nombre del rol del cual se desea obtener la clave primaria.
     * @return La clave primaria del rol si se encuentra, 0 en caso contrario.
     */
    public static int obtenerPKRol(String rol){
        return RolesDAO.obtenerPKRol(rol);
    }
}
