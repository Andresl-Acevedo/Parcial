package Main.java.com.parklibre.persistencias;

import Main.java.com.parklibre.modelo.Vehiculo;
import java.util.List;
import java.sql.SQLException;

public interface VehiculoDAO {
    void guardar(Vehiculo vehiculo) throws SQLException;
    List<Vehiculo> listar() throws SQLException;
    void actualizar(Vehiculo vehiculo) throws SQLException;
    void eliminar(int id) throws SQLException;
}
