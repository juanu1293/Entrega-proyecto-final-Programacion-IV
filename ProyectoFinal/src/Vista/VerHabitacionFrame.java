package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerHabitacionFrame extends JFrame {
    private JComboBox<Habitacion> comboHabitaciones;
    private JTextArea areaDetalles;

    public VerHabitacionFrame() {
        super("Ver Detalles de Habitación - MyHotel");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaHabitacion = new JLabel("Seleccionar Habitación:");

        List<Habitacion> habitaciones = HabitacionController.getHabitaciones();
        comboHabitaciones = new JComboBox<>(habitaciones.toArray(new Habitacion[0]));

        areaDetalles = new JTextArea(10, 30);
        areaDetalles.setEditable(false);

        comboHabitaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
                if (habitacionSeleccionada != null) {
                    mostrarDetallesHabitacion(habitacionSeleccionada);
                }
            }
        });

        panel.add(etiquetaHabitacion);
        panel.add(comboHabitaciones);
        panel.add(new JScrollPane(areaDetalles));

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarDetallesHabitacion(Habitacion habitacion) {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Tipo de Habitación: ").append(habitacion.getTipo()).append("\n");
        detalles.append("Capacidad: ").append(habitacion.getCapacidad()).append("\n");
        detalles.append("Precio por Noche: ").append(habitacion.getPrecio()).append("\n");
        detalles.append("Comodidades: ").append(habitacion.getComodidades()).append("\n");

        areaDetalles.setText(detalles.toString());
    }
}