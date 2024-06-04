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

public class RealizarReservaFrame extends JFrame {
    private JComboBox<Habitacion> comboHabitaciones;
    private JTextField campoNombre;
    private JTextField campoFechaEntrada;
    private JTextField campoFechaSalida;
    private JTextField campoNumHuespedes;
    private SimpleDateFormat dateFormat;

    public RealizarReservaFrame() {
        super("Realizar Reserva - MyHotel");

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaHabitacion = new JLabel("Seleccionar Habitación:");
        JLabel etiquetaNombre = new JLabel("Nombre:");
        JLabel etiquetaFechaEntrada = new JLabel("Fecha de Entrada (yyyy-MM-dd):");
        JLabel etiquetaFechaSalida = new JLabel("Fecha de Salida (yyyy-MM-dd):");
        JLabel etiquetaNumHuespedes = new JLabel("Número de Huéspedes:");

        List<Habitacion> habitaciones = HabitacionController.getHabitaciones();
        comboHabitaciones = new JComboBox<>(habitaciones.toArray(new Habitacion[0]));
        campoNombre = new JTextField(20);
        campoFechaEntrada = new JTextField(10);
        campoFechaSalida = new JTextField(10);
        campoNumHuespedes = new JTextField(5);

        JButton botonReservar = new JButton("Realizar Reserva");

        botonReservar.addActionListener(e -> {
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            String nombre = campoNombre.getText();
            String fechaEntradaStr = campoFechaEntrada.getText();
            String fechaSalidaStr = campoFechaSalida.getText();
            String numHuespedesStr = campoNumHuespedes.getText();
            
            if (habitacionSeleccionada != null && !nombre.isEmpty() && !fechaEntradaStr.isEmpty() && !fechaSalidaStr.isEmpty() && !numHuespedesStr.isEmpty()) {
                try {
                    Date fechaEntrada = dateFormat.parse(fechaEntradaStr);
                    Date fechaSalida = dateFormat.parse(fechaSalidaStr);
                    int numHuespedes = Integer.parseInt(numHuespedesStr);

                    if (numHuespedes <= habitacionSeleccionada.getCapacidad()) {
                        Reserva nuevaReserva = new Reserva(fechaEntrada, fechaSalida, nombre, numHuespedes);
                        boolean reservaExitosa = HabitacionController.reservarHabitacion(habitacionSeleccionada, nuevaReserva);
                        if (reservaExitosa) {
                            JOptionPane.showMessageDialog(this, "Reserva realizada satisfactoriamente", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "La habitación ya no está disponible para las fechas seleccionadas.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "El número de huéspedes excede la capacidad de la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
        panel.add(etiquetaNombre);
        panel.add(campoNombre);
        panel.add(etiquetaFechaEntrada);
        panel.add(campoFechaEntrada);
        panel.add(etiquetaFechaSalida);
        panel.add(campoFechaSalida);
        panel.add(etiquetaNumHuespedes);
        panel.add(campoNumHuespedes);
        panel.add(botonReservar);

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}