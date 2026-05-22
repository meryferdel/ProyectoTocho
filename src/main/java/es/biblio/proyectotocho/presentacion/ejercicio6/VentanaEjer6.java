package es.biblio.proyectotocho.presentacion.ejercicio6;

import es.biblio.proyectotocho.negocio.Ejer6Controller;
import javax.swing.JFrame;

/**
 *
 * @author Usuario25
 */
public class VentanaEjer6 extends JFrame {
    public final int ALTO = 450;
    public final int ANCHO = 500;

    public LaminaEjer6 lamina;

    public VentanaEjer6(Ejer6Controller controlador) {

        setTitle("Alta nuevo almacén");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEjer6(this, controlador);

        add(lamina);

        setVisible(true);
    }

    public LaminaEjer6 getLamina() {
        return lamina;
    }
}
