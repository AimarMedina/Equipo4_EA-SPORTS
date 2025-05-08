package Vista;

import Controlador.VistaController;
import Excepciones.CampoObligatorioException;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;
/**
 * Clase que representa la ventana de inicio de sesión.
 */
public class VentanaIniciarSesionV2 extends JFrame {
    private JPanel pPrincipal;
    private JLabel Titulo;
    private JLabel SubTitulo;
    private JTextField nombre;
    private JPasswordField contrasena;
    private JButton iniciarSesiónButton;
    /**
     * Constructor de la clase VentanaIniciarSesionV2.
     * Configura la ventana de inicio de sesión, inicializa los componentes y define los eventos de los botones.
     *
     * @param tipoUsuario Tipo de usuario que está iniciando sesión (Administrador o Usuario).
     */
    public VentanaIniciarSesionV2(String tipoUsuario) {
        setTitle("Iniciando sesión como "+ tipoUsuario);
        setContentPane(pPrincipal);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("FaviconEA.png")));
        setIconImage(icon.getImage());



        Titulo.setText("Iniciando sesión como "+ tipoUsuario);
        SubTitulo.setText("Por favor introduzca los datos del "+tipoUsuario.toLowerCase()+" para iniciar sesion.");


        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion(tipoUsuario);
            }
        });
        contrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    iniciarSesion(tipoUsuario);
                }
            }
        });
    }
    /**
     * Método que gestiona el proceso de inicio de sesión.
     * Valida los campos obligatorios y verifica las credenciales del usuario.
     *
     * @param tipoUsuario Tipo de usuario que está iniciando sesión (Administrador o Usuario).
     */
    public void iniciarSesion(String tipoUsuario) {
        String passw = new String(contrasena.getPassword());
        try {
            if (nombre.equals("")){
                throw new CampoObligatorioException("Error: El campo nombre es obligatorio!");
            }
            if (contrasena.equals("")) {
                throw new CampoObligatorioException("Error: El campo contraseña el obligatorio!");
            }
            else{
                if (VistaController.inciarSesionUsuario(nombre.getText(),passw,tipoUsuario)){
                    switch (tipoUsuario){
                        case "Adminstrador":
                            VistaController.VentanaAdministradorV2(nombre.getText(),VentanaIniciarSesionV2.this);
                            break;
                        case "Usuario":
                            VistaController.VentanaUsuarioV2(nombre.getText(),VentanaIniciarSesionV2.this);
                            break;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error: Contraseña o Usuario incorrectos","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (CampoObligatorioException u){
            JOptionPane.showMessageDialog(null,u.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}

