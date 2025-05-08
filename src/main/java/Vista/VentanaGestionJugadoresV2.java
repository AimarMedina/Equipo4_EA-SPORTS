package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class VentanaGestionJugadoresV2 extends JFrame {
    private JButton mostrarJugadores;
    private JButton retroceder;
    private JButton inscribirJugador;
    private JButton eliminarJugador;
    private JButton modificarJugador;
    private JPanel pPrincipal;

    public VentanaGestionJugadoresV2(String nombre) {

        setContentPane(pPrincipal);
        setTitle("Gestion de Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        //Deshabilitar inscribir-modificar-eliminar jugador si la competicion esta cerrada
        if (!VistaController.estadoCompeticion()) {
            inscribirJugador.setEnabled(false);
            modificarJugador.setEnabled(false);
            eliminarJugador.setEnabled(false);
            JOptionPane.showMessageDialog(null, "La competición ya esta cerrada. No se pueden gestionar jugadores, unicamente mostrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        retroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaAdministradorV2(nombre,VentanaGestionJugadoresV2.this);
            }
        });
        retroceder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        mostrarJugadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                mostrarJugadores.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        inscribirJugador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inscribirJugador.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        eliminarJugador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                eliminarJugador.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        modificarJugador.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                modificarJugador.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        //ACCIONES
        inscribirJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalInscripcionJugadoresV2();
            }
        });
        eliminarJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalEliminacionJugadoresV2();
            }
        });
        modificarJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalSeleccionarJugadorV2();
            }
        });
        mostrarJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalMostrarJugadoresV2();
            }
        });

    }


}
