// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio1;

import es.biblio.proyectotocho.negocio.Ejer1Controller;
import javax.swing.*;

// Ventana gráfica correspondiente al Ejercicio 1.
// Su función es mostrar la interfaz para dar de alta una nueva categoría de producto.
public class VentanaEj1 extends JFrame {

    public final int ALTO = 350; // Altura de la ventana.
    public final int ANCHO = 450; // Anchura de la ventana.
    public LaminaEj1 lamina; // Lámina que contiene los componentes gráficos del ejercicio.

    // Constructor de la ventana del ejercicio 1.
    // Recibe el controlador para que la lámina pueda comunicarse con la lógica del ejercicio.
    public VentanaEj1(Ejer1Controller controlador) {

        // Establece el título de la ventana.
        setTitle("Alta nueva categoría");

        // Establece el tamaño de la ventana usando las constantes definidas.
        setSize(ANCHO, ALTO);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Al cerrar esta ventana, solo se cierra ella, no toda la aplicación.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crea la lámina del ejercicio 1 y le pasa la ventana y el controlador.
        lamina = new LaminaEj1(this, controlador);

        // Añade la lámina a la ventana.
        add(lamina);

        // Hace visible la ventana.
        setVisible(true);
    }
}
