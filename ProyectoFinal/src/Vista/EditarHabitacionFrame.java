package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;
import java.util.List;

public class EditarHabitacionFrame extends JFrame {
    private JComboBox<Habitacion> comboHabitaciones;
    private JTextField campoPrecio;
    private JTextArea campoComodidades;
    private JCheckBox checkDisponibilidad;

    public EditarHabitacionFrame() {
        super("Editar Habitación - MyHotel");

        JPanel panelEditar = new JPanel();
        GroupLayout layout = new GroupLayout(panelEditar);
        panelEditar.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel etiquetaHabitacion = new JLabel("Seleccionar Habitación:");
        JLabel etiquetaPrecio = new JLabel("Precio:");
        JLabel etiquetaComodidades = new JLabel("Comodidades:");
        JLabel etiquetaDisponibilidad = new JLabel("Disponibilidad:");

        List<Habitacion> habitaciones = HabitacionController.getHabitaciones();
        comboHabitaciones = new JComboBox<>(habitaciones.toArray(new Habitacion[0]));
        campoPrecio = new JTextField(20);
        campoComodidades = new JTextArea(5, 20);
        checkDisponibilidad = new JCheckBox("Disponible");

        JButton botonGuardar = new JButton("Guardar Cambios");

        comboHabitaciones.addActionListener(e -> {
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            if (habitacionSeleccionada != null) {
                campoPrecio.setText(String.valueOf(habitacionSeleccionada.getPrecio()));
                campoComodidades.setText(habitacionSeleccionada.getComodidades());
                checkDisponibilidad.setSelected(habitacionSeleccionada.isDisponible());
            }
        });

        botonGuardar.addActionListener(e -> {
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            if (habitacionSeleccionada != null) {
                try {
                    double precio = Double.parseDouble(campoPrecio.getText());
                    String comodidades = campoComodidades.getText();
                    boolean disponibilidad = checkDisponibilidad.isSelected();

                    habitacionSeleccionada.setPrecio(precio);
                    habitacionSeleccionada.setComodidades(comodidades);
                    habitacionSeleccionada.setDisponible(disponibilidad);

                    HabitacionController.actualizarHabitacion(habitacionSeleccionada);
                    JOptionPane.showMessageDialog(this, "Habitación actualizada satisfactoriamente", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El precio debe ser un valor numérico", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(etiquetaHabitacion)
                .addComponent(etiquetaPrecio)
                .addComponent(etiquetaComodidades)
                .addComponent(etiquetaDisponibilidad));
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(comboHabitaciones)
                .addComponent(campoPrecio)
                .addComponent(campoComodidades)
                .addComponent(checkDisponibilidad)
                .addComponent(botonGuardar));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaHabitacion)
                .addComponent(comboHabitaciones));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaPrecio)
                .addComponent(campoPrecio));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaComodidades)
                .addComponent(campoComodidades));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaDisponibilidad)
                .addComponent(checkDisponibilidad));
        vGroup.addComponent(botonGuardar);
        layout.setVerticalGroup(vGroup);

        add(panelEditar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}