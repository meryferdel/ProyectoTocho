// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio5;

import es.biblio.proyectotocho.negocio.Ejer5Controller;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lámina gráfica del Ejercicio 5.
// Permite seleccionar una categoría y aplicar un descuento a todos sus productos.
public class LaminaEjer5 extends JPanel {

    private JComboBox<ProductCategory> comboCategorias; // Combo para seleccionar la categoría a la que se aplicará el descuento.
    private JTextField txtDescuento; // Campo donde el usuario introduce el porcentaje de descuento.
    private JButton btnAplicar; // Botón para ejecutar la acción de aplicar el descuento.
    private JFrame ventana; // Referencia a la ventana padre para mostrar mensajes emergentes.
    private Ejer5Controller controlador; // Controlador que gestiona la lógica del ejercicio 5.

    // Constructor de la lámina. Recibe la ventana padre y el controlador.
    public LaminaEjer5(JFrame ventanaPadre, Ejer5Controller controlador) {

        this.controlador = controlador; // Guarda el controlador para poder llamar a la lógica de negocio.
        this.ventana = ventanaPadre; // Guarda la ventana para mostrar mensajes emergentes.

        // Usa GridBagLayout para colocar los componentes con flexibilidad.
        setLayout(new GridBagLayout());

        // Objeto que define la posición y comportamiento de cada componente.
        GridBagConstraints gbc = new GridBagConstraints();

        // Márgenes entre componentes.
        gbc.insets = new Insets(10, 10, 10, 10);
        
        
        // ---------- CAMPO DE SELECCIÓN DE CATEGORÍA ---------- 
        // -----------------------------------------------------
        // Posición de la etiqueta de Categoría: columna 0, fila 0.
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Etiqueta que indica la selección de categoría.
        add(new JLabel("Categoría:"), gbc);

        // Combo donde se cargarán las categorías disponibles.
        comboCategorias = new JComboBox<>();
        gbc.gridx = 1; // Posición del combo en la columna 1.
        add(comboCategorias, gbc); // Añade el combo a la lámina.
        
        
        // ---------- CAMPO DE PORCENTAJE DE DESCUENTO ----------
        // ------------------------------------------------------
        gbc.gridx = 0;
        gbc.gridy = 1;

        // Etiqueta que indica el porcentaje de descuento.
        add(new JLabel("Descuento (%):"), gbc);

        // Campo donde el usuario escribe el descuento a aplicar.
        txtDescuento = new JTextField(10);
        gbc.gridx = 1;
        add(txtDescuento, gbc); // Añade el campo de texto.

        
        // ------------ BOTÓN DE APLICAR DESCUENTO --------------
        // ------------------------------------------------------
        // Botón para ejecutar la acción de aplicar el descuento.
        btnAplicar = new JButton("Aplicar descuento");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // El botón ocupa las dos columnas.
        add(btnAplicar, gbc); // Añade el botón a la lámina.

        // Asigna el listener que gestionará el clic del botón.
        btnAplicar.addActionListener(new ManejadorActionEvent());
    }

    // Getter para acceder al combo de categorías desde la ventana.
    public JComboBox<ProductCategory> getComboCategorias() {
        return comboCategorias;
    }

    // Getter para acceder al campo de descuento desde el controlador.
    public JTextField getTxtDescuento() {
        return txtDescuento;
    }

    // Getter para acceder al botón desde la ventana.
    public JButton getBtnAplicar() {
        return btnAplicar;
    }

    // Clase interna que gestiona el evento del botón "Aplicar descuento".
    private class ManejadorActionEvent implements ActionListener {

        // Método que se ejecuta cuando se pulsa el botón.
        @Override
        public void actionPerformed(ActionEvent e) {

            // Comprueba que la acción proviene del botón de aplicar.
            if (e.getSource() == getBtnAplicar()) {

                // Llama al controlador para aplicar el descuento a la categoría seleccionada.
                controlador.aplicarDescuentoCategoria();
            }
        }
    }
}
