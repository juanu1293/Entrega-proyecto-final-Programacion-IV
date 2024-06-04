package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;
import Modelo.Reserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModificarReservaFrame extends JFrame {
    private JComboBox<Habitacion> comboHabitaciones;
    private JComboBox<Reserva> comboReservas;
    private JTextField campoFechaEntrada;
    private JTextField campoFechaSalida;
    private JTextField campoNumHuespedes;
    private SimpleDateFormat dateFormat;

    public ModificarReservaFrame() {
        super("Modificar Reserva - MyHotel");

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaHabitacion = new JLabel("Seleccionar Habitación:");
        JLabel etiquetaReserva = new JLabel("Seleccionar Reserva:");
        JLabel etiquetaFechaEntrada = new JLabel("Nueva Fecha de Entrada (yyyy-MM-dd):");
        JLabel etiquetaFechaSalida = new JLabel("Nueva Fecha de Salida (yyyy-MM-dd):");
        JLabel etiquetaNumHuespedes = new JLabel("Nuevo Número de Huéspedes:");

        List<Habitacion> habitaciones = HabitacionController.getHabitaciones();
        comboHabitaciones = new JComboBox<>(habitaciones.toArray(new Habitacion[0]));
        comboReservas = new JComboBox<>();
        campoFechaEntrada = new JTextField(10);
        campoFechaSalida = new JTextField(10);
        campoNumHuespedes = new JTextField(5);

        JButton botonModificar = new JButton("Modificar Reserva");

        comboHabitaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
                if (habitacionSeleccionada != null) {
                    List<Reserva> reservas = habitacionSeleccionada.getReservas();
                    comboReservas.removeAllItems();
                    for (Reserva reserva : reservas) {
                        comboReservas.addItem(reserva);
                    }
                }
            }
        });

        botonModificar.addActionListener(e -> {
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            Reserva reservaSeleccionada = (Reserva) comboReservas.getSelectedItem();
            String fechaEntradaStr = campoFechaEntrada.getText();
            String fechaSalidaStr = campoFechaSalida.getText();
            String numHuespedesStr = campoNumHuespedes.getText();
            
            if (habitacionSeleccionada != null && reservaSeleccionada != null && !fechaEntradaStr.isEmpty() && !fechaSalidaStr.isEmpty() && !numHuespedesStr.isEmpty()) {
                try {
                    Date fechaEntrada = dateFormat.parse(fechaEntradaStr);
                    Date fechaSalida = dateFormat.parse(fechaSalidaStr);
                    int numHuespedes = Integer.parseInt(numHuespedesStr);

                    reservaSeleccionada.setFechaEntrada(fechaEntrada);
                    reservaSeleccionada.setFechaSalida(fechaSalida);
                    reservaSeleccionada.setNumHuespedes(numHuespedes);

                    JOptionPane.showMessageDialog(this, "Reserva modificada satisfactoriamente", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese fechas en el formato correcto (yyyy-MM-dd).", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido de huéspedes.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(etiquetaHabitacion);
        panel.add(comboHabitaciones);
        panel.add(etiquetaReserva);
        panel.add(comboReservas);
        panel.add(etiquetaFechaEntrada);
        panel.add(campoFechaEntrada);
        panel.add(etiquetaFechaSalida);
        panel.add(campoFechaSalida);
        panel.add(etiquetaNumHuespedes);
        panel.add(campoNumHuespedes);
        panel.add(botonModificar);

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}