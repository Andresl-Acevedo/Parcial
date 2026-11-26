package Main.java.com.parklibre.modelo;
import java.time.LocalDateTime;

public class Camion extends Vehiculo {
    private static final double TARIFA = 5000;

    public Camion(String placa, String propietario, LocalDateTime horaIngreso, int horas) {
        super(placa, propietario, horaIngreso, horas);
    }

    @Override
    public double calcularValor() {
        return getHoras() * TARIFA;
    }
}
