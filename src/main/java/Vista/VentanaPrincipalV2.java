package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
/**
 * Clase que representa la ventana principal de la aplicación.
 */
public class VentanaPrincipalV2 extends JFrame {
    private JButton button1;
    private JButton comenzarButton;
    private JPanel pPrincipal;
    /**
     * Constructor de la clase VentanaPrincipalV2.
     * Configura la ventana principal, inicializa los componentes y define los eventos de los botones.
     */
    public VentanaPrincipalV2() {
        setContentPane(pPrincipal);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ventana Principal");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());
        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (VistaController.verificarCompeticionCreada() >0){
                    VistaController.VentanaSelccionUsuarioV2(VentanaPrincipalV2.this);
                }
                else {
                    VistaController.abrirCompeticion();
                    VistaController.VentanaSelccionUsuarioV2(VentanaPrincipalV2.this);
                }
            }
        });
    }
}
