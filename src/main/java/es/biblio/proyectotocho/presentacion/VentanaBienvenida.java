// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion;

import javax.swing.*;

// Ventana principal de bienvenida del proyecto.
// Esta ventana es la primera que ve el usuario al iniciar la aplicación
// y contiene los botones para acceder a cada uno de los ejercicios.
public class VentanaBienvenida extends JFrame {

    public final int ALTO = 600; // Altura de la ventana.
    public final int ANCHO = 400; // Anchura de la ventana.
    public LaminaBienvenida lamina; // Lámina que contiene los botones y elementos gráficos de la bienvenida.

    // Constructor de la ventana de bienvenida:
    public VentanaBienvenida() {

        // Establece el título de la ventana.
        setTitle("Proyecto Dual en el centro");

        // Establece el tamaño de la ventana usando las constantes definidas anteriormente.
        setSize(ALTO, ANCHO);

        // Centra la ventana en la pantalla.
        setLocationRelativeTo(null);

        // Indica que al cerrar esta ventana se cierre la aplicación completa.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea la lámina de bienvenida y la añade a la ventana.
        lamina = new LaminaBienvenida();
        add(lamina);

        // Hace visible la ventana.
        setVisible(true);
    }
}
