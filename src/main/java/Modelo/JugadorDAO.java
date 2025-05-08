package Modelo;

import oracle.jdbc.internal.OracleTypes;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Constructor por defecto que inicializa un jugador sin valores específicos.
 */
public class JugadorDAO {
    /**
     * Inscribe un jugador en la base de datos con los datos proporcionados.
     *
     * @param nombre Nombre del jugador.
     * @param apellido Apellido del jugador.
     * @param nacionalidad Nacionalidad del jugador.
     * @param fechaParseada Fecha de nacimiento del jugador.
     * @param nickname Apodo o nickname del jugador.
     * @param sueldoFloat Sueldo del jugador.
     * @param rol Rol del jugador.
     * @param equipo Nombre del equipo al que pertenece el jugador.
     * @return true si el jugador fue inscrito correctamente, false en caso contrario.
     */
    public static boolean inscribirJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaParseada, String nickname, float sueldoFloat, String rol, String equipo) {
        boolean insertado = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();
            String plantilla = "INSERT INTO jugadores(NOMBRE,APELLIDO,NACIONALIDAD,FECHANAC,NICKNAME,SUELDO, ROL, IDEQUIPO) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, nacionalidad);
            java.sql.Date fechanac = java.sql.Date.valueOf(fechaParseada);

            ps.setDate(4, fechanac);
            ps.setString(5,nickname);
            ps.setFloat(6,sueldoFloat);
            ps.setString(7,rol);
            ps.setInt(8,EquipoDAO.obtenerPKequipo(equipo));
            int filasInsertadas = ps.executeUpdate();

            if (filasInsertadas>0){
                insertado = true;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return insertado;
    }

    /**
     * Busca un jugador en la base de datos por su nombre.
     *
     * @param nombre Nombre del jugador a buscar.
     * @return true si el jugador fue encontrado, false en caso contrario.
     */
    public static boolean buscarJugador(String nombre){
        boolean encontrado=false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();
            String plantilla = "SELECT * FROM jugadores WHERE nombre = ?";

            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                encontrado=true;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return encontrado;
    }

    /**
     * Obtiene una lista con los nombres de todos los jugadores en la base de datos.
     *
     * @return Una lista de nombres de jugadores.
     */
    public static List<String> listaJugadores(){
        List<String> jugadores = new ArrayList<>();

        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            PreparedStatement ps = con.prepareStatement("SELECT nombre FROM jugadores");

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                jugadores.add(rs.getString(1));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return jugadores;
    }
    /**
     * Elimina un jugador de la base de datos por su nombre.
     *
     * @param jugadorSeleccionado Nombre del jugador a eliminar.
     * @return true si el jugador fue eliminado correctamente, false en caso contrario.
     */
    public static boolean eliminarJugador(String jugadorSeleccionado){
        boolean eliminado = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "DELETE FROM jugadores WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,jugadorSeleccionado);

            int filas = ps.executeUpdate();
            if(filas > 0){
                eliminado = true;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return eliminado;
    }

    /**
     * Verifica si existe un jugador en la base de datos por su nickname.
     *
     * @param nickname Nickname del jugador a buscar.
     * @return true si el nickname existe, false en caso contrario.
     */

    public static boolean buscarNickname(String nickname) {
        boolean existe = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();
            String plantilla = "SELECT * FROM jugadores WHERE nickname = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,nickname);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                existe = true;
            }
        }

        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return existe;
    }


    /**
     * Obtiene una lista de jugadores con sus datos personales y profesionales.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los datos de un jugador.
     */
    public static List<String[]> obtenerJugadores() {
            List<String[]> jugadores = new ArrayList<>();

            try {
                BaseDatos.abrirConexion();
                Connection con = BaseDatos.getCon();

                String plantilla = "SELECT nombre, apellido, nacionalidad, fechanac, nickname, sueldo, rol, idequipo  FROM jugadores";
                PreparedStatement ps = con.prepareStatement(plantilla);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String nacionalidad = rs.getString("nacionalidad");
                    String fechaNac = String.valueOf(rs.getDate("fechaNac"));
                    String nickname = rs.getString("nickname");
                    String sueldo = String.valueOf(rs.getFloat("sueldo"));
                    String rol = rs.getString("rol");
                    String idequipo = EquipoDAO.buscarEquipoPK(rs.getInt("idequipo"));

                    jugadores.add(new String[]{nombre, apellido, nacionalidad, fechaNac, nickname, sueldo, rol, idequipo});
                }

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return jugadores;

    }

    //Comprobacion Para CerrarCoompeticion
    /**
     * Verifica si todos los equipos tienen una cantidad válida de jugadores.
     * La cantidad válida es entre 2 y 6 jugadores por equipo.
     *
     * @return true si todos los equipos tienen una cantidad válida de jugadores, false en caso contrario.
     */
    public static boolean equiposConCantidadValidaDeJugadores() {
        boolean valido = true;

        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT idequipo, COUNT(*) AS cantidad FROM jugadores GROUP BY idequipo";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ResultSet rs = ps.executeQuery();

            while (rs.next() && valido) {
                int cantidad = rs.getInt("cantidad");

                if (cantidad < 2 || cantidad > 6) {
                    valido = false;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
            valido = false; // por si hay error, lo consideramos inválido
        }

        return valido;
    }
    /**
     * Obtiene el identificador único (PK) de un jugador en la base de datos por su nombre.
     *
     * @param jugador Nombre del jugador cuyo identificador se desea obtener.
     * @return El identificador del jugador si se encuentra, 0 en caso contrario.
     */
    public static int obtenerPKjugador(String jugador){
        int idJugador = 0;
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT idJugador FROM jugadores WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,jugador);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                idJugador=rs.getInt("idJugador");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return idJugador;
    }
    /**
     * Obtiene el identificador único (PK) de un jugador en la base de datos por su nickname.
     *
     * @param jugador Nickname del jugador cuyo identificador se desea obtener.
     * @return El identificador del jugador si se encuentra, 0 en caso contrario.
     */
    public static int obtenerPKjugadorNick(String jugador){
        int idJugador = 0;
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT idJugador FROM jugadores WHERE nickname = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,jugador);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                idJugador=rs.getInt("idJugador");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return idJugador;
    }

    /**
     * Obtiene el rol de un jugador en la base de datos por su nombre.
     *
     * @param jugador Nombre del jugador cuyo rol se desea obtener.
     * @return El rol del jugador si se encuentra, una cadena vacía en caso contrario.
     */
    public static String obtenerRolJugador(String jugador){
        String rol = "";
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "Select rol From jugadores WHERE idJugador = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,obtenerPKjugadorNick(jugador));
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
     * Obtiene el rol de un jugador en la base de datos utilizando su nickname.
     *
     * @param nick Nickname del jugador cuyo rol se desea obtener.
     * @return El rol del jugador si se encuentra, una cadena vacía en caso contrario.
     */
    public static String obtenerRolJugadorNick(String nick){
        String rol = "";
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "Select rol From jugadores WHERE nickname = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setString(1,nick);
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
     * Obtiene el identificador del equipo al que pertenece un jugador en la base de datos.
     *
     * @param jugador Identificador único (PK) del jugador.
     * @return El identificador del equipo si se encuentra, 0 en caso contrario.
     */
    public static int obtenerEquipoJugador(int jugador){
        int idEquipo = 0;
        try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT idEquipo FROM jugadores WHERE idJugador = ?";
            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,jugador);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                idEquipo=rs.getInt("idEquipo");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return idEquipo;
    }

    /**
     * Genera un informe de los jugadores que pertenecen a un equipo específico.
     * Este método utiliza un procedimiento almacenado en la base de datos llamado
     * `obtener_jugadores_equipo` que recibe el nombre del equipo y devuelve un cursor
     * con los jugadores asociados a dicho equipo.
     *
     * @param nombreEquipo Nombre del equipo cuyos jugadores se desean listar.
     * @throws Exception Si ocurre algún error al ejecutar el procedimiento almacenado
     *                   o al interactuar con la base de datos.
     */
public static void informeJugadoresEquipo(String nombreEquipo) throws Exception {
    BaseDatos.abrirConexion();
    Connection con = BaseDatos.getCon();
    // Llamada al procedimiento almacenado
    CallableStatement cs = con.prepareCall("{ call obtener_jugadores_equipo(?, ?) }");
    cs.setString(1, "NombreDelEquipo");
    cs.registerOutParameter(2, OracleTypes.CURSOR);

    cs.execute();

    ResultSet rs = (ResultSet) cs.getObject(2);
    while (rs.next()) {
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String rol = rs.getString("rol");
        double sueldo = rs.getDouble("sueldo");

    }


}
    /**
     * Obtiene una lista de todos los nicknames de los jugadores almacenados en la base de datos.
     *
     * @return Una lista de cadenas que contiene los nicknames de los jugadores.
     *         Si ocurre un error al conectar con la base de datos, se devuelve una lista vacía.
     */
    public static List<String> listaNicknames(){
    List<String> nicknames = new ArrayList<>();
    try {
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();

            String plantilla = "SELECT nickname FROM jugadores";
            PreparedStatement ps = con.prepareStatement(plantilla);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                nicknames.add(rs.getString("nickname"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return nicknames;
    }
    /**
     * Modifica los datos de un jugador en la base de datos.
     *
     * @param nombre Nombre del jugador.
     * @param apellido Apellido del jugador.
     * @param nacionalidad Nacionalidad del jugador.
     * @param fechaParseada Fecha de nacimiento del jugador.
     * @param nickname Nuevo nickname del jugador.
     * @param sueldoFloat Sueldo del jugador.
     * @param rol Rol del jugador.
     * @param equipo Nombre del equipo al que pertenece el jugador.
     * @param duplicado Indica si el nickname ya existe en la base de datos.
     * @param nickname_viejo Nickname actual del jugador que se desea modificar.
     * @return true si el jugador fue modificado correctamente, false en caso contrario.
     * @throws RuntimeException Si ocurre un error durante la ejecución de la consulta.
     */
    public static boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaParseada, String nickname, float sueldoFloat, String rol, String equipo,Boolean duplicado,String nickname_viejo) {
        boolean modificado = false;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();
            String plantilla;
            PreparedStatement ps;
            int filasActualizadas;
            if (!duplicado){
                plantilla = "UPDATE jugadores SET nombre = ?,apellido =?,nacionalidad = ?,fechanac = ?, nickname = ?, sueldo = ?, rol = ?, idequipo = ? Where nickname = ?";
                ps = con.prepareStatement(plantilla);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, nacionalidad);
                java.sql.Date fechanac = java.sql.Date.valueOf(fechaParseada);

                ps.setDate(4, fechanac);
                ps.setString(5,nickname);
                ps.setFloat(6,sueldoFloat);
                ps.setString(7,rol);
                ps.setInt(8,EquipoDAO.obtenerPKequipo(equipo));
                ps.setString(9,nickname_viejo);
            }else {
                plantilla = "UPDATE jugadores SET nombre = ?,apellido =?,nacionalidad = ?,fechanac = ?, sueldo = ?, rol = ?, idequipo = ? WHere nickname = ?";
                ps = con.prepareStatement(plantilla);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, nacionalidad);
                java.sql.Date fechanac = java.sql.Date.valueOf(fechaParseada);

                ps.setDate(4, fechanac);
                ps.setFloat(5,sueldoFloat);
                ps.setString(6,rol);
                ps.setInt(7,EquipoDAO.obtenerPKequipo(equipo));
                ps.setString(8,nickname_viejo);

            }

            filasActualizadas = ps.executeUpdate();

            if (filasActualizadas>0){
                modificado = true;
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return modificado;
    }
    /**
     * Obtiene la cantidad de jugadores que pertenecen a un equipo específico.
     *
     * @param nombre Nombre del equipo.
     * @return La cantidad de jugadores en el equipo. Si ocurre un error al conectar con la base de datos, se devuelve 0.
     */
    public static int obtenerCantidadJugadoreEquipo(String nombre){
        int cantidad=0;
        try{
            BaseDatos.abrirConexion();
            Connection con = BaseDatos.getCon();
            String plantilla = "SELECT Count(*) cantidad FROM jugadores WHERE idEquipo = ?";

            PreparedStatement ps = con.prepareStatement(plantilla);
            ps.setInt(1,EquipoDAO.obtenerPKequipo(nombre));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cantidad= rs.getInt("cantidad");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
        }
        return cantidad;
    }

}

