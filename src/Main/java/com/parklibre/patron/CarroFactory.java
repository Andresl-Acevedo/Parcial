package Main.java.com.parklibre.patron;
import Main.java.com.parklibre.modelo.*;
import java.time.LocalDateTime;

public class CarroFactory extends VehiculoFactory {
    @Override
    public Vehiculo crearVehiculo(String placa, String propietario, LocalDateTime horaIngreso, int horas) {
        return new Carro(
            placa,
            propietario,
            horaIngreso,
            horas
        );
    }
}

