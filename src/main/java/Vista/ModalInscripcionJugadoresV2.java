package Vista;

import Controlador.VistaController;
import Excepciones.CampoObligatorioException;
import Excepciones.FormatoIncorrectoException;
import Excepciones.NombreDuplicadoExcepcion;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
/**
 * Clase que representa un cuadro de diálogo para la inscripción de jugadores.
 */
public class ModalInscripcionJugadoresV2 extends JDialog {
    private JPanel pPrincipal;
    private JPanel datosPersonales;
    private JTextField apellidoTf;
    private JTextField nacionalidadTf;
    private JTextField fechaTF;
    private JTextField nombreTF;
    private JPanel datosJugador;
    private JTextField nicknameTF;
    private JTextField sueldoTF;
    private JPanel datosEquipo;
    private JComboBox equipos;
    private JComboBox roles;
    private JButton atrásButton;
    private JButton enviarButton;
    private JButton siguienteButton;
    private JButton cancelar;

    /**
     * Constructor de la clase ModalInscripcionJugadoresV2.
     * Configura el cuadro de diálogo, inicializa los componentes y define los eventos de los botones.
     */
    public ModalInscripcionJugadoresV2() {
        setContentPane(pPrincipal);
        setModal(true);
        setTitle("Inscripcion Jugdador");
        setContentPane(pPrincipal);

        datosJugador.setVisible(false);
        datosEquipo.setVisible(false);

        if (datosJugador.isVisible()) {
            setSize(350, 300);
        }
        if (datosPersonales.isVisible()) {
            atrásButton.setEnabled(false);
            setSize(350, 300);
        }
        if (datosEquipo.isVisible()) {
            siguienteButton.setEnabled(false);
            setSize(350, 325);
        }
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());





        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datosPersonales.isVisible()) {
                    datosPersonales.setVisible(false);
                    datosJugador.setVisible(true);
                    atrásButton.setEnabled(true);
                }
            }
        });
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datosJugador.isVisible()) {
                    datosJugador.setVisible(false);
                    datosEquipo.setVisible(true);
                    atrásButton.setEnabled(true);
                    siguienteButton.setEnabled(false);
                }
            }
        });
        atrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datosJugador.isVisible()) {
                    datosJugador.setVisible(false);
                    datosPersonales.setVisible(true);
                    atrásButton.setEnabled(false);

                }
                if (datosEquipo.isVisible()) {
                    datosEquipo.setVisible(false);
                    datosJugador.setVisible(true);
                    siguienteButton.setEnabled(true);
                }
            }
        });

        List<String> listaEquipos = VistaController.listaEquipos();
        for (String equipo : listaEquipos){
            equipos.addItem(equipo);
        }
        roles.setEnabled(false);

        equipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equipos.getSelectedIndex() != 0) {
                    roles.setEnabled(true);
                    roles.removeAllItems();
                    roles.insertItemAt("Haz click para descubrir los roles",0);
                    roles.setSelectedIndex(0);
                    List<String> listaRoles = actualizarRoles(equipos.getSelectedItem().toString());
                    for (String role : listaRoles) {
                        roles.addItem(role);
                    }
                }
                else{
                    roles.setEnabled(false);
                    roles.removeAllItems();
                    roles.insertItemAt("Haz click para descubrir los roles",0);
                    roles.setSelectedIndex(0);
                }
            }
        });


        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    /**
     * Acción ejecutada al presionar el botón "Enviar".
     * Valida los datos ingresados, verifica duplicados y realiza la inscripción si es válida.
     * Muestra mensajes de error en caso de datos incorrectos.
     */
    private void onOK() {
        try {
            String nombre = nombreTF.getText();
            if (nombre.isEmpty()) {
                throw new CampoObligatorioException("El nombre es un campo obligatorio");
            }
            String apellido = apellidoTf.getText();
            if (apellido.isEmpty()) {
                throw new CampoObligatorioException("El apellido es un campo obligatorio");
            }
            String nacionalidad = nacionalidadTf.getText();
            if (nacionalidad.isEmpty()) {
                throw new CampoObligatorioException("La nacionalidad es un campo obligatorio");
            }


            LocalDate fecha;
            if (fechaTF.getText().isEmpty()) {
                throw new CampoObligatorioException("La fecha es un campo obligatorio");
            }

            fecha = LocalDate.parse(fechaTF.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));


            int añonacimiento = fecha.getYear();
            int añoactual = LocalDate.now().getYear();
            if (añoactual - añonacimiento < 13) {
                throw new FormatoIncorrectoException("El jugador debe de tener 13 años como mínimo");
            }


            String nickname = nicknameTF.getText();
            if (nickname.isEmpty()) {
                throw new CampoObligatorioException("El nickname es un campo obligatorio");
            }

            if (VistaController.buscarNickname(nickname)) {
                throw new NombreDuplicadoExcepcion("El nickname ya existe");
            }
            String sueldo = sueldoTF.getText();
            if (sueldo.isEmpty()) {
                throw new CampoObligatorioException("El sueldo es un campo obligatorio");
            }

            float sueldoFloat;
            try {
                sueldoFloat = Float.parseFloat(sueldo);
            } catch (NumberFormatException e) {
                throw new FormatoIncorrectoException("El sueldo debe ser un número válido");
            }

            if (sueldoFloat < 1184) {
                throw new FormatoIncorrectoException("El sueldo debe ser igual o superior al SMI (1184€)");
            }

            String equipo = equipos.getSelectedItem().toString();
            if (equipos.getSelectedIndex() == 0) {
                throw new CampoObligatorioException("Debes seleccionar un equipo");
            }
            String rol = (String) roles.getSelectedItem();
            if (roles.getSelectedIndex() == 0) {
                throw new CampoObligatorioException("El rol es un campo obligatorio");
            }

            if (VistaController.inscribirJugador(nombre, apellido, nacionalidad, fecha, nickname, sueldoFloat, rol, equipo)) {
                JOptionPane.showMessageDialog(pPrincipal, "Se ha inscrito correctamente el jugador", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                switch (JOptionPane.showConfirmDialog(pPrincipal, "Desea inscribir otro jugador?", "Pregunta", JOptionPane.YES_NO_OPTION)) {
                    case 0:
                        nombreTF.setText(null);
                        fechaTF.setText(null);
                        nicknameTF.setText(null);
                        sueldoTF.setText(null);
                        roles.setSelectedIndex(0);
                        nacionalidadTf.setText(null);
                        apellidoTf.setText(null);
                        equipos.setSelectedIndex(0);
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
        } catch (FormatoIncorrectoException e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR: " + e.getMessage(), "ERROR", -1);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(pPrincipal, "Error: Fecha con formato invalido", "ERROR", -1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR inesperado: " + e.getMessage(), "ERROR", -1);
        }
    }
    /**
     * Actualiza los roles disponibles según el equipo seleccionado.
     *
     * @param equipo Nombre del equipo seleccionado.
     * @return Lista de roles disponibles para el equipo.
     */
    public static List<String> actualizarRoles(String equipo) {
        return VistaController.obtenerRoles(equipo);
    }
}
