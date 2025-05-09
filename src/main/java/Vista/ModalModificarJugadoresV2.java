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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModalModificarJugadoresV2 extends JDialog {
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
    private String nickname_viejo;
    private List<String> listaEquipos = new ArrayList<>();


    public ModalModificarJugadoresV2(String jugador) {
        setContentPane(pPrincipal);
        setModal(true);
        setTitle("Inscripcion Jugdador");
        setContentPane(pPrincipal);

        datosJugador.setVisible(false);
        datosEquipo.setVisible(false);

        nickname_viejo = jugador;

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

        listaEquipos = VistaController.listaEquipos();
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

            boolean duplicado = false;
            if (nickname_viejo.equals(nickname)) {
                if (VistaController.buscarNickname(nickname)) {
                    throw new NombreDuplicadoExcepcion("El nickname ya existe");
                }
                duplicado = true;
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

            if (VistaController.obtenerCantidadJugadoreEquipo(equipo) >= 6){
                throw new Exception("Este equipo ya tiene 6 jugadores");
            }
            String rol = (String) roles.getSelectedItem();
            if (roles.getSelectedIndex() == 0) {
                throw new CampoObligatorioException("El rol es un campo obligatorio");
            }

            String rol_viejo = VistaController.obtenerRolJugadorNick(nickname_viejo);

            if (VistaController.modificarJugador(nombre, apellido, nacionalidad, fecha, nickname, sueldoFloat, rol, equipo, duplicado,nickname_viejo)) {
                JOptionPane.showMessageDialog(pPrincipal, "Se ha modificado correctamente el jugador", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                switch (JOptionPane.showConfirmDialog(pPrincipal, "Desea Modificar otro jugador?", "Pregunta", JOptionPane.YES_NO_OPTION)) {
                    case 0:
                        VistaController.ModalSeleccionarJugadorV2();
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
        } catch (FormatoIncorrectoException e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR: " + e.getMessage(), "ERROR", -1);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(pPrincipal, "Error: Fecha con formato invalido", "ERROR", -1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pPrincipal, "ERROR inesperado: " + e.getMessage(), "ERROR", -1);
        }
    }
    public static List<String> actualizarRoles(String equipo) {
        return VistaController.obtenerRoles(equipo);
    }
}
