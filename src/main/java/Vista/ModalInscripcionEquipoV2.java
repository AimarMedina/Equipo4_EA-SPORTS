package Vista;

import Controlador.VistaController;
import Excepciones.CampoObligatorioException;
import Excepciones.EquipoExistenteException;
import Excepciones.FechaInvalidaException;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
/**
 * Clase que representa un cuadro de diálogo para la inscripción de equipos.
 */
public class ModalInscripcionEquipoV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel datosPersonales;
    private JTextField fecha;
    private JTextField nombre;

    private LocalDate fechaParseada;
    private LocalDate fechaMax = LocalDate.now();
    private LocalDate fechaMin = LocalDate.parse("02/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    /**
     * Constructor de la clase ModalInscripcionEquipoV2.
     * Configura el cuadro de diálogo, inicializa los componentes y define los eventos de los botones.
     */
    public ModalInscripcionEquipoV2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(350, 250);
        setLocationRelativeTo(null);
        setTitle("Inscripcion de Equipo");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
     * Valida los datos ingresados, verifica si el equipo ya existe y realiza la inscripción si es válida.
     * Muestra mensajes de error en caso de datos incorrectos.
     */
    private void onOK() {
        fechaParseada = LocalDate.parse(fecha.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        try{
            if(VistaController.buscarEquipo(nombre.getText())){
                throw new EquipoExistenteException();
            }
            if (nombre.getText().isEmpty()) {
                throw new CampoObligatorioException("Este campo es obligatorio");
            }


            if (fechaParseada.isEqual(fechaMin) || fechaParseada.isEqual(fechaMax) || fechaParseada.isBefore(fechaMin) || fechaParseada.isAfter(fechaMax)) {
                throw new FechaInvalidaException();
            }
            VistaController.inscribirEquipo(nombre.getText(), fechaParseada);
            JOptionPane.showMessageDialog(contentPane, "El Equipo se ha ingresado correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            dispose();

        }catch(EquipoExistenteException e){
            JOptionPane.showMessageDialog(contentPane, "Ya existe un Equipo con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(CampoObligatorioException e){
            JOptionPane.showMessageDialog(contentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (FechaInvalidaException e){
            JOptionPane.showMessageDialog(contentPane,"Error, la fecha de fundacion no es correcta", "Error", JOptionPane.ERROR_MESSAGE);
        }catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(contentPane,"Error, formato de fecha incorrecto pruebe con (dd/mm/yyyy)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Acción ejecutada al presionar el botón "Cancelar" o cerrar el cuadro de diálogo.
     * Cierra el cuadro de diálogo.
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
