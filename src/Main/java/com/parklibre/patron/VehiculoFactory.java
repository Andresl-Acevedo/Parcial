package Main.java.com.parklibre.patron;
import Main.java.com.parklibre.modelo.Vehiculo;
import java.time.LocalDateTime;

public abstract class VehiculoFactory {
    public abstract Vehiculo crearVehiculo(String placa, String propietario, LocalDateTime horaIngreso, int horas
    );
}

