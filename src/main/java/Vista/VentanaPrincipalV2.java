package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class VentanaPrincipalV2 extends JFrame {
    private JButton button1;
    private JButton comenzarButton;
    private JPanel pPrincipal;

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
                VistaController.VentanaSelccionUsuarioV2(VentanaPrincipalV2.this);
            }
        });
    }

    public static void main(String[] args) {
        VentanaPrincipalV2 ventana = new VentanaPrincipalV2();
        ventana.setVisible(true);
    }
}
