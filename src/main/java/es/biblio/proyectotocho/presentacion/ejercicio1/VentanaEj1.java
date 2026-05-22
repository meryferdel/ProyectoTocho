package es.biblio.proyectotocho.presentacion.ejercicio1;

import es.biblio.proyectotocho.negocio.Ejer1Controller;
import javax.swing.*;

public class VentanaEj1 extends JFrame {

    public final int ALTO = 350;
    public final int ANCHO = 450;

    public LaminaEj1 lamina;

    public VentanaEj1(Ejer1Controller controlador) {

        setTitle("Alta nueva categoría");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEj1(this, controlador);

        add(lamina);

        setVisible(true);
    }
    
    
}