// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio2;

import es.biblio.proyectotocho.negocio.Ejer2Controller;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lámina gráfica del Ejercicio 2.
// Contiene los campos y controles necesarios para dar de alta un nuevo producto:
// nombre, descripción, coste, precio y categoría.
public class LaminaEj2 extends JPanel {

    private Ejer2Controller controlador; // Controlador del ejercicio 2.
    private JFrame ventana; // Referencia a la ventana que contiene esta lámina.
    private JLabel lblNombre; // Etiqueta para el nombre del producto.
    private JLabel lblDescripcion; // Etiqueta para la descripción del producto.
    private JLabel lblCoste; // Etiqueta para el coste estándar.
    private JLabel lblPrecio; // Etiqueta para el precio de venta.
    private JLabel lblCategoria; // Etiqueta para la categoría del producto.
    private JTextField txtNombre; // Campo de texto para el nombre.
    private JTextField txtDescripcion; // Campo de texto para la descripción.
    private JTextField txtCoste; // Campo de texto para el coste.
    private JTextField txtPrecio; // Campo de texto para el precio.
    private JComboBox<ProductCategory> comboCategorias; // Combo para seleccionar la categoría del producto.
    private JButton btnCrear; // Botón para crear el nuevo producto.

    // Constructor de la lámina del ejercicio 2.
    // Recibe la ventana padre y el controlador para poder interactuar con la lógica del ejercicio.
    public LaminaEj2(JFrame ventanaPadre, Ejer2Controller controlador) {

        this.controlador = controlador; // Guarda el controlador para poder llamar a sus métodos.
        this.ventana = ventanaPadre; // Guarda la ventana para mostrar mensajes sobre ella.

        // Establece el color de fondo de la lámina.
        setBackground(new Color(220, 220, 220));

        // Usa GridBagLayout para colocar los componentes con flexibilidad.
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Márgenes entre componentes.
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta del campo del nombre del producto.
        lblNombre = new JLabel("Nombre:");
        gbc.gridx = 0; // Posición de la etiqueta en la columna 0.
        gbc.gridy = 0; // Posición de la etiqueta en la fila 0.
        add(lblNombre, gbc); // Añade la etiqueta en la lámina en la posición (0,0).

        // Campo donde el usuario escribe el nombre del producto.
        txtNombre = new JTextField(20);
        gbc.gridx = 1; // Posición del campo: columna 1 y misma fila que la etiqueta anterior.
        add(txtNombre, gbc); // Añade el campo de texto en la posición (1,0).

        // Etiqueta donde el usuario escribe la descripción del producto.
        lblDescripcion = new JLabel("Descripción:");
        gbc.gridx = 0; // Posición de la etiqueta en la columna 0.
        gbc.gridy = 1; // Posición de la etiqueta en la fila 1.
        add(lblDescripcion, gbc); // Añade la etiqueta a la la lámina.

        // Campo donde el usuario escribe la descripción.
        txtDescripcion = new JTextField(20);
        gbc.gridx = 1;
        add(txtDescripcion, gbc); // Añade el campo a la lámina.

        // Etiqueta del coste estándar.
        lblCoste = new JLabel("Coste estándar:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblCoste, gbc); // Añade la etiqueta.

        // Campo donde el usuario introduce el coste.
        txtCoste = new JTextField(20);
        gbc.gridx = 1;
        add(txtCoste, gbc);

        // Etiqueta del precio de venta.
        lblPrecio = new JLabel("Precio venta:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPrecio, gbc);

        // Campo donde el usuario introduce el precio.
        txtPrecio = new JTextField(20);
        gbc.gridx = 1;
        add(txtPrecio, gbc);

        // Etiqueta de categoría.
        lblCategoria = new JLabel("Categoría:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblCategoria, gbc);

        // Combo donde se cargarán las categorías disponibles.
        comboCategorias = new JComboBox<>();
        gbc.gridx = 1;
        add(comboCategorias, gbc);

        // Botón para confirmar la creación del producto.
        btnCrear = new JButton("Crear nuevo producto");
        btnCrear.setBackground(new Color(245, 245, 245));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // El botón ocupa dos columnas.
        add(btnCrear, gbc);

        // Asigna el listener al botón.
        btnCrear.addActionListener(new ManejadorBotonCrearProducto());
    }

    // Listener que gestiona la creación del nuevo producto.
    private class ManejadorBotonCrearProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Obtiene los valores introducidos por el usuario.
            String nombre = getTxtNombre().getText().trim();
            String descripcion = getTxtDescripcion().getText().trim();
            String costeTexto = getTxtCoste().getText().trim();
            String precioTexto = getTxtPrecio().getText().trim();
            ProductCategory categoria = (ProductCategory) getComboCategorias().getSelectedItem();

            // Valida y comprueba que ningún campo esté vacío.
            if (nombre.isEmpty()
                    || descripcion.isEmpty()
                    || costeTexto.isEmpty()
                    || precioTexto.isEmpty()
                    || categoria == null) {

                UtilidadesVista.mostrarVacio(ventana);
                return;
            }

            // Validación numérica del coste y precio, no pueden ser texto.
            if (!isNumber(costeTexto) || !isNumber(precioTexto)) {
                UtilidadesVista.mostrarWarningMensaje(ventana,
                        "El coste o el precio no tienen el formato correcto");
                return;
            }

            // Conversión a double.
            double coste = Double.parseDouble(costeTexto);
            double precio = Double.parseDouble(precioTexto);

            // Llama al controlador para crear el producto.
            controlador.crearNuevoProducto(nombre, descripcion, coste, precio, categoria.getCategoryId());
        }
    }

    // Comprueba si una cadena puede convertirse a número.
    private boolean isNumber(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Todos los getters para que se pueda acceder a todos estos campos desde la ventana correspondiente.
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtCoste() {
        return txtCoste;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JComboBox<ProductCategory> getComboCategorias() {
        return comboCategorias;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }
}
