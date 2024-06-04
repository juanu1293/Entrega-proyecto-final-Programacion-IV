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

public class CancelarReservaFrame extends JFrame {
    private JComboBox<Habitacion> comboHabitaciones;
    private JComboBox<Reserva> comboReservas;
    private SimpleDateFormat dateFormat;

    public CancelarReservaFrame() {
        super("Cancelar Reserva - MyHotel");

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaHabitacion = new JLabel("Seleccionar Habitación:");
        JLabel etiquetaReserva = new JLabel("Seleccionar Reserva:");

        List<Habitacion> habitaciones = HabitacionController.getHabitaciones();
        comboHabitaciones = new JComboBox<>(habitaciones.toArray(new Habitacion[0]));
        comboReservas = new JComboBox<>();

        JButton botonCancelar = new JButton("Cancelar Reserva");

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

        botonCancelar.addActionListener(e -> {
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            Reserva reservaSeleccionada = (Reserva) comboReservas.getSelectedItem();

            if (habitacionSeleccionada != null && reservaSeleccionada != null) {
                long diff = reservaSeleccionada.getFechaEntrada().getTime() - new Date().getTime();
                long diffDays = diff / (1000 * 60 * 60 * 24);
                habitacionSeleccionada.eliminarReserva(reservaSeleccionada);
                HabitacionController.actualizarHabitacion(habitacionSeleccionada);
                if (diffDays >= 7) {
                    JOptionPane.showMessageDialog(this, "Reserva cancelada satisfactoriamente. Usted es elegible para un reembolso.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Reserva cancelada satisfactoriamente. No es posible reembolso debido a la cancelación tardía.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una habitación y una reserva.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(etiquetaHabitacion);
        panel.add(comboHabitaciones);
        panel.add(etiquetaReserva);
        panel.add(comboReservas);
        panel.add(botonCancelar);

        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}