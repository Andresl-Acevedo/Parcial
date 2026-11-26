package Main.java.com.parklibre;

import Main.java.com.parklibre.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VentanaPrincipal ventana =
                new VentanaPrincipal();

            ventana.setVisible(true);

        });
    }
}
