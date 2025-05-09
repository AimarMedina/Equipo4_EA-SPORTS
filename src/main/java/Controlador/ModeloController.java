package Controlador;

import Modelo.EquipoDAO;
import Modelo.JornadaDAO;
import Modelo.JugadorDAO;

import java.time.LocalDate;
import java.util.List;
/**
 * Controlador principal que centraliza las operaciones relacionadas con usuarios, jugadores, equipos, competiciones y jornadas.
 */
public class ModeloController {
    /**
     * Inicia sesión para un usuario con las credenciales proporcionadas.
     *
     * @param usr Nombre de usuario.
     * @param con Contraseña del usuario.
     * @param tipoUsr Tipo de usuario (por ejemplo, administrador, cliente, etc.).
     * @return true si las credenciales son correctas y el inicio de sesión es exitoso, false en caso contrario.
     */
    public static boolean inciarSesionUsuario(String usr, String con, String tipoUsr){
        return UsuarioController.inciarSesionusUario(usr, con,tipoUsr);
    }
    //Metodos relacionados con Jugadores


    /**
     * Inscribe un jugador en la base de datos con los datos proporcionados.
     *
     * @param nombre Nombre del jugador.
     * @param apellido Apellido del jugador.
     * @param nacionalidad Nacionalidad del jugador.
     * @param fechaParseada Fecha de nacimiento del jugador.
     * @param nickname Apodo del jugador.
     * @param sueldoFloat Sueldo del jugador.
     * @param rol Rol del jugador en el equipo.
     * @param equipo Nombre del equipo al que pertenece el jugador.
     * @return true si el jugador fue inscrito correctamente, false en caso contrario.
     */
    public static  boolean inscribirJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaParseada, String nickname, float sueldoFloat, String rol, String equipo){
        return JugadorController.inscribirJugador(nombre,apellido,nacionalidad,fechaParseada,nickname,sueldoFloat, rol, equipo);
    }

    /**
     * Busca un jugador en la base de datos por su nombre.
     *
     * @param nombre Nombre del jugador a buscar.
     * @return true si el jugador existe, false en caso contrario.
     */
    public static boolean buscarJugador(String nombre){
        return JugadorController.buscarJugador(nombre);
    }
    /**
     * Obtiene una lista de nombres de todos los jugadores registrados.
     *
     * @return Una lista de cadenas con los nombres de los jugadores.
     */
    public static List<String> listaJugadores(){
        return JugadorController.listaJugadores();
    }
    /**
     * Elimina un jugador de la base de datos.
     *
     * @param jugadorSeleccionado Nombre del jugador a eliminar.
     * @return true si el jugador fue eliminado correctamente, false en caso contrario.
     */
    public static boolean eliminarJugador(String jugadorSeleccionado) {
        return JugadorController.eliminarJugador(jugadorSeleccionado);
    }

    /**
     * Obtiene una lista de jugadores con sus detalles almacenados en la base de datos.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de un jugador.
     */
    public static List<String[]> obtenerJugadores(){
        return JugadorController.obtenerJugadores();
    }

