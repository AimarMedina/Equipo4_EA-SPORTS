package Vista;

import Controlador.VistaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class VentanaSeleccionUsuarioV2 extends JFrame{
    private JButton usuario;
    private JButton administrador;
    private JButton helpUsuario;
    private JButton helpAdministrador;
    private JPanel pPrincipal;

    public VentanaSeleccionUsuarioV2() {

        setContentPane(pPrincipal);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("¡Bienvenido!");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        usuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        administrador.setCursor(new Cursor(Cursor.HAND_CURSOR));

        helpUsuario.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Icon icon = helpUsuario.getIcon();
                if (icon != null) {
                    int iconWidth = icon.getIconWidth();
                    int iconHeight = icon.getIconHeight();
                    int x = e.getX();
                    int y = e.getY();

                    // Centrado del icono dentro del botón
                    int offsetX = (helpUsuario.getWidth() - iconWidth) / 2;
                    int offsetY = (helpUsuario.getHeight() - iconHeight) / 2;

                    if (x >= offsetX && x <= offsetX + iconWidth &&
                            y >= offsetY && y <= offsetY + iconHeight) {
                        helpUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        helpUsuario.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        });

        helpAdministrador.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Icon icon = helpAdministrador.getIcon();
                if (icon != null) {
                    int iconWidth = icon.getIconWidth();
                    int iconHeight = icon.getIconHeight();
                    int x = e.getX();
                    int y = e.getY();

                    // Centrado del icono dentro del botón
                    int offsetX = (helpAdministrador.getWidth() - iconWidth) / 2;
                    int offsetY = (helpAdministrador.getHeight() - iconHeight) / 2;

                    if (x >= offsetX && x <= offsetX + iconWidth &&
                            y >= offsetY && y <= offsetY + iconHeight) {
                        helpAdministrador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    } else {
                        helpAdministrador.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        });
        helpUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalDescripcionUsuariosV2("Usuario");
            }
        });
        helpAdministrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.ModalDescripcionUsuariosV2("Administrador");
            }
        });
        usuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaInciarSesionV2(usuario.getText(), VentanaSeleccionUsuarioV2.this);
            }
        });

        administrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaController.VentanaInciarSesionV2(administrador.getText(), VentanaSeleccionUsuarioV2.this);
            }
        });
    }

}
