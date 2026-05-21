package es.biblio.proyectotocho.presentacion.ejercicio5;

import javax.swing.*;

public class VentanaEj5 extends JFrame {

    public final int ANCHO = 450;
    public final int ALTO = 300;

    public LaminaEj5 lamina;

    public VentanaEj5() {

        setTitle("Aplicar descuento por categoría");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEj5();
        add(lamina);

        setVisible(true);
    }
}