//Metodos relacionados con Equipos

    /**
     * Inscribe un equipo en la base de datos con los datos proporcionados.
     *
     * @param nombre Nombre del equipo.
     * @param fecha Fecha de creación del equipo.
     * @return true si el equipo fue inscrito correctamente, false en caso contrario.
     */
    public static boolean inscribirEquipo(String nombre, LocalDate fecha){
        return EquipoController.inscribirEquipo(nombre,fecha);
    }

    /**
     * Busca un equipo en la base de datos por su nombre.
     *
     * @param nombre Nombre del equipo a buscar.
     * @return true si el equipo existe, false en caso contrario.
     */
    public static boolean buscarEquipo(String nombre){
        return EquipoController.buscarEquipo(nombre);
    }

    /**
     * Obtiene una lista de nombres de todos los equipos registrados.
     *
     * @return Una lista de cadenas con los nombres de los equipos.
     */
    public static List<String> listaEquipos(){
        return EquipoController.listaEquipos();
    }
    /**
     * Elimina un equipo de la base de datos.
     *
     * @param equipoSeleccionado Nombre del equipo a eliminar.
     * @return true si el equipo fue eliminado correctamente, false en caso contrario.
     */
    public static boolean eliminarEquipo(String equipoSeleccionado) {
        return EquipoController.eliminarEquipo(equipoSeleccionado);
    }
    /**
     * Obtiene una lista de equipos con sus fechas de creación almacenadas en la base de datos.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de un equipo.
     */
    public static List<String[]> obtenerEquiposConFechas(){
        return EquipoController.obtenerEquiposConFechas();
    }
    /**
     * Modifica los datos de un equipo en la base de datos.
     *
     * @param nuevoNombre Nuevo nombre del equipo.
     * @param nuevaFecha Nueva fecha de creación del equipo.
     * @param nombre Nombre actual del equipo.
     * @return true si el equipo fue modificado correctamente, false en caso contrario.
     */
    public static boolean modificarEquipo(String nuevoNombre, LocalDate nuevaFecha, Boolean duplicado, String nombre){
        return EquipoController.modificarEquipo(nuevoNombre,nuevaFecha,duplicado,nombre);
    }

    //Métodos relacionados con competiciones

    /**
     * Abre una competición.
     *
     * @return true si la competición fue abierta correctamente, false en caso contrario.
     */
    public static boolean abrirCompeticion(){
        return CompeticionController.abrirCompeticion();
    }

    /**
     * Cierra una competición.
     *
     * @return true si la competición fue cerrada correctamente, false en caso contrario.
     */
    public static boolean cerrarCompeticion(){
        return CompeticionController.cerrarCompeticion();
    }
    //Comprobacion para Competicion(Equipos)

    /**
     * Verifica si hay más de dos equipos registrados.
     *
     * @return true si hay más de dos equipos, false en caso contrario.
     */
    public static boolean hayMasDeDosEquipos() {
        return EquipoController.hayMasDeDosEquipos();
    }
    /**
     * Verifica si hay una cantidad par de equipos registrados.
     *
     * @return true si hay una cantidad par de equipos, false en caso contrario.
     */
    public static boolean hayCantidadParDeEquipos() {
        return EquipoController.hayCantidadParDeEquipos();
    }
    //Comprobacion para Competicion(Jugadores)

    /**
     * Verifica si los equipos tienen una cantidad válida de jugadores para cerrar la competición.
     *
     * @return true si todos los equipos tienen una cantidad válida de jugadores, false en caso contrario.
     */
    public static boolean equiposConCantidadValidaDeJugadores() {
        return JugadorController.equiposConCantidadValidaDeJugadores();
    }


    /**
     * Verifica si una competición ya ha sido creada.
     *
     * @return Un entero que indica el estado de la competición.
     */
    public static int verificarCompeticionCreada(){
        return CompeticionController.verificarCompeticionCreada();
    }
    /**
     * Verifica el estado actual de la competición.
     *
     * @return true si la competición está activa, false en caso contrario.
     */
    public static boolean estadoCompeticion() {
        return CompeticionController.estadoCompeticion();
    }

    //Métodos relacionados con jornadas

    /**
     * Verifica si una jornada existe en la base de datos.
     *
     * @param numJornada Número de la jornada a verificar.
     * @return true si la jornada existe, false en caso contrario.
     */
    public static boolean existeJornada (String numJornada){
        return JornadaController.existeJornada(numJornada);
    }

    /**
     * Obtiene una lista de jornadas con sus detalles almacenados en la base de datos.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de una jornada.
     */
    public static List<String[]> obtenerJornadas(){
        return JornadaController.obtenerJornadas();
    }
    /**
     * Obtiene una lista de nombres de todas las jornadas registradas.
     *
     * @return Una lista de cadenas con los nombres de las jornadas.
     */
    public static List<String> listaJornadas(){
        return JornadaController.listaJornadas();
    }

    /**
     * Busca un jugador en la base de datos por su nickname.
     *
     * @param nickname Nickname del jugador a buscar.
     * @return true si el nickname existe, false en caso contrario.
     */
    public static boolean buscarNickname(String nickname) {
        return JugadorController.buscarNickname(nickname);
    }


    /**
     * Obtiene una lista de roles disponibles para un equipo seleccionado.
     *
     * @param equipoSeleccionado Nombre del equipo.
     * @return Una lista de cadenas con los roles disponibles.
     */
    public static List<String> obtenerRoles(String equipoSeleccionado) {
        return EquipoRolesController.obtenerRoles(equipoSeleccionado);
    }
    /**
     * Selecciona el ganador y el perdedor de un enfrentamiento entre equipos en una jornada específica.
     *
     * @param equipos Lista de nombres de los equipos que participan en el enfrentamiento.
     * @param numJor Número de la jornada en la que se realiza el enfrentamiento.
     * @return Una lista de cadenas donde el primer elemento es el nombre del equipo ganador y el segundo es el del perdedor.
     */
    public static List<String> seleccionarGanador(List<String> equipos,int numJor){
        return VistaController.ModalSeleccionGanador(equipos,numJor);
    }
    /**
     * Obtiene una lista de todos los nicknames de los jugadores registrados en la base de datos.
     *
     * @return Una lista de cadenas que contiene los nicknames de los jugadores.
     */
    public static List<String> listaNicknames(){
        return JugadorController.listaNicknames();
    }
    /**
     * Modifica los datos de un jugador en la base de datos.
     *
     * @param nombre Nombre del jugador.
     * @param apellido Apellido del jugador.
     * @param nacionalidad Nacionalidad del jugador.
     * @param fecha Fecha de nacimiento del jugador.
     * @param nickname Nuevo nickname del jugador.
     * @param sueldoFloat Sueldo del jugador.
     * @param rol Rol del jugador en el equipo.
     * @param equipo Nombre del equipo al que pertenece el jugador.
     * @param duplicado Indica si el nickname ya existe en la base de datos.
     * @param nickname_viejo Nickname actual del jugador que se desea modificar.
     * @param cambiarRoles Indica si se deben actualizar los roles del jugador en el equipo.
     * @return true si el jugador fue modificado correctamente, false en caso contrario.
     */
    public static boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fecha, String nickname, Float sueldoFloat, String rol, String equipo,Boolean duplicado,String nickname_viejo) {
        return JugadorController.modificarJugador(nombre,apellido,nacionalidad,fecha,nickname,sueldoFloat,rol,equipo,duplicado,nickname_viejo);
    }
    /**
     * Obtiene la cantidad de jugadores que pertenecen a un equipo específico.
     *
     * @param equipo Nombre del equipo.
     * @return La cantidad de jugadores en el equipo.
     */
    public static int obtenerCantidadJugadoreEquipo(String equipo){
        return JugadorController.obtenerCantidadJugadoreEquipo(equipo);
    }
    /**
     * Obtiene el rol de un jugador específico a partir de su nickname.
     *
     * @param nickname Nickname del jugador.
     * @return El rol del jugador si se encuentra, null en caso contrario.
     */
    public static String obtenerRolJugadorNick(String nickname){
        return JugadorController.obtenerRolJugadorNick(nickname);
    }
    /**
     * Obtiene una lista de enfrentamientos registrados en la base de datos con sus detalles.
     *
     * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de un enfrentamiento.
     */
    public static List<String[]> obtenerEnfrentamientos(){
        return EnfrentamientoController.obtenerEnfrentamientos();
    }

}