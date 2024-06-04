package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BuscarHabitacionFrame extends JFrame {
    private JTextArea areaResultado;
    private JTextField campoFechaEntrada;
    private JTextField campoFechaSalida;
    private JTextField campoNumHuespedes;
    private JComboBox<String> comboTipoHabitacion;
    private SimpleDateFormat dateFormat;

    public BuscarHabitacionFrame() {
        super("Buscar Habitaciones Disponibles - MyHotel");

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaFechaEntrada = new JLabel("Fecha de Entrada (yyyy-MM-dd):");
        JLabel etiquetaFechaSalida = new JLabel("Fecha de Salida (yyyy-MM-dd):");
        JLabel etiquetaNumHuespedes = new JLabel("Número de Huéspedes:");
        JLabel etiquetaTipoHabitacion = new JLabel("Tipo de Habitación:");

        campoFechaEntrada = new JTextField(10);
        campoFechaSalida = new JTextField(10);
        campoNumHuespedes = new JTextField(5);
        comboTipoHabitacion = new JComboBox<>(new String[]{"Acomodación sencilla", "Acomodación múltiple"});

        JButton botonBuscar = new JButton("Buscar Habitaciones Disponibles");

        areaResultado = new JTextArea(20, 40);
        areaResultado.setEditable(false);

        botonBuscar.addActionListener(e -> {
            String fechaEntradaStr = campoFechaEntrada.getText();
            String fechaSalidaStr = campoFechaSalida.getText();
            String numHuespedesStr = campoNumHuespedes.getText();
            String tipoHabitacion = (String) comboTipoHabitacion.getSelectedItem();
            if (!fechaEntradaStr.isEmpty() && !fechaSalidaStr.isEmpty() && !numHuespedesStr.isEmpty()) {
                try {
                    Date fechaEntrada = dateFormat.parse(fechaEntradaStr);
                    Date fechaSalida = dateFormat.parse(fechaSalidaStr);
                    int numHuespedes = Integer.parseInt(numHuespedesStr);
                    List<Habitacion> habitacionesDisponibles = HabitacionController.verificarDisponibilidad(fechaEntrada, fechaSalida);
                    mostrarHabitacionesDisponibles(habitacionesDisponibles);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese fechas en el formato correcto (yyyy-MM-dd).", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de huéspedes.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(etiquetaFechaEntrada);
        panel.add(campoFechaEntrada);
        panel.add(etiquetaFechaSalida);
        panel.add(campoFechaSalida);
        panel.add(etiquetaNumHuespedes);
        panel.add(campoNumHuespedes);
        panel.add(etiquetaTipoHabitacion);
        panel.add(comboTipoHabitacion);
        panel.add(botonBuscar);
        panel.add(new JScrollPane(areaResultado));

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mostrarHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
        if (habitacionesDisponibles.isEmpty()) {
            areaResultado.setText("No hay habitaciones disponibles para los criterios especificados.\n");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Habitaciones disponibles para los criterios especificados:\n");
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