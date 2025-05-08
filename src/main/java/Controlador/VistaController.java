
package Controlador;

import Vista.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class VistaController {
    private static ModeloController mc = new ModeloController();

    /*
    *FUNCIONES
     */

        /*
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

            public static boolean modificarJugador(String nombre, String apellido, String nacionalidad, LocalDate fecha, String nickname, Float sueldoFloat, String rol, String equipo,Boolean duplicado,String nickname_viejo,Boolean cambiarRoles) {
                return ModeloController.modificarJugador(nombre,apellido,nacionalidad,fecha,nickname,sueldoFloat,rol,equipo,duplicado,nickname_viejo,cambiarRoles);
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

        /*
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

        /*
        *USUARIO
         */
            public static boolean inciarSesionUsuario(String usr, String con, String tipoUsr) {
                return ModeloController.inciarSesionUsuario(usr,con,tipoUsr);
            }

        /*
        *JORNADAS
         */
            public static List<String[]> obtenerJornadas(){
                return ModeloController.obtenerJornadas();
            }

        /*
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

    /*
    *VENTANAS NUEVAS
     */

        /*
        *PRINCIPAL
         */
            public static void VentanaPrincipalV2(){
                VentanaPrincipalV2 vp2 = new VentanaPrincipalV2();
                vp2.setVisible(true);
            }

        /*
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

            /*
            *VENTANA INICIAR SESCION
             */
                public static void VentanaInciarSesionV2(String tipoUsr,JFrame ventana){
                    ventana.dispose();
                    VentanaIniciarSesionV2 vis2 = new VentanaIniciarSesionV2(tipoUsr);
                    vis2.setVisible(true);
                }
        /*
        *VENTANAS ADMINISTRADOR
         */
            public static void VentanaAdministradorV2(String nombre, JFrame ventana) {
                ventana.dispose();
                VentanaAdministradorV2 va2 = new VentanaAdministradorV2(nombre);
                va2.setVisible(true);
            }

            /*
            *GESTION JUGADORES
             */
                public static void VentanaGestionJugadoresV2(JFrame ventana, String nombre) {
                    ventana.dispose();
                    VentanaGestionJugadoresV2 vg2 = new VentanaGestionJugadoresV2(nombre);
                    vg2.setVisible(true);
                }

                /*
                *INSCRIBIR JUGADOR
                 */
                    public static void ModalInscripcionJugadoresV2() {
                        ModalInscripcionJugadoresV2 vg2 = new ModalInscripcionJugadoresV2();
                        vg2.setVisible(true);
                    }

                /*
                *ELIMINAR JUGADOR
                 */
                    public static void ModalEliminacionJugadoresV2(){
                        ModalEliminacionJugadresV2 mej2 = new ModalEliminacionJugadresV2();
                        mej2.setVisible(true);
                    }

                /*
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

                /*
                *MOSTRAR JUGADOR
                 */
                    public static void ModalMostrarJugadoresV2(){
                        ModalMostrarJugadoresV2 mmj2 = new ModalMostrarJugadoresV2();
                        mmj2.setVisible(true);
                    }

            /*
            *GESTION EQUIPOS
             */
                public static void VentanaGestionEquiposV2(JFrame ventana, String nombre) {
                    ventana.dispose();
                    VentanaGestionEquiposV2 vg2 = new VentanaGestionEquiposV2(nombre);
                    vg2.setVisible(true);
                }

                /*
                *INSCRIBIR EQUIPO
                 */
                        public static void ModalInscripcionEquiposV2(){
                            ModalInscripcionEquipoV2 mme2 = new ModalInscripcionEquipoV2();
                            mme2.setVisible(true);
                        }
                /*
                *ELIMINAR EQUIPO
                 */
                    public static void ModalEliminacionEquiposV2(){
                        ModalEliminacionEquiposV2 mee2 = new ModalEliminacionEquiposV2();
                        mee2.setVisible(true);
                    }

                /*
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

                /*
                *MOSTRAR EQUIPO
                 */
                    public static void ModalMostrarEquiposV2(){
                        ModalMostrarEquiposV2 mme2 = new ModalMostrarEquiposV2();
                        mme2.setVisible(true);
                    }

            /*
            *GESTION JORNADAS
             */
                public static void VentanaGestionJornadasV2(JFrame ventana,String nombre) {
                    ventana.dispose();
                    VentanaGestionJornadasV2 vgj2 = new VentanaGestionJornadasV2(nombre);
                    vgj2.setVisible(true);
                }

                /*
                *MOSTRAR JORNADAS
                 */
                    public static void ModalMostrarJornadasV2(){
                        ModalMostrarJornadasV2 mmj2 = new ModalMostrarJornadasV2();
                        mmj2.setVisible(true);
                    }

            /*
            *GESTION ENFRENTAMIENTOS
             */
                public static void VentanaGestionEnfrentamientosV2(JFrame ventana, String nombre) {
                    ventana.dispose();
                    VentanaGestionEnfrentamientosV2 vge2 = new VentanaGestionEnfrentamientosV2(nombre);
                    vge2.setVisible(true);
                }

                /*
                *MOSTRAR ENFRENTAMIENTOS
                 */
                    public static void ModalMostrarEnfrentamientosV2(){
                        ModalMostrarEnfrentamientosV2 mme2 = new ModalMostrarEnfrentamientosV2();
                        mme2.setVisible(true);
                    }

                    public static List<String[]> obtenerEnfrentamientos(){
                        return ModeloController.obtenerEnfrentamientos();
                    }

            /*
            *GESTION COMPETICION
             */
                public static void VentanaGestionCompeticionV2(JFrame ventana,String nombre) {
                    ventana.dispose();
                    VentanaGestionCompeticionV2 vgc2 = new VentanaGestionCompeticionV2(nombre);
                    vgc2.setVisible(true);
                }

                /*
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

            /*
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