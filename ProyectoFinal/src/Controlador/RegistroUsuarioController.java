package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class RegistroUsuarioController {
    private static List<Usuario> usuariosRegistrados = new ArrayList<>();

    public static void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.add(usuario);
    }
    
    public static List<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }
    
    public static boolean usuarioRegistrado(String correo) {
        for (Usuario usuario : usuariosRegistrados) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }
}