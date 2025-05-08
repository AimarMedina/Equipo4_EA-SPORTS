package Vista;

import Controlador.VistaController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;

public class ModalMostrarJugadoresV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTable tablaJugadores;

    public ModalMostrarJugadoresV2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(900,400);
        setResizable(false);
        setLocationRelativeTo(null);

        setTitle("Lista de Jugadores");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        // Configurar columnas
        String[] columnas = {"Nombre", "Apellido","Nacionalidad","FechaNacimiento","Nickname","Sueldo","Rol","Equipo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Obtener lista de equipos desde el controlador
        List<String[]> listaJugadores = VistaController.obtenerJugadores();

        // Rellenar tabla
        for (String[] fila : listaJugadores) {
            modelo.addRow(fila);
        }

        tablaJugadores.setModel(modelo);
        tablaJugadores.setEnabled(false); // Para que no se pueda editar

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaJugadores.getColumnCount(); i++) {
            tablaJugadores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tablaJugadores.setRowSorter(sorter);

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
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ModalMostrarJugadoresV2 dialog = new ModalMostrarJugadoresV2();
        dialog.setVisible(true);
        System.exit(0);
    }
}
