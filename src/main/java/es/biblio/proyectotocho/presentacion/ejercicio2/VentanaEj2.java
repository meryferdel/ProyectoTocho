// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio2;

import es.biblio.proyectotocho.negocio.Ejer2Controller;
import javax.swing.JFrame;

// Ventana gráfica correspondiente al Ejercicio 2.
// Su función es mostrar la interfaz para dar de alta un nuevo producto.
public class VentanaEj2 extends JFrame {

    private final int ALTO = 500; // Altura de la ventana.
    private final int ANCHO = 500; // Anchura de la ventana.

    public LaminaEj2 lamina; // Lámina que contiene los componentes gráficos del ejercicio.

    // Constructor de la ventana del ejercicio 2.
    // Recibe el controlador para que la lámina pueda comunicarse con la lógica del ejercicio.
    public VentanaEj2(Ejer2Controller controlador) {

        // Establece el título de la ventana.
        setTitle("Alta nuevo producto");

        // Establece el tamaño de la ventana usando las constantes definidas.
        setSize(ANCHO, ALTO);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Al cerrar esta ventana, solo se cierra ella, no toda la aplicación.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crea la lámina del ejercicio 2 y le pasa la ventana y el controlador.
        lamina = new LaminaEj2(this, controlador);

        // Añade la lámina a la ventana.
        add(lamina);

        // Hace visible la ventana.
        setVisible(true);
    }

    // Método auxiliar para limpiar todos los campos de texto de la lámina.
    // Se utiliza después de crear un producto correctamente.
    public void limpiarCamposTexto() {

        lamina.getTxtNombre().setText("");
        lamina.getTxtDescripcion().setText("");
        lamina.getTxtCoste().setText("");
        lamina.getTxtPrecio().setText("");
    }
}
