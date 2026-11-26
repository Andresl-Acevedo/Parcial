package Main.java.com.parklibre.patron;
import Main.java.com.parklibre.modelo.*;
import java.time.LocalDateTime;

public class CamionFactory extends VehiculoFactory {
    @Override
    public Vehiculo crearVehiculo(String placa, String propietario, LocalDateTime horaIngreso, int horas) {
        return new Camion(
            placa,
            propietario,
            horaIngreso,
            horas
        );
    }
}
