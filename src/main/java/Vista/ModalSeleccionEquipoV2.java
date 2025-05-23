package Vista;

import Controlador.VistaController;
import Excepciones.CampoObligatorioException;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Clase que representa un cuadro de diálogo para seleccionar un equipo a modificar.
 */
public class ModalSeleccionEquipoV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox comboBox1;
    /**
     * Constructor de la clase ModalSeleccionEquipoV2.
     * Configura el cuadro de diálogo, inicializa los componentes y define los eventos de los botones.
     */
    public ModalSeleccionEquipoV2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);

        setTitle("Selecciona un equipo para modificar");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        List<String> equipos = VistaController.listaEquipos();

        for (int i = 0; i < equipos.size(); i++) {
            comboBox1.addItem(equipos.get(i));
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
    /**
     * Acción ejecutada al presionar el botón "OK".
     * Verifica que se haya seleccionado un equipo y abre el cuadro de diálogo para modificarlo.
     * Muestra un mensaje de error si no se selecciona un equipo.
     */
    private void onOK() {
        try{
            if (comboBox1.getSelectedIndex() != 0) {
                VistaController.ModalModificarEquipoV2(ModalSeleccionEquipoV2.this,comboBox1.getSelectedItem().toString());
                dispose();
            }
            else {
                throw new CampoObligatorioException("Error: Debes seleccionar un equipo para poder modificarlo");
            }

        }catch (CampoObligatorioException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }
    /**
     * Acción ejecutada al presionar el botón de cerrar o la tecla ESCAPE.
     * Cierra el cuadro de diálogo.
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
