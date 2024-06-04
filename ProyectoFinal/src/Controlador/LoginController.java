package Controlador;

import Modelo.Usuario;
import java.util.List;

public class LoginController {
    private static List<Usuario> usuariosRegistrados = RegistroUsuarioController.getUsuariosRegistrados();

    public static boolean iniciarSesion(String correo, String contrasena) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}