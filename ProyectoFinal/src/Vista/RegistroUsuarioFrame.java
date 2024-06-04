package Vista;

import javax.swing.*;
import Controlador.RegistroUsuarioController;
import Modelo.Usuario;

public class RegistroUsuarioFrame extends JFrame {
    private JTextField campoTipoID;
    private JTextField campoID;
    private JTextField campoNombres;
    private JTextField campoApellidos;
    private JTextField campoCorreo;
    private JTextField campoDireccion;
    private JTextField campoCiudad;
    private JTextField campoTelefono;
    private JPasswordField campoContrasena;
    private JPasswordField campoConfirmarContrasena;

    public RegistroUsuarioFrame() {
        super("Registro de usuario - MyHotel");

        JPanel panelRegistro = new JPanel();
        GroupLayout layout = new GroupLayout(panelRegistro);
        panelRegistro.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel etiquetaTipoID = new JLabel("Tipo de Identificación:");
        JLabel etiquetaID = new JLabel("Número de Identificación:");
        JLabel etiquetaNombres = new JLabel("Nombres:");
        JLabel etiquetaApellidos = new JLabel("Apellidos:");
        JLabel etiquetaCorreo = new JLabel("Correo Electrónico:");
        JLabel etiquetaDireccion = new JLabel("Dirección de Residencia:");
        JLabel etiquetaCiudad = new JLabel("Ciudad de Residencia:");
        JLabel etiquetaTelefono = new JLabel("Teléfono de Contacto:");
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        JLabel etiquetaConfirmarContrasena = new JLabel("Confirmar Contraseña:");
        campoTipoID = new JTextField(20);
        campoID = new JTextField(20);
        campoNombres = new JTextField(20);
        campoApellidos = new JTextField(20);
        campoCorreo = new JTextField(20);
        campoDireccion = new JTextField(20);
        campoCiudad = new JTextField(20);
        campoTelefono = new JTextField(20);
        campoContrasena = new JPasswordField(20);
        campoConfirmarContrasena = new JPasswordField(20);
        
        JButton botonRegistrar = new JButton("Registrar");

        botonRegistrar.addActionListener(e -> {
            String tipoID = campoTipoID.getText();
            String ID = campoID.getText();
            String nombres = campoNombres.getText();
            String apellidos = campoApellidos.getText();
            String correo = campoCorreo.getText();
            String direccion = campoDireccion.getText();
            String ciudad = campoCiudad.getText();
            String telefono = campoTelefono.getText();
            String contrasena = new String(campoContrasena.getPassword());
            String confirmarContrasena = new String(campoConfirmarContrasena.getPassword());
            if (RegistroUsuarioController.usuarioRegistrado(correo)) {
                JOptionPane.showMessageDialog(this, "Este usuario ya está registrado", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            } else {
                if (correo.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (!contrasena.equals(confirmarContrasena)) {
                        JOptionPane.showMessageDialog(this, "La confirmación de la contraseña es incorrecta", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Usuario nuevoUsuario = new Usuario(tipoID, ID, nombres, apellidos, correo, direccion, ciudad, telefono, contrasena);
                        RegistroUsuarioController.registrarUsuario(nuevoUsuario);
                        JOptionPane.showMessageDialog(this, "Usuario registrado satisfactoriamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new LoginFrame();
                    }
                }
            }
        });

        JButton botonInicioSesion = new JButton("Iniciar Sesión");
        botonInicioSesion.addActionListener(e -> {
            dispose();
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(etiquetaTipoID)
                .addComponent(etiquetaID)
                .addComponent(etiquetaNombres)
                .addComponent(etiquetaApellidos)
                .addComponent(etiquetaCorreo)
                .addComponent(etiquetaDireccion)
                .addComponent(etiquetaCiudad)
                .addComponent(etiquetaTelefono)
                .addComponent(etiquetaContrasena)
                .addComponent(etiquetaConfirmarContrasena));
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(campoTipoID)
                .addComponent(campoID)
                .addComponent(campoNombres)
                .addComponent(campoApellidos)
                .addComponent(campoCorreo)
                .addComponent(campoDireccion)
                .addComponent(campoCiudad)
                .addComponent(campoTelefono)
                .addComponent(campoContrasena)
                .addComponent(campoConfirmarContrasena)
                .addComponent(botonRegistrar)
                .addComponent(botonInicioSesion));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaTipoID)
                .addComponent(campoTipoID));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaID)
                .addComponent(campoID));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaNombres)
                .addComponent(campoNombres));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaApellidos)
                .addComponent(campoApellidos));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaCorreo)
                .addComponent(campoCorreo));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaDireccion)
                .addComponent(campoDireccion));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaCiudad)
                .addComponent(campoCiudad));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaTelefono)
                .addComponent(campoTelefono));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaContrasena)
                .addComponent(campoContrasena));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaConfirmarContrasena)
                .addComponent(campoConfirmarContrasena));
        vGroup.addComponent(botonRegistrar);
        vGroup.addComponent(botonInicioSesion);
        layout.setVerticalGroup(vGroup);

        add(panelRegistro);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}