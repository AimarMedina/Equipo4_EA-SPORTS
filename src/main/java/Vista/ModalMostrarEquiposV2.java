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
 * Clase que representa un cuadro de diálogo para mostrar la lista de equipos.
 */
public class ModalMostrarEquiposV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTable tablaEquipos;
    /**
     * Constructor de la clase ModalMostrarEquiposV2.
     * Configura el cuadro de diálogo, inicializa los componentes y define los eventos de los botones.
     */
    public ModalMostrarEquiposV2() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(400,400);
        setResizable(false);
        setLocationRelativeTo(null);

        setTitle("Lista de Equipos");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        // Configurar columnas
        String[] columnas = {"Nombre", "Fecha de Fundación"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Obtener lista de equipos desde el controlador
        List<String[]> listaEquipos = VistaController.obtenerEquiposConFechas();

        // Rellenar tabla
        for (String[] fila : listaEquipos) {
            modelo.addRow(fila);
        }

        tablaEquipos.setModel(modelo);
        tablaEquipos.setEnabled(false); // Para que no se pueda editar
        // Habilitar el ordenamiento de las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaEquipos.getColumnCount(); i++) {
            tablaEquipos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        tablaEquipos.setRowSorter(sorter);

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
