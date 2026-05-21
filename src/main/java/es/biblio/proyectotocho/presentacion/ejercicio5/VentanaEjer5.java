package es.biblio.proyectotocho.presentacion.ejercicio5;

import javax.swing.*;

public class VentanaEjer5 extends JFrame {

    public final int ANCHO = 450;
    public final int ALTO = 300;

    public LaminaEjer5 lamina;

    public VentanaEjer5() {

        setTitle("Aplicar descuento por categoría");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEjer5();
        add(lamina);

        setVisible(true);
    }
}
