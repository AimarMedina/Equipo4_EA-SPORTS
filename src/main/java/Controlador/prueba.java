package Controlador;

import Modelo.EquipoRolesDAO;
import Modelo.JugadorDAO;
import Vista.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class prueba {
    package Controlador;

import Modelo.EquipoRolesDAO;
import Modelo.JugadorDAO;

import java.time.LocalDate;
import java.util.List;
    /**
     * Controlador para gestionar las operaciones relacionadas con los jugadores.
     */
    public class JugadorController {

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
        public static boolean inscribirJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaParseada, String nickname, float sueldoFloat, String rol, String equipo) {
            boolean insertado =  JugadorDAO.inscribirJugador(nombre,apellido,nacionalidad,fechaParseada,nickname,sueldoFloat, rol, equipo);

            if (insertado){
                EquipoRolesDAO.eliminarRolEquipo(rol,equipo);
            }

            return insertado;
        }
        /**
         * Busca un jugador en la base de datos por su nombre.
         *
         * @param nombre Nombre del jugador a buscar.
         * @return true si el jugador existe, false en caso contrario.
         */
        public static boolean buscarJugador(String nombre){
            return JugadorDAO.buscarJugador(nombre);
        }
        /**
         * Obtiene una lista de nombres de todos los jugadores registrados.
         *
         * @return Una lista de cadenas con los nombres de los jugadores.
         */
        public static List<String> listaJugadores(){
            return JugadorDAO.listaJugadores();
        }
        /**
         * Elimina un jugador de la base de datos y actualiza los roles disponibles.
         *
         * @param jugadorSeleccionado Nombre del jugador a eliminar.
         * @return true si el jugador fue eliminado correctamente, false en caso contrario.
         */
        public static boolean eliminarJugador(String jugadorSeleccionado) {
            boolean eliminado = false;

            if (EquipoRolesController.insertarRolJugadorEliminado(jugadorSeleccionado)){
                eliminado = JugadorDAO.eliminarJugador(jugadorSeleccionado);
            }

            return eliminado;
        }

        /**
         * Busca un jugador en la base de datos por su nickname.
         *
         * @param nickname Nickname del jugador a buscar.
         * @return true si el nickname existe, false en caso contrario.
         */
        public static boolean buscarNickname(String nickname) {
            return JugadorDAO.buscarNickname(nickname);
        }



        /**
         * Obtiene una lista de jugadores con sus detalles almacenados en la base de datos.
         *
         * @return Una lista de arreglos de cadenas, donde cada arreglo contiene los detalles de un jugador.
         */
        public static List<String[]> obtenerJugadores() {
            return JugadorDAO.obtenerJugadores();

        }
        //Comprobacion para cerrar Competicion(Jugadores)

        /**
         * Verifica si los equipos tienen una cantidad válida de jugadores para cerrar la competición.
         *
         * @return true si todos los equipos tienen una cantidad válida de jugadores, false en caso contrario.
         */
        public static boolean equiposConCantidadValidaDeJugadores() {
            return JugadorDAO.equiposConCantidadValidaDeJugadores();
        }

        /**
         * Obtiene la clave primaria (PK) de un jugador específico desde la base de datos.
         *
         * @param jugador Nombre del jugador del cual se desea obtener la clave primaria.
         * @return La clave primaria del jugador si se encuentra, 0 en caso contrario.
         */
        public static int obtenerPKjugadorNick(String jugador){
            return JugadorDAO.obtenerPKjugadorNick(jugador);
        }

        /**
         * Obtiene el rol de un jugador específico desde la base de datos.
         *
         * @param jugador Nombre del jugador del cual se desea obtener el rol.
         * @return El rol del jugador si se encuentra, null en caso contrario.
         */
        public static String obtenerRolJugador(String jugador){
            return JugadorDAO.obtenerRolJugador(jugador);
        }

        /**
         * Obtiene el identificador del equipo al que pertenece un jugador.
         *
         * @param idJugador Identificador del jugador.
         * @return El identificador del equipo si se encuentra, 0 en caso contrario.
         */
        public static int obtenerEquipoJugador(int idJugador){
            return JugadorDAO.obtenerEquipoJugador(idJugador);
        }
        /**
         * Obtiene una lista de todos los nicknames de los jugadores registrados en la base de datos.
         *
         * @return Una lista de cadenas que contiene los nicknames de los jugadores.
         */
        public static List<String> listaNicknames(){
            return JugadorDAO.listaNicknames();
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
         * @return true si el jugador fue modificado correctamente, false en caso contrario.
         */
        public static boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fecha, String nickname, Float sueldoFloat, String rol, String equipo,Boolean duplicado,String nickname_viejo) {
            EquipoRolesDAO.eliminarRolEquipo(rol,equipo);
            EquipoRolesDAO.insertarRolJugadorEliminado(nickname_viejo);
            return JugadorDAO.modificarJugador(nombre,apellido,nacionalidad,fecha,nickname,sueldoFloat,rol,equipo,duplicado,nickname_viejo);
        }
        /**
         * Obtiene la cantidad de jugadores que pertenecen a un equipo específico.
         *
         * @param equipo Nombre del equipo.
         * @return La cantidad de jugadores en el equipo.
         */
        public static int obtenerCantidadJugadoreEquipo(String equipo){
            return JugadorDAO.obtenerCantidadJugadoreEquipo(equipo);
        }
        /**
         * Obtiene el rol de un jugador específico a partir de su nickname.
         *
         * @param nickname Nickname del jugador.
         * @return El rol del jugador si se encuentra, null en caso contrario.
         */
        public static String obtenerRolJugadorNick(String nickname){
            return JugadorDAO.obtenerRolJugadorNick(nickname);
        }
    }

