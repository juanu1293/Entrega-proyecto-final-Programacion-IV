package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Reserva;
import java.util.List;

public class HistorialReservasFrame extends JFrame {
    private JTextArea areaHistorial;

    public HistorialReservasFrame() {
        super("Historial de Reservas - MyHotel");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        areaHistorial = new JTextArea(20, 40);
        areaHistorial.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaHistorial);

        panel.add(scrollPane);
        add(panel);

        cargarHistorialReservas();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cargarHistorialReservas() {
        List<Reserva> reservas = HabitacionController.getReservas();
        if (reservas.isEmpty()) {
            areaHistorial.setText("No hay reservas realizadas.");
        } else {
            StringBuilder historial = new StringBuilder();
            for (Reserva reserva : reservas) {
                historial.append(reserva.toString()).append("\n");
            }
            areaHistorial.setText(historial.toString());
        }
    }
}