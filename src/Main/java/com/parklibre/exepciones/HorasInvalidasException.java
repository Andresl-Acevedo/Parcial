package Main.java.com.parklibre.exepciones;

public class HorasInvalidasException extends RuntimeException {
    public HorasInvalidasException(String mensaje) {
        super(mensaje);
    }
}
