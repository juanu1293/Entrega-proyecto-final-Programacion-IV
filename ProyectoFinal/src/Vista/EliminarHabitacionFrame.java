package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;

public class EliminarHabitacionFrame extends JFrame {
    private JComboBox<Habitacion> comboHabitaciones;

    public EliminarHabitacionFrame() {
        super("Eliminar Habitación - MyHotel");

        JPanel panelEliminar = new JPanel();
        GroupLayout layout = new GroupLayout(panelEliminar);
        panelEliminar.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel etiquetaHabitacion = new JLabel("Seleccionar Habitación:");

        comboHabitaciones = new JComboBox<>(HabitacionController.getHabitaciones().toArray(new Habitacion[0]));

        JButton botonEliminar = new JButton("Eliminar Habitación");

        botonEliminar.addActionListener(e -> {
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            if (habitacionSeleccionada != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta habitación?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    HabitacionController.eliminarHabitacion(habitacionSeleccionada);
                    JOptionPane.showMessageDialog(this, "Habitación eliminada satisfactoriamente", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    comboHabitaciones.removeItem(habitacionSeleccionada);
                }
            }
        });

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(etiquetaHabitacion));
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(comboHabitaciones)
                .addComponent(botonEliminar));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaHabitacion)
                .addComponent(comboHabitaciones));
        vGroup.addComponent(botonEliminar);
        layout.setVerticalGroup(vGroup);

        add(panelEliminar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}