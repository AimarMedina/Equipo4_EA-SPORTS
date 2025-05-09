package Modelo;

import Controlador.ModeloController;
import Controlador.VistaController;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase CompeticionDAO que gestiona las operaciones relacionadas con la tabla de competiciones en la base de datos.
 */
public class CompeticionDAO {
    /**
     * Crea una nueva competición en la base de datos con el estado predeterminado.
     * Muestra un mensaje de error si ocurre algún problema al conectarse a la base de datos.
     */
    public static boolean abrirCompeticion(){
        boolean abierto = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();
            PreparedStatement ps = con.prepareStatement("INSERT INTO competiciones (estado) VALUES ('abierto')");
            int filas = ps.executeUpdate();

            if (filas<0){
                abierto = true;
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return abierto;
    }
    /**
     * Cierra una competición actualizando su estado a 'cerrado' en la base de datos.
     *
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public static boolean cerrarCompeticion(){

        boolean cerrado = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "UPDATE competiciones SET estado='cerrado' ";
            PreparedStatement ps = con.prepareStatement(plantilla);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                cerrado = true;
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return cerrado;
    }
    /**
     * Verifica si existe al menos una competición creada en la base de datos.
     *
     * @return 1 si existe al menos una competición, 0 en caso contrario.
     */
    public static int verificarCompeticionCreada(){
        int filasSelect = 0;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT COUNT(*) cant FROM competiciones";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ResultSet filas = ps.executeQuery();

            if (filas.next()){
                filasSelect=filas.getInt("cant");
            }
        }catch (Exception e){
            if (e instanceof SQLSyntaxErrorException) {
                filasSelect = 0;
            } else {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return filasSelect;
    }
    /**
     * Verifica si existe alguna competición con el estado 'abierto' en la base de datos.
     *
     * @return true si existe una competición abierta, false en caso contrario.
     */
    public static boolean estadoCompeticion(){
        boolean estado = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT * FROM competiciones Where estado='abierto'";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ResultSet filas = ps.executeQuery();

            if (filas.next()){
                estado = true;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return estado;
    }

    /**
     * Genera el calendario de una competición en formato "todos contra todos".
     *
     * Este método crea un calendario de partidos para los equipos registrados,
     * distribuyendo los enfrentamientos en jornadas semanales. Cada jornada incluye
     * partidos con fechas y horas generadas aleatoriamente dentro de la semana correspondiente.
     *
     * El calendario se genera siguiendo el algoritmo de rotación para garantizar que
     * todos los equipos se enfrenten entre sí una vez. Además, se registra el ganador
     * y el perdedor de cada partido, y se actualizan los datos en la base de datos.
     *
     * Detalles del proceso:
     * - Se obtiene la lista de equipos desde la base de datos.
     * - Se calcula el número de jornadas y partidos por jornada.
     * - Se generan fechas y horas aleatorias para los partidos.
     * - Se registra cada enfrentamiento en la base de datos.
     * - Se actualizan las estadísticas de los equipos (victorias).
     */
    public static void generarCalendarioCompe(){
        List<String> equipos = EquipoDAO.listaEquipos();
        int n = equipos.size();
        int jornadas = n - 1;
        int partidosPorJornada = n / 2;
        Random rand = new Random();

        List<String> rotacion = new ArrayList<>(equipos);
        LocalDate fechaInicio = LocalDate.now().plusWeeks(1);

        System.out.println("Calendario del torneo (todos contra todos):");

        for (int j = 0; j < jornadas; j++) {
            LocalDate inicioSemana = fechaInicio.plusWeeks(j);
            int numJor = j+1;
            LocalDate finJor = inicioSemana.plusDays(6);
            System.out.println("\nJornada " + (numJor) + " - Semana del: " + inicioSemana + " al " + finJor);
            JornadaDAO.crearJornada(numJor,inicioSemana,finJor);
            for (int i = 0; i < partidosPorJornada; i++) {
                String local = rotacion.get(i);
                String visitante = rotacion.get(n - 1 - i);

                // Día aleatorio de la semana
                int diasDespues = rand.nextInt(7); // 0 a 6
                LocalDate diaPartido = inicioSemana.plusDays(diasDespues);

                // Hora aleatoria entre 15:00 y 21:00
                int hora = 15 + rand.nextInt(7); // 15 a 21
                int minuto = rand.nextBoolean() ? 0 : 30; // 00 o 30

                LocalDateTime fechaHoraPartido = LocalDateTime.of(diaPartido, LocalTime.of(hora, minuto));

                List<String> equiposJugando = new ArrayList<>();
                equiposJugando.add(visitante);
                equiposJugando.add(local);
                List<String> gandor_perdedor = ModeloController.seleccionarGanador(equiposJugando,numJor);

                System.out.println(" - " + local + " vs " + visitante);
                System.out.println("   Fecha y hora: " + fechaHoraPartido);
                System.out.println("   Ganador: " + gandor_perdedor.get(0));
                System.out.println("   Perdedor: " + gandor_perdedor.get(1));

                EnfrentamientoDAO.crearEnfrentamiento(diaPartido, LocalTime.from(fechaHoraPartido),gandor_perdedor.get(0),gandor_perdedor.get(1),numJor);
                EquipoDAO.insertarGanador(gandor_perdedor.get(0));
            }

            // Rotar todos menos el primer equipo
            rotacion.add(1, rotacion.remove(rotacion.size() - 1));
        }
    }



}