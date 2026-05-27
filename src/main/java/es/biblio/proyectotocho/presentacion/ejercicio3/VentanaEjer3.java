package es.biblio.proyectotocho.presentacion.ejercicio3;

import es.biblio.proyectotocho.negocio.Ejer3Controller;
import javax.swing.JFrame;

public class VentanaEjer3 extends JFrame {

    public final int ALTO = 450;
    public final int ANCHO = 500;
    private Ejer3Controller controlador;
    public LaminaEjer3 lamina;

    public VentanaEjer3(Ejer3Controller controlador) {
        this.controlador = controlador;
        setTitle("Alta nuevo almacén");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEjer3(this, controlador);

        add(lamina);

        setVisible(true);
    }

    public LaminaEjer3 getLamina() {
        return lamina;
    }
}