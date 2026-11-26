package Main.java.com.parklibre.persistencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL ="jdbc:sqlite:parklibre.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
