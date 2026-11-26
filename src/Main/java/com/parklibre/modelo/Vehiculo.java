package Main.java.com.parklibre.modelo;
import Main.java.com.parklibre.exepciones.*;
import java.time.LocalDateTime;

public abstract class Vehiculo {
    private int id;
    private String placa;
    private String propietario;
    private LocalDateTime horaIngreso;
    private int horas;

    public Vehiculo(String placa, String propietario, LocalDateTime horaIngreso, int horas) {
        setPlaca(placa);
        setPropietario(propietario);
        setHoraIngreso(horaIngreso);
        setHoras(horas);
    }

    public abstract double calcularValor();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        if (placa == null || placa.isBlank()) {
            throw new PlacaInvalidaException(
                "La placa no puede estar vacía"
            );
        }

        this.placa = placa.trim().toUpperCase();
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        if (propietario == null || propietario.isBlank()) {
            throw new PropietarioInvalidoException(
                "El propietario es obligatorio"
            );
        }

        this.propietario = propietario.trim();
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        if (horaIngreso == null) {
            throw new HoraIngresoInvalidaException(
                "La hora de ingreso es obligatoria"
            );
        }

        this.horaIngreso = horaIngreso;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas <= 0) {
            throw new HorasInvalidasException(
                "Las horas deben ser mayores a cero"
            );
        }

        this.horas = horas;
    }
}

