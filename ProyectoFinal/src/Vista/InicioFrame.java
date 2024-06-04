package Vista;

import javax.swing.*;

public class InicioFrame extends JFrame {
    public InicioFrame() {
        super("Inicio de sesión - MyHotel");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu reservasMenu = new JMenu("Reservas");
        JMenuItem realizarReservaItem = new JMenuItem("Realizar reserva");
        JMenuItem modificarReservaItem = new JMenuItem("Modificar reserva");
        JMenuItem cancelarReservaItem = new JMenuItem("Cancelar reserva");
        JMenuItem historialReservasItem = new JMenuItem("Historial de reservas");

        realizarReservaItem.addActionListener(e -> {
            new RealizarReservaFrame();
        });

        modificarReservaItem.addActionListener(e -> {
            new ModificarReservaFrame();
        });

        cancelarReservaItem.addActionListener(e -> {
            new CancelarReservaFrame();
        });
        
        historialReservasItem.addActionListener(e -> {
            new HistorialReservasFrame();
        });

        reservasMenu.add(realizarReservaItem);
        reservasMenu.add(modificarReservaItem);
        reservasMenu.add(cancelarReservaItem);
        reservasMenu.add(historialReservasItem);
        
        JMenu habitacionMenu = new JMenu("Habitaciones");
        JMenuItem verHabitacionItem = new JMenuItem("Ver detalles de habitación");
        JMenuItem buscarHabitacionItem = new JMenuItem("Buscar Habitación");
        
        verHabitacionItem.addActionListener(e -> {
            new VerHabitacionFrame();
        });
        
        buscarHabitacionItem.addActionListener(e -> {
            new BuscarHabitacionFrame();
        });
        
        habitacionMenu.add(verHabitacionItem);
        habitacionMenu.add(buscarHabitacionItem);
        
        JMenuItem cerrarSesionItem = new JMenuItem("Cerrar sesión");
        cerrarSesionItem.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        
        menuBar.add(reservasMenu);
        menuBar.add(habitacionMenu);
        menuBar.add(cerrarSesionItem);

        
        setJMenuBar(menuBar);

        setVisible(true);
    }
}