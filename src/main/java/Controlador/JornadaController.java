package Controlador;

import Modelo.JornadaDAO;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con las jornadas.
 */
public class JornadaController {

    /**
     * Verifica si existe una jornada en la base de datos con el número proporcionado.
     *
     * @param numJornada Número de la jornada a verificar.
     * @return true si la jornada existe, false en caso contrario.
     */
    public static boolean existeJornada (String numJornada){
        return JornadaDAO.existeJornada(numJornada);
    }
    /**
     * Obtiene una lista de jornadas con sus detalles almacenados en la base de datos.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de una jornada.
     */
    public static List<String[]> obtenerJornadas(){
        return JornadaDAO.obtenerJornadas();
    }

    /**
     * Obtiene una lista de nombres o identificadores de todas las jornadas registradas.
     *
     * @return Una lista de cadenas con los nombres o identificadores de las jornadas.
     */
    public static List<String> listaJornadas(){
        return JornadaDAO.listaJornadas();
    }

}
