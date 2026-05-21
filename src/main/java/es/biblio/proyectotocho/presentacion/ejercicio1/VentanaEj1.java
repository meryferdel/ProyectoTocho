package es.biblio.proyectotocho.presentacion.ejercicio1;

import javax.swing.*;

public class VentanaEj1 extends JFrame {

    public final int ALTO = 350;
    public final int ANCHO = 450;

    public LaminaEj1 lamina;

    public VentanaEj1() {

        setTitle("Alta nueva categoría");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEj1();

        add(lamina);

        setVisible(true);
    }
    
    
}