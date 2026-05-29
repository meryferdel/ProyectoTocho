// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio4;

import es.biblio.proyectotocho.negocio.Ejer4Controller;
import javax.swing.JFrame;

// Ventana gráfica correspondiente al Ejercicio 4.
// Su función es mostrar el inventario de un almacén y permitir su consulta.
public class VentanaEjer4 extends JFrame {

    public final int ALTO = 500; // Altura fija de la ventana.
    public final int ANCHO = 900; // Anchura fija de la ventana.
    private LaminaEjer4 lamina; // Lámina que contiene los componentes gráficos del ejercicio 4.

    // Constructor de la ventana del ejercicio 4.
    // Recibe el controlador para que la lámina pueda comunicarse con la lógica del ejercicio.
    public VentanaEjer4(Ejer4Controller controlador) {

        // Establece el título de la ventana.
        setTitle("Inventario de almacén");

        // Establece el tamaño de la ventana usando las constantes definidas.
        setSize(ANCHO, ALTO);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Al cerrar esta ventana, solo se cierra ella, no toda la aplicación.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crea la lámina del ejercicio 4 y le pasa la ventana y el controlador.
        lamina = new LaminaEjer4(this, controlador);

        // Añade la lámina a la ventana.
        add(lamina);

        // Hace visible la ventana.
        setVisible(true);
    }

    // Devuelve la lámina del ejercicio 4.
    // Útil si desde fuera se necesita acceder a sus componentes.
    public LaminaEjer4 getLamina() {
        return lamina;
    }
}
