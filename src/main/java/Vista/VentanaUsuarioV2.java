package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class VentanaUsuarioV2 extends javax.swing.JFrame {
    /**
     * Clase que representa la ventana principal para los usuarios.
     */
    private JPanel panel1;
    private JLabel Titulo;
    private JButton mostrarInformeEquipos;
    private JButton mostrarInformeUltimaJornada;
    private JButton retroceder;
    /**
     * Constructor de la clase VentanaUsuarioV2.
     * Configura la ventana principal del usuario, inicializa los componentes y define los eventos de los botones.
     *
     * @param nombre Nombre del usuario que se mostrará en la ventana.
     */
    public VentanaUsuarioV2(String nombre) {
        setContentPane(panel1);
        pack();
        setTitle("¡Bienvenido "+nombre+"!");
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ventana Principal");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());


        retroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaSelccionUsuarioV2(VentanaUsuarioV2.this);
            }
        });
        mostrarInformeEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalMostrarEquiposV2();
            }
        });
        mostrarInformeUltimaJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalMostrarJornadasV2();
            }
        });


        mostrarInformeEquipos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                mostrarInformeEquipos.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        retroceder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        mostrarInformeUltimaJornada.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                mostrarInformeUltimaJornada.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }
}
