// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio1;

import es.biblio.proyectotocho.negocio.Ejer1Controller;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lámina gráfica del Ejercicio 1.
// Contiene los componentes necesarios para dar de alta una nueva categoría de producto,
// un campo de texto para el nombre y un botón para crearla.
public class LaminaEj1 extends JPanel {

    private JLabel lblNombre; // Etiqueta para indicar el campo del nombre.
    private JTextField txtNombre; // Campo de texto donde el usuario escribe el nombre de la categoría.
    private JButton btnCrear; // Botón para confirmar la creación.
    private JFrame ventana; // Referencia a la ventana que contiene esta lámina.
    private Ejer1Controller controlador; // Controlador del ejercicio 1.

    // Constructor de la lámina del ejercicio 1.
    // Recibe la ventana padre y el controlador para poder interactuar con la lógica del ejercicio.
    public LaminaEj1(JFrame ventanaPadre, Ejer1Controller controlador) {

        // Guarda en la propiedad 'controlador' el controlador del ejercicio 1 que recibe por parámetro.
        // Esto permite que la lámina pueda llamar a los métodos de la lógica de negocio
        // (por ejemplo crearCategoriaProducto) cuando el usuario pulse el botón.
        this.controlador = controlador;
        
        // Guarda una referencia a la ventana que contiene esta lámina.
        // Se usa para mostrar mensajes (JOptionPane) posicionados sobre esa ventana.
        ventana = ventanaPadre;

        // Establece el color de fondo de la lámina.
        setBackground(new Color(220, 220, 220));

        // Usa GridBagLayout para colocar los componentes con flexibilidad.
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Márgenes entre componentes.
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta "Nombre categoría"
        lblNombre = new JLabel("Nombre categoría:");

        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0

        // Añade la etiqueta "Nombre categoría" a la lámina,
        // usando las coordenadas y configuraciones establecidas en 'gbc'.
        // En este caso, gbc indica que la etiqueta debe colocarse en la columna 0, fila 0.
        add(lblNombre, gbc);

        // Campo de texto
        txtNombre = new JTextField(20);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0; // Fila 0

        // Añade el campo de texto a la lámina,
        // también usando las propiedades actuales de 'gbc'.
        // Aquí, gbc indica que el campo debe colocarse en la columna 1, fila 0.
        add(txtNombre, gbc);

        // Botón "Crear categoría" con las características estéticas de su fondo.
        btnCrear = new JButton("Crear categoría");
        btnCrear.setBackground(new Color(245, 245, 245));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // El botón ocupa dos columnas

        // Añade el botón "Crear categoría" a la lámina,
        // utilizando la configuración actual del objeto 'gbc'.
        // En este momento, gbc indica que el botón debe colocarse en la fila 1,
        // ocupando dos columnas (gridwidth = 2), centrado dentro del GridBagLayout.
        add(btnCrear, gbc);

        // Asigna el listener al botón.
        btnCrear.addActionListener(new ManejadorBotonCrear());
    }

    // Devuelve el campo de texto, por si el controlador lo necesitara.
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    // Devuelve el botón, por si fuera necesario desde fuera.
    public JButton getBtnCrear() {
        return btnCrear;
    }

    // Clase interna y privada que gestiona el evento del botón "Crear categoría".
    private class ManejadorBotonCrear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Obtiene el texto introducido por el usuario, eliminando espacios sobrantes.
            String nombreCategoria = txtNombre.getText().trim();

            // Valida si el campo está vacío, de ser así, se muestra un aviso y se detiene la operación.
            if (nombreCategoria.isEmpty()) {
                UtilidadesVista.mostrarVacio(ventana);
                return;
            }

            // Si el campo es válido, se llama al método crearCategoriaProducto del controlador.
            controlador.crearCategoriaProducto(nombreCategoria);
        }
    }
}
