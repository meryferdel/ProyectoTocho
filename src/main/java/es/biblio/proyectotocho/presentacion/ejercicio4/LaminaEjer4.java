// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio4;

import es.biblio.proyectotocho.negocio.Ejer4Controller;
import es.biblio.proyectotocho.persistencia.Warehouse;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lámina gráfica del Ejercicio 4.
// Permite seleccionar un almacén y visualizar su inventario en una tabla.
public class LaminaEjer4 extends JPanel {

    private JComboBox<Warehouse> comboAlmacenes; // Combo para seleccionar un almacén.
    private JButton btnBuscar; // Botón para ejecutar la búsqueda del inventario.
    private JTable tabla; // Tabla donde se mostrará el inventario del almacén seleccionado.
    private DefaultTableModel modelo; // Modelo que gestiona las columnas y filas de la tabla.
    private JFrame ventana; // Referencia a la ventana padre para mostrar mensajes emergentes.
    private Ejer4Controller controlador; // Controlador que gestiona la lógica del ejercicio 4.

    // Constructor de la lámina. Recibe la ventana padre y el controlador.
    public LaminaEjer4(JFrame ventanaPadre, Ejer4Controller controlador) {

        this.ventana = ventanaPadre; // Guarda la ventana para mostrar mensajes emergentes.
        this.controlador = controlador; // Guarda el controlador para cargar almacenes y buscar inventario.

        // Establece un color de fondo suave.
        setBackground(new Color(220, 220, 220));

        // Usa BorderLayout para dividir la lámina en zonas.
        setLayout(new BorderLayout());

        // ==========================
        // PANEL SUPERIOR
        // ==========================
        // Panel superior que contiene el combo y el botón de búsqueda.
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(220, 220, 220)); // Establece el mismo color de fondo que la lámina.

        // Combo donde se cargarán los almacenes disponibles.
        comboAlmacenes = new JComboBox<>();

        // Botón para buscar el inventario del almacén seleccionado.
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(245, 245, 245)); // Color claro para el botón.

        // Añade la etiqueta "Almacén:" al panel superior.
        panelSuperior.add(new JLabel("Almacén:"));

        // Añade el combo de almacenes al panel superior.
        panelSuperior.add(comboAlmacenes);

        // Añade el botón de búsqueda al panel superior.
        panelSuperior.add(btnBuscar);

        // Añade el panel superior a la parte norte de la lámina.
        add(panelSuperior, BorderLayout.NORTH);

        // ==========================
        // TABLA
        // ==========================
        // Crea el modelo de la tabla.
        modelo = new DefaultTableModel();

        // Añade las columnas que mostrará la tabla.
        modelo.addColumn("PRODUCT_ID");
        modelo.addColumn("PRODUCT_NAME");
        modelo.addColumn("QUANTITY");

        // Crea la tabla usando el modelo.
        tabla = new JTable(modelo);

        // Añade la tabla dentro de un JScrollPane para permitir el scroll.
        JScrollPane scroll = new JScrollPane(tabla);

        // Añade el scroll (con la tabla dentro) al centro de la lámina.
        add(scroll, BorderLayout.CENTER);

        // ==========================
        // LISTENER
        // ==========================
        // Listener que gestiona la búsqueda del inventario.
        btnBuscar.addActionListener(new ManejadorBuscar());
    }

    // Getter para acceder al combo de almacenes desde la ventana.
    public JComboBox<Warehouse> getComboAlmacenes() {
        return comboAlmacenes;
    }

    // Getter para acceder al modelo de la tabla desde el controlador.
    public DefaultTableModel getModelo() {
        return modelo;
    }

    // ==========================
    // EVENTO BUSCAR
    // ==========================
    // Listener que se ejecuta cuando el usuario pulsa el botón "Buscar".
    private class ManejadorBuscar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Obtiene el almacén seleccionado en el combo.
            Warehouse warehouse = (Warehouse) comboAlmacenes.getSelectedItem();

            // Si no se ha seleccionado ningún almacén, muestra un mensaje de error.
            if (warehouse == null) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "Debes seleccionar un almacén"
                );
                return;
            }

            // Llama al controlador para buscar el inventario del almacén seleccionado.
            controlador.buscarInventario(warehouse.getWarehouseId());
        }
    }
}