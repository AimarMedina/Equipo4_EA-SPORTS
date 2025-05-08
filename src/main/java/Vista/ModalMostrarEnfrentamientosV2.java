package Vista;

import Controlador.VistaController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;
/**
 * Clase que representa un cuadro de diálogo para mostrar la lista de enfrentamientos.
 */
public class ModalMostrarEnfrentamientosV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTable tablaJugadores;
    /**
     * Constructor de la clase ModalMostrarEnfrentamientosV2.
     * Configura el cuadro de diálogo, inicializa los componentes y define los eventos de los botones.
     */
    public ModalMostrarEnfrentamientosV2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500,300);
        setResizable(false);
        setLocationRelativeTo(null);

        setTitle("Lista de Enfrentamientos");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        // Configurar columnas
        String[] columnas = {"Enfrentamiento","Fecha","Hora","Ganador","Perdedor","Jornada"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Obtener lista de equipos desde el controlador
        List<String[]> listaEnfrentamientos = VistaController.obtenerEnfrentamientos();

        // Rellenar tabla
        for (String[] fila : listaEnfrentamientos) {
            modelo.addRow(fila);
        }

        tablaJugadores.setModel(modelo);
        tablaJugadores.setEnabled(false); // Para que no se pueda editar
        // Centra el contenido de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaJugadores.getColumnCount(); i++) {
            tablaJugadores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Habilita el ordenamiento de las filas
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tablaJugadores.setRowSorter(sorter);
        // Configura el evento del botón "OK"
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });



        // Configura el comportamiento al cerrar la ventana con el botón de cerrar
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Configura el comportamiento al presionar la tecla ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    /**
     * Acción ejecutada al presionar el botón "OK".
     * Cierra el cuadro de diálogo.
     */
    private void onOK() {
        dispose();
    }
    /**
     * Acción ejecutada al presionar el botón de cerrar o la tecla ESCAPE.
     * Cierra el cuadro de diálogo.
     */
    private void onCancel() {
        dispose();
    }


}
