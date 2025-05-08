package Vista;

import Controlador.VistaController;
import Excepciones.*;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class ModalModificarEquipoV2 extends JDialog {
    private JPanel pPrincipal;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel datosPersonales;
    private JTextField fecha;
    private JTextField nombre;
    private String nombreViejo;

    private LocalDate fechaParseada;
    private LocalDate fechaMax = LocalDate.now();
    private LocalDate fechaMin = LocalDate.parse("02/06/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    public ModalModificarEquipoV2(String equipo) {
        setContentPane(pPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(350, 250);
        setLocationRelativeTo(null);
        nombreViejo = equipo;

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
        pPrincipal.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            if (nombre.getText().isEmpty()) {
                throw new CampoObligatorioException("El nombre es un campo obligatorio");
            }

            LocalDate fechaParseada = LocalDate.parse(fecha.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (fechaParseada.toString().isEmpty()) {
                throw new CampoObligatorioException("La fecha es un campo obligatorio");
            }

            boolean duplicado = false;
            if (!nombreViejo.equals(nombre)) {
                if (VistaController.buscarEquipo(nombre.getText())) {
                    throw new NombreDuplicadoExcepcion("El nombre ya existe");
                }
                duplicado = true;
            }

            if (VistaController.modificarEquipo(nombre.getText(),fechaParseada,duplicado,nombreViejo)) {
                JOptionPane.showMessageDialog(pPrincipal, "Se ha modificado correctamente el equipo", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                switch (JOptionPane.showConfirmDialog(pPrincipal, "Desea Modificar otro equipo?", "Pregunta", JOptionPane.YES_NO_OPTION)) {
                    case 0:
                        VistaController.ModalSeleccionarEquipoV2();
                        dispose();
                        break;
                    default:
                        dispose();
                        break;
                }
            }
        } catch (CampoObligatorioException e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR: " + e.getMessage(), "ERROR", -1);
        } catch (NombreDuplicadoExcepcion e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR: " + e.getMessage(), "ERROR", -1);
        }  catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(pPrincipal, "Error: Fecha con formato invalido", "ERROR", -1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR inesperado: " + e.getMessage(), "ERROR", -1);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
