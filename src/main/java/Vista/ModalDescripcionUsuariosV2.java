package Vista;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class ModalDescripcionUsuariosV2 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JList<String> descripcion;
    private DefaultListModel<String> modelo = new DefaultListModel<>();

    public ModalDescripcionUsuariosV2(String usr) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Descripcion de usuario: " + usr);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());

        String[] opcionesUsr = {"1. Ver informe de equipos en competición","2. Ver resultados de la ultima jornada"};
        String[] opcionesAdmin = {"1. Gestionar Jugadores","2.Gestionar Equipos","3. Gestionar Enfrentamientos","4. Gestionar Jorandas","5. Gestionar Competición"};



        if (usr.equals("Administrador")){
            setSize(400,275);
            setLocationRelativeTo(null);
            for(String op : opcionesAdmin){
                modelo.addElement(op);
            }
            descripcion.setModel(modelo);
        }else if(usr.equals("Usuario")){
            setSize(400,225);
            setLocationRelativeTo(null);
            for(String op : opcionesUsr){
                modelo.addElement(op);
            }
            descripcion.setModel(modelo);
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
        modelo.removeAllElements();
        dispose();
    }

    private void onCancel() {
        modelo.removeAllElements();
        dispose();
    }

    public static void main(String[] args) {
        ModalDescripcionUsuariosV2 dialog = new ModalDescripcionUsuariosV2("Administrador");
        dialog.setVisible(true);
        System.exit(0);
    }
}
