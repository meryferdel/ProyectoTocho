package es.biblio.proyectotocho.presentacion.ejercicio2;

import es.biblio.proyectotocho.negocio.Ejer2Controller;
import javax.swing.JFrame;

public class VentanaEj2 extends JFrame {

    private final int ALTO = 500;
    private final int ANCHO = 500;
    
    public LaminaEj2 lamina;

    public VentanaEj2(Ejer2Controller controlador) {
        setTitle("Alta nuevo producto");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lamina = new LaminaEj2(this, controlador);

        add(lamina);

        setVisible(true);
    }

    public void limpiarCamposTexto() {
        
        lamina.getTxtNombre().setText("");
        lamina.getTxtDescripcion().setText("");
        lamina.getTxtCoste().setText("");
        lamina.getTxtPrecio().setText("");
    }
}
