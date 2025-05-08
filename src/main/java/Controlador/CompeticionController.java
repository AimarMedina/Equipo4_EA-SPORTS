package Controlador;

import Modelo.CompeticionDAO;
import Modelo.EquipoDAO;

import javax.swing.*;
/**
 * Controlador para gestionar las operaciones relacionadas con la competición.
 */
public class CompeticionController {
    /**
     * Abre una nueva competición en la base de datos.
     *
     * @return true si la competición se abrió correctamente, false en caso contrario.
     */
    public static boolean abrirCompeticion(){
        return CompeticionDAO.abrirCompeticion();
    }

    /**
     * Cierra la competición si se cumplen las condiciones necesarias.
     * Las condiciones incluyen:
     * - Debe haber más de dos equipos.
     * - La cantidad de equipos debe ser par.
     * - Cada equipo debe tener una cantidad válida de jugadores (mínimo 2, máximo 6).
     *
     * @return true si la competición se cerró correctamente, false en caso contrario.
     */
    public static boolean cerrarCompeticion() {
        boolean cerrado = false;
        if (ModeloController.hayMasDeDosEquipos()) {
            if (ModeloController.hayCantidadParDeEquipos()) {
                //Comprobacion para cerrar la Competicion (Jugadores)
                if(ModeloController.equiposConCantidadValidaDeJugadores()) {
                    cerrado = CompeticionDAO.cerrarCompeticion();
                }else{
                    JOptionPane.showMessageDialog(null, "La cantidad valida de jugadores por equipos(min 2/max 6) no es valida para cerrar la competicion)");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad de equipos debe ser par para cerrar la competición.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay suficientes equipos para cerrar la competición.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return cerrado;
    }
    /**
     * Verifica si ya existe una competición creada en la base de datos.
     *
     * @return Un entero que indica el estado de la competición (1 si existe, 0 si no).
     */
    public static int verificarCompeticionCreada(){
        return CompeticionDAO.verificarCompeticionCreada();

    }
    /**
     * Verifica el estado actual de la competición.
     *
     * @return true si la competición está activa, false en caso contrario.
     */
    public static boolean estadoCompeticion() {
        return CompeticionDAO.estadoCompeticion();
    }
}
