
package Main.java.com.parklibre.vista;

import Main.java.com.parklibre.modelo.*;
import Main.java.com.parklibre.patron.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class FormularioVehiculo extends JDialog {

    private JTextField txtPlaca;
    private JTextField txtPropietario;
    private JComboBox<String> cmbTipo;
    private JSpinner spnHoras;

    private Vehiculo vehiculo;
    private Vehiculo original;

    public FormularioVehiculo(
        JFrame padre,
        Vehiculo existente
    ) {

        super(padre, true);

        original = existente;

        setTitle(
            existente == null
                ? "Registrar vehículo"
                : "Editar vehículo"
        );

        setSize(450, 350);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout(15, 15));

        crearFormulario();
        crearBotones();

        if (existente != null) {
            cargarDatos(existente);
        }
    }

    private void crearFormulario() {

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));

        panel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        txtPlaca = new JTextField();
        txtPropietario = new JTextField();

        cmbTipo = new JComboBox<>(
            new String[]{"Carro", "Moto", "Camion"}
        );

        spnHoras = new JSpinner(
            new SpinnerNumberModel(1, 1, 1000, 1)
        );

        panel.add(new JLabel("Placa:"));
        panel.add(txtPlaca);

        panel.add(new JLabel("Propietario:"));
        panel.add(txtPropietario);

        panel.add(new JLabel("Tipo de vehículo:"));
        panel.add(cmbTipo);

        panel.add(new JLabel("Horas:"));
        panel.add(spnHoras);

        cmbTipo.setEnabled(original == null);

        add(panel, BorderLayout.CENTER);
    }

    private void crearBotones() {

        JPanel panel = new JPanel(
            new FlowLayout(FlowLayout.CENTER)
        );

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());

        panel.add(btnGuardar);
        panel.add(btnCancelar);

        add(panel, BorderLayout.SOUTH);
    }

    private void cargarDatos(Vehiculo v) {

        txtPlaca.setText(v.getPlaca());
        txtPropietario.setText(v.getPropietario());
        spnHoras.setValue(v.getHoras());

        cmbTipo.setSelectedItem(
            v.getClass().getSimpleName()
        );
    }

    private void guardar() {

        try {

            String placa = txtPlaca.getText();
            String propietario = txtPropietario.getText();

            int horas = (int) spnHoras.getValue();

            LocalDateTime ingreso =
                original == null
                    ? LocalDateTime.now()
                    : original.getHoraIngreso();

            VehiculoFactory fabrica;

            String tipo = (String) cmbTipo.getSelectedItem();

            switch (tipo) {

                case "Carro":
                    fabrica = new CarroFactory();
                    break;

                case "Moto":
                    fabrica = new MotoFactory();
                    break;

                case "Camion":
                    fabrica = new CamionFactory();
                    break;

                default:
                    throw new IllegalArgumentException(
                        "Tipo de vehículo inválido"
                    );
            }

            vehiculo = fabrica.crearVehiculo(
                placa,
                propietario,
                ingreso,
                horas
            );

            if (original != null) {
                vehiculo.setId(original.getId());
            }

            dispose();

        } catch (RuntimeException ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error de validación",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
}
