package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VerificarDisponibilidadFrame extends JFrame {
    private JTextArea areaResultado;
    private SimpleDateFormat dateFormat;

    public VerificarDisponibilidadFrame() {
        super("Verificar Disponibilidad - MyHotel");

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaPeriodo = new JLabel("Ingrese el período de tiempo (por ejemplo, '2024-06-10' al '2024-06-15'):");
        JTextField campoFechaInicio = new JTextField(10);
        JTextField campoFechaFin = new JTextField(10);
        JButton botonVerificar = new JButton("Verificar Disponibilidad");

        areaResultado = new JTextArea(20, 40);
        areaResultado.setEditable(false);

        botonVerificar.addActionListener(e -> {
            String fechaInicioStr = campoFechaInicio.getText();
            String fechaFinStr = campoFechaFin.getText();
            if (!fechaInicioStr.isEmpty() && !fechaFinStr.isEmpty()) {
                try {
                    Date fechaInicio = dateFormat.parse(fechaInicioStr);
                    Date fechaFin = dateFormat.parse(fechaFinStr);
                    List<Habitacion> habitacionesDisponibles = HabitacionController.verificarDisponibilidad(fechaInicio, fechaFin);
                    mostrarDisponibilidad(habitacionesDisponibles);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese fechas en el formato correcto (yyyy-MM-dd).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese ambas fechas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(etiquetaPeriodo);
        panel.add(new JLabel("Fecha Inicio:"));
        panel.add(campoFechaInicio);
        panel.add(new JLabel("Fecha Fin:"));
        panel.add(campoFechaFin);
        panel.add(botonVerificar);
        panel.add(new JScrollPane(areaResultado));

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarDisponibilidad(List<Habitacion> habitacionesDisponibles) {
        if (habitacionesDisponibles.isEmpty()) {
            areaResultado.setText("No hay habitaciones disponibles para el período especificado.\n");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Habitaciones disponibles para el período especificado:\n");
            for (Habitacion habitacion : habitacionesDisponibles) {
                sb.append("Tipo: ").append(habitacion.getTipo())
                  .append(" - Capacidad: ").append(habitacion.getCapacidad())
                  .append(" - Precio: ").append(habitacion.getPrecio())
                  .append(" - Comodidades: ").append(habitacion.getComodidades()).append("\n");
            }
            areaResultado.setText(sb.toString());
        }
    }

}