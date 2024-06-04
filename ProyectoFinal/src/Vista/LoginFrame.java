package Vista;

import javax.swing.*;
import Controlador.LoginController;

public class LoginFrame extends JFrame {
    private JTextField campoCorreo;
    private JPasswordField campoContrasena;

    public LoginFrame() {
        super("Login - MyHotel");

        JPanel panelLogin = new JPanel();
        GroupLayout layout = new GroupLayout(panelLogin);
        panelLogin.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoCorreo = new JTextField(20);
        campoContrasena = new JPasswordField(20);
        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        JButton botonRegistro = new JButton("Registrarse");

        botonIniciarSesion.addActionListener(e -> {
            String correo = campoCorreo.getText();
            String contrasena = new String(campoContrasena.getPassword());
            if (correo.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Por favor, complete todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            } else {
                if (correo.equals("admin") && contrasena.equals("admin")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Inicio de sesión como Administrador", "¡Bienvenido!", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new AdminFrame();
                } else {
                    boolean acceso = LoginController.iniciarSesion(correo, contrasena);
                    if (acceso) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Inicio de sesión exitoso", "¡Bienvenido!", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new InicioFrame();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Credenciales incorrectas", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        botonRegistro.addActionListener(e -> {
            dispose(); 
            RegistroUsuarioFrame registroFrame = new RegistroUsuarioFrame();
            registroFrame.setVisible(true);
        });

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(etiquetaCorreo)
                        .addComponent(etiquetaContrasena))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(campoCorreo)
                        .addComponent(campoContrasena)))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(botonIniciarSesion)
                    .addComponent(botonRegistro))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaCorreo)
                    .addComponent(campoCorreo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaContrasena)
                    .addComponent(campoContrasena))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(botonIniciarSesion)
                    .addComponent(botonRegistro))
        );

        add(panelLogin);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}