package Controlador;

import Vista.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
    /**
     * Controlador para gestionar las operaciones relacionadas con la vista del proyecto.
     */
    public class VistaController {
        private static ModeloController mc = new ModeloController();

        /**
         *FUNCIONES
         */

        /**
         *JUGADORES
         */
        public static boolean inscribirJugador(String nombre, String apellido, String nacionalidad, LocalDate fechaParseada, String nickname, float sueldoFloat, String rol, String equipo) {
            return ModeloController.inscribirJugador(nombre, apellido,nacionalidad,fechaParseada,nickname,sueldoFloat, rol, equipo);
        }

        public static List<String> listaJugadores(){
            return ModeloController.listaJugadores();
        }

        public static List<String> listaNicknames(){
            return ModeloController.listaNicknames();
        }

        public static boolean eliminarJugador(String jugadorSeleccionado) {
            return ModeloController.eliminarJugador(jugadorSeleccionado);
        }

        public static boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fecha, String nickname, Float sueldoFloat, String rol, String equipo,Boolean duplicado,String nickname_viejo) {
            return ModeloController.modificarJugador(nombre,apellido,nacionalidad,fecha,nickname,sueldoFloat,rol,equipo,duplicado,nickname_viejo);
        }

        public static List<String[]> obtenerJugadores() {
            return ModeloController.obtenerJugadores();
        }

        public static boolean buscarNickname(String nickname) {
            return ModeloController.buscarNickname(nickname);
        }

        public static List<String> obtenerRoles(String equipoSeleccionado) {
            return ModeloController.obtenerRoles(equipoSeleccionado);
        }

        public static int obtenerCantidadJugadoreEquipo(String equipo){
            return ModeloController.obtenerCantidadJugadoreEquipo(equipo);
        }

        public static String obtenerRolJugadorNick(String nickname){
            return ModeloController.obtenerRolJugadorNick(nickname);
        }


        /**
         *EQUIPO
         */
        public static List<String> listaEquipos(){
            return ModeloController.listaEquipos();
        }

        public static boolean inscribirEquipo(String nombre, LocalDate fecha) {
            return ModeloController.inscribirEquipo(nombre,fecha);
        }

        public static boolean modificarEquipo(String nuevoNombre, LocalDate nuevaFecha, Boolean duplicado, String nombre){
            return ModeloController.modificarEquipo(nuevoNombre,nuevaFecha,duplicado,nombre);
        }

        public static boolean buscarEquipo(String nombre){
            return ModeloController.buscarEquipo(nombre);
        }

        public static boolean eliminarEquipo(String equipoSeleccionado) {
            return ModeloController.eliminarEquipo(equipoSeleccionado);
        }

        public static List<String[]> obtenerEquiposConFechas(){
            return ModeloController.obtenerEquiposConFechas();
        }

        /**
         *USUARIO
         */
        public static boolean inciarSesionUsuario(String usr, String con, String tipoUsr) {
            return ModeloController.inciarSesionUsuario(usr,con,tipoUsr);
        }

        /**
         *JORNADAS
         */
        public static List<String[]> obtenerJornadas(){
            return ModeloController.obtenerJornadas();
        }

        /**
         *ENFRENTAMIENTOS
         */
        public static List<String[]> obtenerEnfretamientos(){return ModeloController.obtenerEnfrentamientos();}

        /**
         *COMPETICION
         */
        public static boolean abrirCompeticion(){
            return ModeloController.abrirCompeticion();
        }

        public static boolean cerrarCompeticion(){
            return ModeloController.cerrarCompeticion();
        }

        public static int verificarCompeticionCreada(){
            return ModeloController.verificarCompeticionCreada();
        }

        public static boolean estadoCompeticion() {
            return ModeloController.estadoCompeticion();
        }

        /**
         *VENTANAS NUEVAS
         */

        /**
         *PRINCIPAL
         */
        public static void VentanaPrincipalV2(){
            VentanaPrincipalV2 vp2 = new VentanaPrincipalV2();
            vp2.setVisible(true);
        }

        /**
         /SELECCION USUARIO
         */
        public static void ModalDescripcionUsuariosV2(String nombre){
            ModalDescripcionUsuariosV2 vdu2 = new ModalDescripcionUsuariosV2(nombre);
            vdu2.setVisible(true);
        }

        public static void VentanaSelccionUsuarioV2(JFrame ventana){
            ventana.dispose();
            VentanaSeleccionUsuarioV2 vsu2 = new VentanaSeleccionUsuarioV2();
            vsu2.setVisible(true);
        }

        /**
         *VENTANA INICIAR SESCION
         */
        public static void VentanaInciarSesionV2(String tipoUsr,JFrame ventana){
            ventana.dispose();
            VentanaIniciarSesionV2 vis2 = new VentanaIniciarSesionV2(tipoUsr);
            vis2.setVisible(true);
        }
        /**
         *VENTANAS ADMINISTRADOR
         */
        public static void VentanaAdministradorV2(String nombre, JFrame ventana) {
            ventana.dispose();
            VentanaAdministradorV2 va2 = new VentanaAdministradorV2(nombre);
            va2.setVisible(true);
        }

        /**
         *GESTION JUGADORES
         */
        public static void VentanaGestionJugadoresV2(JFrame ventana, String nombre) {
            ventana.dispose();
            VentanaGestionJugadoresV2 vg2 = new VentanaGestionJugadoresV2(nombre);
            vg2.setVisible(true);
        }

        /**
         *INSCRIBIR JUGADOR
         */
        public static void ModalInscripcionJugadoresV2() {
            ModalInscripcionJugadoresV2 vg2 = new ModalInscripcionJugadoresV2();
            vg2.setVisible(true);
        }

        /**
         *ELIMINAR JUGADOR
         */
        public static void ModalEliminacionJugadoresV2(){
            ModalEliminacionJugadresV2 mej2 = new ModalEliminacionJugadresV2();
            mej2.setVisible(true);
        }

        /**
         *MODIFICAR JUGADOR
         */
        public static void ModalModificarJugadorV2(JDialog modal,String jugador){
            modal.dispose();
            ModalModificarJugadoresV2 mmj2 = new ModalModificarJugadoresV2(jugador);
            mmj2.setVisible(true);
        }

        public static void ModalSeleccionarJugadorV2(){
            ModalSeleccionJugadorV2 msj2 = new ModalSeleccionJugadorV2();
            msj2.setVisible(true);
        }

        /**
         *MOSTRAR JUGADOR
         */
        public static void ModalMostrarJugadoresV2(){
            ModalMostrarJugadoresV2 mmj2 = new ModalMostrarJugadoresV2();
            mmj2.setVisible(true);
        }

        /**
         *GESTION EQUIPOS
         */
        public static void VentanaGestionEquiposV2(JFrame ventana, String nombre) {
            ventana.dispose();
            VentanaGestionEquiposV2 vg2 = new VentanaGestionEquiposV2(nombre);
            vg2.setVisible(true);
        }

        /**
         *INSCRIBIR EQUIPO
         */
        public static void ModalInscripcionEquiposV2(){
            ModalInscripcionEquipoV2 mme2 = new ModalInscripcionEquipoV2();
            mme2.setVisible(true);
        }
        /**
         *ELIMINAR EQUIPO
         */
        public static void ModalEliminacionEquiposV2(){
            ModalEliminacionEquiposV2 mee2 = new ModalEliminacionEquiposV2();
            mee2.setVisible(true);
        }

        /**
         *MODIFICAR EQUIPO
         */
        public static void ModalModificarEquipoV2(JDialog modal,String equipo){
            modal.dispose();
            ModalModificarEquipoV2 mme2 = new ModalModificarEquipoV2(equipo);
            mme2.setVisible(true);
        }

        public static void ModalSeleccionarEquipoV2(){
            ModalSeleccionEquipoV2 mse2 = new ModalSeleccionEquipoV2();
            mse2.setVisible(true);
        }

        /**
         *MOSTRAR EQUIPO
         */
        public static void ModalMostrarEquiposV2(){
            ModalMostrarEquiposV2 mme2 = new ModalMostrarEquiposV2();
            mme2.setVisible(true);
        }

        /**
         *GESTION JORNADAS
         */
        public static void VentanaGestionJornadasV2(JFrame ventana,String nombre) {
            ventana.dispose();
            VentanaGestionJornadasV2 vgj2 = new VentanaGestionJornadasV2(nombre);
            vgj2.setVisible(true);
        }

        /**
         *MOSTRAR JORNADAS
         */
        public static void ModalMostrarJornadasV2(){
            ModalMostrarJornadasV2 mmj2 = new ModalMostrarJornadasV2();
            mmj2.setVisible(true);
        }

        /**
         *GESTION ENFRENTAMIENTOS
         */
        public static void VentanaGestionEnfrentamientosV2(JFrame ventana, String nombre) {
            ventana.dispose();
            VentanaGestionEnfrentamientosV2 vge2 = new VentanaGestionEnfrentamientosV2(nombre);
            vge2.setVisible(true);
        }

        /**
         *MOSTRAR ENFRENTAMIENTOS
         */
        public static void ModalMostrarEnfrentamientosV2(){
            ModalMostrarEnfrentamientosV2 mme2 = new ModalMostrarEnfrentamientosV2();
            mme2.setVisible(true);
        }

        public static List<String[]> obtenerEnfrentamientos(){
            return ModeloController.obtenerEnfrentamientos();
        }

        /**
         *GESTION COMPETICION
         */
        public static void VentanaGestionCompeticionV2(JFrame ventana,String nombre) {
            ventana.dispose();
            VentanaGestionCompeticionV2 vgc2 = new VentanaGestionCompeticionV2(nombre);
            vgc2.setVisible(true);
        }

        /**
         *CERRAR COMPETICION
         */
        public static boolean ModalConfirmarCerrarCompeV2(){
            ModalConfirmarCerrarCompeV2 mccc2 = new ModalConfirmarCerrarCompeV2();
            mccc2.setVisible(true);
            return mccc2.isConfirmado();
        }

        public static List<String> ModalSeleccionGanador(List<String> equipo,int numJor){
            ModalSeleccionGanadroV2 mdg2 = new ModalSeleccionGanadroV2();
            mdg2.ModalSeleccionGanadroV2(equipo,numJor);
            mdg2.setVisible(true);
            return mdg2.getGanadorPerdedor();
        }

        /**
         *OTROS
         */
        public static boolean ModalAdvertencia(){
            ModalAdvertencia vc = new ModalAdvertencia();
            vc.setVisible(true);
            return vc.isConfirmado();
        }


        /**
         * USUARIO
         */
        public static void VentanaUsuarioV2(String nombre,JFrame ventana){
            ventana.dispose();
            VentanaUsuarioV2 vu2 = new VentanaUsuarioV2(nombre);
            vu2.setVisible(true);
        }
    }
}
