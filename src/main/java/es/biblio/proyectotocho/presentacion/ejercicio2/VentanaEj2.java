package es.biblio.proyectotocho.presentacion.ejercicio2;

import javax.swing.JFrame;

public class VentanaEj2 extends JFrame {

    public final int ALTO = 500;
    public final int ANCHO = 500;

    public LaminaEj2 lamina;

    public VentanaEj2() {

        setTitle("Alta nuevo producto");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEj2();

        add(lamina);

        setVisible(true);
    }
}
