package Vista;

import javax.swing.*;
import Controlador.HabitacionController;
import Modelo.Habitacion;

public class AgregarHabitacionFrame extends JFrame {
    private JComboBox<String> comboTipo;
    private JTextField campoCapacidad;
    private JTextField campoPrecio;
    private JTextArea campoComodidades;

    public AgregarHabitacionFrame() {
        super("Agregar Habitación - MyHotel");

        JPanel panelAgregar = new JPanel();
        GroupLayout layout = new GroupLayout(panelAgregar);
        panelAgregar.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel etiquetaTipo = new JLabel("Tipo de Habitación:");
        JLabel etiquetaCapacidad = new JLabel("Capacidad:");
        JLabel etiquetaPrecio = new JLabel("Precio:");
        JLabel etiquetaComodidades = new JLabel("Comodidades:");
        
        comboTipo = new JComboBox<>(new String[] {"Sencilla", "Múltiple"});
        campoCapacidad = new JTextField(20);
        campoPrecio = new JTextField(20);
        campoComodidades = new JTextArea(5, 20);

        JButton botonAgregar = new JButton("Agregar");

        botonAgregar.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            int capacidad;
            double precio;
            String comodidades = campoComodidades.getText();
            try {
                capacidad = Integer.parseInt(campoCapacidad.getText());
                precio = Double.parseDouble(campoPrecio.getText());
                if (comodidades.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                } else {
                    Habitacion nuevaHabitacion = new Habitacion(tipo, capacidad, precio, comodidades);
                    HabitacionController.agregarHabitacion(nuevaHabitacion);
                    JOptionPane.showMessageDialog(this, "Habitación agregada satisfactoriamente", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacidad y Precio deben ser valores numéricos", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        });

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(etiquetaTipo)
                .addComponent(etiquetaCapacidad)
                .addComponent(etiquetaPrecio)
                .addComponent(etiquetaComodidades));
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(comboTipo)
                .addComponent(campoCapacidad)
                .addComponent(campoPrecio)
                .addComponent(campoComodidades)
                .addComponent(botonAgregar));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaTipo)
                .addComponent(comboTipo));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaCapacidad)
                .addComponent(campoCapacidad));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaPrecio)
                .addComponent(campoPrecio));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(etiquetaComodidades)
                .addComponent(campoComodidades));
        vGroup.addComponent(botonAgregar);
        layout.setVerticalGroup(vGroup);

        add(panelAgregar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}