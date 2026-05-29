// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio5;

import es.biblio.proyectotocho.negocio.Ejer5Controller;
import javax.swing.*;

// Ventana gráfica correspondiente al Ejercicio 5.
// Su función es mostrar la interfaz para aplicar un descuento a una categoría de productos.
public class VentanaEjer5 extends JFrame {

    public final int ANCHO = 450; // Anchura fija de la ventana.
    public final int ALTO = 300; // Altura fija de la ventana.
    public LaminaEjer5 lamina; // Lámina que contiene los componentes gráficos del ejercicio 5.

    // Constructor de la ventana del ejercicio 5.
    // Recibe el controlador para que la lámina pueda comunicarse con la lógica del ejercicio.
    public VentanaEjer5(Ejer5Controller controlador) {

        // Establece el título de la ventana.
        setTitle("Aplicar descuento por categoría");

        // Establece el tamaño de la ventana usando las constantes definidas.
        setSize(ANCHO, ALTO);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Al cerrar esta ventana, solo se cierra ella, no toda la aplicación.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crea la lámina del ejercicio 5 y le pasa la ventana y el controlador.
        lamina = new LaminaEjer5(this, controlador);

        // Añade la lámina a la ventana.
        add(lamina);

        // Hace visible la ventana.
        setVisible(true);
    }
}
