package Vista;

import javax.swing.*;

public class AdminFrame extends JFrame {
    public AdminFrame() {
        super("Administrador - MyHotel");
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu adminMenu = new JMenu("Administración");
        
        JMenuItem verificarDisponibilidadItem = new JMenuItem("Verificar Disponibilidad");
        JMenuItem agregarHabitacionItem = new JMenuItem("Agregar Habitación al Inventario");
        JMenuItem editarHabitacionItem = new JMenuItem("Editar Habitación en el Inventario");
        JMenuItem eliminarHabitacionItem = new JMenuItem("Eliminar Habitación del Inventario");

        verificarDisponibilidadItem.addActionListener(e -> {
        	new VerificarDisponibilidadFrame();
        });

        agregarHabitacionItem.addActionListener(e -> {
            new AgregarHabitacionFrame();
        });

        editarHabitacionItem.addActionListener(e -> {
            new EditarHabitacionFrame();
        });

        eliminarHabitacionItem.addActionListener(e -> {
            new EliminarHabitacionFrame();
        });
        
        adminMenu.add(verificarDisponibilidadItem);
        adminMenu.add(agregarHabitacionItem);
        adminMenu.add(editarHabitacionItem);
        adminMenu.add(eliminarHabitacionItem);
        
        JMenuItem cerrarSesionItem = new JMenuItem("Cerrar sesión");
        cerrarSesionItem.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        menuBar.add(adminMenu);
        menuBar.add(cerrarSesionItem);

        setJMenuBar(menuBar);

        setVisible(true);
    }
}