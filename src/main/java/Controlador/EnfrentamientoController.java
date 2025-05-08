package Controlador;

import Modelo.EnfrentamientoDAO;

import java.util.List;

public class EnfrentamientoController {
    /**
     * Obtiene la lista de enfrentamientos desde la base de datos.
     *
     * @return Una lista de arreglos de cadenas (\String[]\) que representan los enfrentamientos.
     */
    public static List<String[]> obtenerEnfrentamientos(){
        return EnfrentamientoDAO.obtenerEnfrentamientos();
    }
}
