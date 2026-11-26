
package Main.java.com.parklibre.vista;

import Main.java.com.parklibre.modelo.Vehiculo;
import Main.java.com.parklibre.persistencias.VehiculoDAO;
import Main.java.com.parklibre.persistencias.VehiculoDAOImpl;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private VehiculoDAO dao;

    public VentanaPrincipal() {

        dao = new VehiculoDAOImpl();

        setTitle("ParkLibre - Administración de Parqueadero");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        crearEncabezado();
        crearTabla();
        crearBotones();

        cargarVehiculos();
    }

    private void crearEncabezado() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(25, 45, 75));
        panel.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        JLabel titulo = new JLabel("PARKLIBRE");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel(
            "Sistema de administración de parqueadero"
        );
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel textos = new JPanel(new GridLayout(2, 1));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);

        panel.add(textos, BorderLayout.WEST);

        add(panel, BorderLayout.NORTH);
    }

    private void crearTabla() {

        String[] columnas = {
            "ID",
            "Placa",
            "Propietario",
            "Tipo",
            "Horas",
            "Valor"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));

        tabla.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        add(scroll, BorderLayout.CENTER);
    }

    private void crearBotones() {

        JPanel panel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 15, 15)
        );

        JButton btnGuardar = new JButton("Registrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");

        btnGuardar.addActionListener(e -> registrarVehiculo());
        btnEditar.addActionListener(e -> editarVehiculo());
        btnEliminar.addActionListener(e -> eliminarVehiculo());
        btnActualizar.addActionListener(e -> cargarVehiculos());

        panel.add(btnGuardar);
        panel.add(btnEditar);
        panel.add(btnEliminar);
        panel.add(btnActualizar);

        add(panel, BorderLayout.SOUTH);
    }

    private void cargarVehiculos() {

        try {

            modeloTabla.setRowCount(0);

            List<Vehiculo> vehiculos = dao.listar();

            for (Vehiculo v : vehiculos) {

                modeloTabla.addRow(new Object[]{
                    v.getId(),
                    v.getPlaca(),
                    v.getPropietario(),
                    v.getClass().getSimpleName(),
                    v.getHoras(),
                    "$ " + v.calcularValor()
                });
            }

        } catch (SQLException ex) {

            mostrarError(
                "No se pudieron cargar los vehículos:\n"
                + ex.getMessage()
            );
        }
    }

    private void registrarVehiculo() {

        FormularioVehiculo formulario =
            new FormularioVehiculo(this, null);

        formulario.setVisible(true);

        Vehiculo vehiculo = formulario.getVehiculo();

        if (vehiculo != null) {

            try {

                dao.guardar(vehiculo);
                cargarVehiculos();

                JOptionPane.showMessageDialog(
                    this,
                    "Vehículo registrado correctamente"
                );

            } catch (SQLException ex) {

                mostrarError(
                    "No se pudo registrar el vehículo:\n"
                    + ex.getMessage()
                );
            } catch (RuntimeException ex) {
                mostrarError(ex.getMessage());
            }
        }
    }

    private void editarVehiculo() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            mostrarError("Selecciona un vehículo de la tabla");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);

        try {

            List<Vehiculo> vehiculos = dao.listar();

            Vehiculo seleccionado = vehiculos.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);

            if (seleccionado == null) {
                mostrarError("No se encontró el vehículo");
                return;
            }

            FormularioVehiculo formulario =
                new FormularioVehiculo(this, seleccionado);

            formulario.setVisible(true);

            Vehiculo modificado = formulario.getVehiculo();

            if (modificado != null) {

                modificado.setId(id);

                dao.actualizar(modificado);
                cargarVehiculos();

                JOptionPane.showMessageDialog(
                    this,
                    "Vehículo actualizado correctamente"
                );
            }

        } catch (SQLException ex) {

            mostrarError(
                "No se pudo editar el vehículo:\n"
                + ex.getMessage()
            );

        } catch (RuntimeException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void eliminarVehiculo() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            mostrarError("Selecciona un vehículo para eliminar");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);

        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de eliminar este vehículo?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            try {

                dao.eliminar(id);
                cargarVehiculos();

                JOptionPane.showMessageDialog(
                    this,
                    "Vehículo eliminado correctamente"
                );

            } catch (SQLException ex) {

                mostrarError(
                    "No se pudo eliminar el vehículo:\n"
                    + ex.getMessage()
                );
            }
        }
    }

    private void mostrarError(String mensaje) {

        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "ParkLibre - Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
