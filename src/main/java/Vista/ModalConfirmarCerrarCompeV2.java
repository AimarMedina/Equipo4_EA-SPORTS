package Vista;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;
/**
 * Clase que representa un cuadro de diálogo de confirmación para cerrar una competición.
 */
public class ModalConfirmarCerrarCompeV2 extends JDialog {
    private JPanel pPrincipal;
    private JButton button1;
    private JButton eliminarButton;
    private JButton cancelarButton;
    private boolean confirmado = false;
    /**
     * Constructor de la clase ModalConfirmarCerrarCompeV2.
     * Configura el contenido del cuadro de diálogo, establece su diseño y define los eventos de los botones.
     */
    public ModalConfirmarCerrarCompeV2() {
        setContentPane(pPrincipal);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(eliminarButton);
        setTitle("¡WARNING!");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmado = true;
                dispose();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        pPrincipal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }
    /**
     * Verifica si la acción fue confirmada.
     *
     * @return true si se confirmó la acción, false en caso contrario.
     */
    public boolean isConfirmado() {
        return confirmado;
    }
}
