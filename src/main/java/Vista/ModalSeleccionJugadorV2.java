package Vista;

import Controlador.VistaController;
import Excepciones.CampoObligatorioException;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModalSeleccionJugadorV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox comboBox1;

    public ModalSeleccionJugadorV2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);

        setTitle("Selecciona un jugador para modificar");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());
        List<String> jugadores = VistaController.listaNicknames();

        for (int i = 0; i < jugadores.size(); i++) {
            comboBox1.addItem(jugadores.get(i));
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try{
            if (comboBox1.getSelectedIndex() != 0) {
                VistaController.ModalModificarJugadorV2(ModalSeleccionJugadorV2.this,comboBox1.getSelectedItem().toString());
                dispose();
            }
            else {
                throw new CampoObligatorioException("Error: Debes seleccionar un jugador para poder modificarlo");
            }

        }catch (CampoObligatorioException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ModalSeleccionJugadorV2 dialog = new ModalSeleccionJugadorV2();
        dialog.setVisible(true);
        System.exit(0);
    }
}
