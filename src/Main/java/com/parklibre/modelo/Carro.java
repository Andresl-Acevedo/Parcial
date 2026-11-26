package Main.java.com.parklibre.modelo;
import java.time.LocalDateTime;

public class Carro extends Vehiculo {
    private static final double TARIFA = 3000;

    public Carro(String placa, String propietario, LocalDateTime horaIngreso, int horas) {
        super(placa, propietario, horaIngreso, horas);
    }

    @Override
    public double calcularValor() {
        return getHoras() * TARIFA;
    }
}