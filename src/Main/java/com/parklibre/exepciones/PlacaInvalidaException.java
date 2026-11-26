package Main.java.com.parklibre.exepciones;

public class PlacaInvalidaException extends RuntimeException {
    public PlacaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
