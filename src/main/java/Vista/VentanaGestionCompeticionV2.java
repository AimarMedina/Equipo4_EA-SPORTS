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
 * Clase que representa la ventana de gestión de la competición.
 */
public class VentanaGestionCompeticionV2 extends JFrame{
    private JPanel panel1;
    private JButton abrirCompe;
    private JButton retroceder;
    private JButton cerrarCompe;
    /**
     * Constructor de la clase VentanaGestionCompeticionV2.
     * Configura la ventana de gestión de la competición, inicializa los componentes y define los eventos de los botones.
     *
     * @param nombre Nombre del usuario administrador que se mostrará en la ventana.
     */
    public VentanaGestionCompeticionV2(String nombre) {
        setContentPane(panel1);
        setTitle("Gestion de Competición");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        boolean compeAbierta = VistaController.estadoCompeticion();

        if (VistaController.verificarCompeticionCreada() > 0){
            if (compeAbierta){
                abrirCompe.setEnabled(false);
            }
            else {
                cerrarCompe.setEnabled(false);
            }
            cerrarCompe.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (VistaController.ModalConfirmarCerrarCompeV2()){
                        VistaController.cerrarCompeticion();
                    }
                }
            });
        }
        else {
            abrirCompe.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VistaController.abrirCompeticion();
                }
            });
        }


        abrirCompe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                abrirCompe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        cerrarCompe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                cerrarCompe.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        retroceder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        retroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaAdministradorV2(nombre,VentanaGestionCompeticionV2.this);
            }
        });
    }
}
