// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio3;

import es.biblio.proyectotocho.negocio.Ejer3Controller;
import javax.swing.JFrame;

// Ventana gráfica correspondiente al Ejercicio 3.
// Su función es mostrar la interfaz para dar de alta un nuevo almacén.
public class VentanaEjer3 extends JFrame {

    public final int ALTO = 450; // Altura fija de la ventana.
    public final int ANCHO = 500; // Anchura fija de la ventana.
    private Ejer3Controller controlador; // Controlador que gestiona la lógica del ejercicio 3.
    public LaminaEjer3 lamina; // Lámina que contiene los componentes gráficos del ejercicio.

    // Constructor de la ventana del ejercicio 3.
    // Recibe el controlador para que la lámina pueda comunicarse con la lógica del ejercicio.
    public VentanaEjer3(Ejer3Controller controlador) {

        // Guarda el controlador recibido para poder usarlo desde la ventana.
        this.controlador = controlador;

        // Establece el título de la ventana.
        setTitle("Alta nuevo almacén");

        // Establece el tamaño de la ventana usando las constantes definidas.
        setSize(ANCHO, ALTO);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Al cerrar esta ventana, solo se cierra ella, no toda la aplicación.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crea la lámina del ejercicio 3 y le pasa la ventana y el controlador.
        lamina = new LaminaEjer3(this, controlador);

        // Añade la lámina a la ventana.
        add(lamina);

        // Hace visible la ventana.
        setVisible(true);
    }

    // Devuelve la lámina del ejercicio 3.
    // Útil si desde fuera se necesita acceder a sus componentes.
    public LaminaEjer3 getLamina() {
        return lamina;
    }
}
