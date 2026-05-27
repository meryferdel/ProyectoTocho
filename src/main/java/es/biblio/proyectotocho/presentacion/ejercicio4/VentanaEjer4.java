package es.biblio.proyectotocho.presentacion.ejercicio4;

import es.biblio.proyectotocho.negocio.Ejer4Controller;
import javax.swing.JFrame;

public class VentanaEjer4 extends JFrame {

    public final int ALTO = 500;
    public final int ANCHO = 900;

    private LaminaEjer4 lamina;

    public VentanaEjer4(Ejer4Controller controlador) {

        setTitle("Inventario de almacén");

        setSize(ANCHO, ALTO);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEjer4(this, controlador);

        add(lamina);

        setVisible(true);
    }

    public LaminaEjer4 getLamina() {
        return lamina;
    }
}
