
package Main.java.com.parklibre.persistencias;

import Main.java.com.parklibre.modelo.*;
import Main.java.com.parklibre.patron.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAOImpl implements VehiculoDAO {
    @Override
    public void guardar(Vehiculo v) throws SQLException {
        String sql = """
            INSERT INTO vehiculos
            (placa, propietario, tipo, hora_ingreso, horas, tarifa)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getPropietario());
            ps.setString(3, v.getClass().getSimpleName());
            ps.setString(4, v.getHoraIngreso().toString());
            ps.setInt(5, v.getHoras());
            ps.setDouble(6, v.calcularValor() / v.getHoras());

            ps.executeUpdate();
        }
    }

    @Override
    public List<Vehiculo> listar() throws SQLException {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");

                VehiculoFactory fabrica;
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
                        throw new SQLException(
                            "Tipo de vehículo desconocido: " + tipo
                        );
                }

                Vehiculo v = fabrica.crearVehiculo(
                    rs.getString("placa"),
                    rs.getString("propietario"),
                    LocalDateTime.parse(rs.getString("hora_ingreso")),
                    rs.getInt("horas")
                );

                v.setId(rs.getInt("id"));
                lista.add(v);
            }
        }

        return lista;
    }

    @Override
    public void actualizar(Vehiculo v) throws SQLException {
        String sql = """
            UPDATE vehiculos
            SET placa=?, propietario=?, hora_ingreso=?,
                horas=?, tarifa=?
            WHERE id=?
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getPropietario());
            ps.setString(3, v.getHoraIngreso().toString());
            ps.setInt(4, v.getHoras());
            ps.setDouble(5, v.calcularValor() / v.getHoras());
            ps.setInt(6, v.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vehiculos WHERE id=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
