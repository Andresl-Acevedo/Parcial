package Main.java.com.parklibre.modelo;
import java.time.LocalDateTime;

public class Moto extends Vehiculo {
    private static final double TARIFA = 1500;

    public Moto(String placa, String propietario, LocalDateTime horaIngreso, int horas) {
        super(placa, propietario, horaIngreso, horas);
    }

    @Override
    public double calcularValor() {
        return getHoras() * TARIFA;
    }
}