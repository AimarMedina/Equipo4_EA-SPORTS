package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
/**
 * Clase que representa la ventana principal del administrador.
 */
public class VentanaAdministradorV2 extends JFrame {
    private JPanel pPincipal;
    private JLabel Titulo;
    private JButton jugadores;
    private JButton equipos;
    private JButton jornadas;
    private JButton retroceder;
    private JButton competicion;
    private JButton enfrentamientos;
    /**
     * Constructor de la clase VentanaAdministradorV2.
     * Configura la ventana principal del administrador, inicializa los componentes y define los eventos de los botones.
     *
     * @param nombre Nombre del usuario administrador que se mostrará en la ventana.
     */
    public VentanaAdministradorV2(String nombre) {
        setTitle("Usuario: "+nombre);
        setContentPane(pPincipal);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        Titulo.setText("¡Bienvenido " +nombre+"!");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        JMenuBar menuBar = new JMenuBar();

        JMenu Jusuario = new JMenu("Usuario");
        JMenu JotrasOpciones = new JMenu("Otras opciones");

        JMenuItem JcambiarUsuario = new JMenuItem("Cambiar de Usuario");
        JMenuItem Jsalir = new JMenuItem("Salir");

        Jusuario.add(JcambiarUsuario);
        JotrasOpciones.add(Jsalir);

        menuBar.add(Jusuario);
        menuBar.add(JotrasOpciones);

        setJMenuBar(menuBar);

        JcambiarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaSelccionUsuarioV2(VentanaAdministradorV2.this);
            }
        });

        Jsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        retroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaSelccionUsuarioV2(VentanaAdministradorV2.this);
            }
        });
        jugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaGestionJugadoresV2(VentanaAdministradorV2.this,nombre);
            }
        });
        equipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaGestionEquiposV2(VentanaAdministradorV2.this,nombre);
            }
        });
        jornadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaGestionJornadasV2(VentanaAdministradorV2.this,nombre);
            }
        });
        enfrentamientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaGestionEnfrentamientosV2(VentanaAdministradorV2.this,nombre);
            }
        });
        competicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaGestionCompeticionV2(VentanaAdministradorV2.this,nombre);
            }
        });


        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jugadores.setCursor(new Cursor(Cursor.HAND_CURSOR));
                equipos.setCursor(new Cursor(Cursor.HAND_CURSOR));
                enfrentamientos.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jornadas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                competicion.setCursor(new Cursor(Cursor.HAND_CURSOR));
                retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));

            }
        };
// Asigna el evento de cambio de cursor a los botones
        jugadores.addMouseListener(listener);
        equipos.addMouseListener(listener);
        jornadas.addMouseListener(listener);
        enfrentamientos.addMouseListener(listener);
        competicion.addMouseListener(listener);
        retroceder.addMouseListener(listener);
    }

}
