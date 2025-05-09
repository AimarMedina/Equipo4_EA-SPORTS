package Vista;

import Excepciones.CampoObligatorioException;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Clase que representa un cuadro de diálogo para seleccionar el equipo ganador y perdedor de una jornada.
 */
public class ModalSeleccionGanadroV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JRadioButton Equipo1;
    private JRadioButton Equipo2;
    private JLabel titulo;
    private JButton buttonCancel;

    private String ganador;
    private String perdedor;
    private List<String> equiposGandor_Perdedor = new ArrayList<>();

    /**
     * Constructor de la clase ModalSeleccionGanadroV2.
     * Configura el cuadro de diálogo, inicializa los componentes y define los eventos de los botones.
     *
     * @param equiposJugando Lista con los nombres de los equipos que están jugando.
     * @param numJor Número de la jornada actual.
     * @return Lista con el equipo ganador y el equipo perdedor.
     */
    public List<String> ModalSeleccionGanadroV2(List<String> equiposJugando,int numJor) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500,200);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(false);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        Equipo1.setSelected(true);

        titulo.setText("Seleccion de ganadores para la jornada: "+numJor);

        Equipo1.setText(equiposJugando.get(0));
        Equipo2.setText(equiposJugando.get(1));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
                dispose();
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
        return equiposGandor_Perdedor;
    }
    /**
     * Acción ejecutada al presionar el botón "OK".
     * Determina el equipo ganador y el perdedor según la selección realizada.
     *
     * @return Lista con el equipo ganador y el equipo perdedor.
     */
    private List<String> onOK() {


            if (Equipo1.isSelected()){
                ganador = Equipo1.getText();
                perdedor = Equipo2.getText();
                equiposGandor_Perdedor.add(ganador);
                equiposGandor_Perdedor.add(perdedor);
            }
            else{
                ganador = Equipo2.getText();
                perdedor = Equipo1.getText();
                equiposGandor_Perdedor.add(ganador);
                equiposGandor_Perdedor.add(perdedor);
            }
            return equiposGandor_Perdedor;
    }

    /**
     * Acción ejecutada al presionar el botón de cerrar o la tecla ESCAPE.
     * Cierra el cuadro de diálogo.
     */
    private void onCancel() {
        dispose();
    }

    /**
     * Obtiene la lista con el equipo ganador y el equipo perdedor.
     *
     * @return Lista con el equipo ganador y el equipo perdedor.
     */
    public List<String> getGanadorPerdedor() {
        return equiposGandor_Perdedor;
    }

}
