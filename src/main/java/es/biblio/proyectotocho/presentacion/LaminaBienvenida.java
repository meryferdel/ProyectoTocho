// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion;

import javax.swing.*;
import java.awt.*;

// Lámina principal de la ventana de bienvenida.
// Contiene los botones que permiten acceder a cada uno de los ejercicios del proyecto.
public class LaminaBienvenida extends JPanel {

    private JButton[] botones; // Array que almacenará los 6 botones de navegación.

    // Constructor de la lámina de bienvenida:
    public LaminaBienvenida() {

        // Establece el color de fondo de la lámina.
        setBackground(new Color(220, 220, 220));

        // Establece un GridLayout con 6 filas y 1 columna,
        // dejando un espacio vertical de 15 píxeles entre botones.
        setLayout(new GridLayout(6, 1, 15, 15));

        // Añade un margen interno alrededor de toda la lámina.
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Crea el array de botones.
        botones = new JButton[6];

        // Inicializa cada botón con el texto correspondiente al ejercicio.
        botones[0] = new JButton("Alta nueva categoría de producto");
        botones[1] = new JButton("Alta de nuevo producto");
        botones[2] = new JButton("Alta de nuevo almacén");
        botones[3] = new JButton("Listado del inventario de un almacén");
        botones[4] = new JButton("Aplicar descuento por categoría");
        botones[5] = new JButton("Traspaso y cierre de un almacén");

        // Configura el estilo de cada botón y los añade a la lámina.
        for (JButton boton : botones) {

            // Color de fondo del botón.
            boton.setBackground(new Color(245, 245, 245));

            // Elimina el borde azul que aparece al seleccionar el botón.
            boton.setFocusPainted(false);

            // Establece la fuente del texto del botón.
            boton.setFont(new Font("Tahoma", Font.BOLD, 12));

            // Añade el botón a la lámina.
            add(boton);
        }
    }

    // Método público que devuelve el array de botones,
    // para que el controlador principal pueda asignarles los listeners.
    public JButton[] getBotones() {
        return botones;
    }
}
