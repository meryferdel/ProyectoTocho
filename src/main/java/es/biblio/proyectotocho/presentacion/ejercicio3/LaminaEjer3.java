// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio3;

import es.biblio.proyectotocho.negocio.Ejer3Controller;
import es.biblio.proyectotocho.persistencia.Country;
import es.biblio.proyectotocho.persistencia.Location;
import es.biblio.proyectotocho.persistencia.Region;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lámina gráfica del Ejercicio 3.
// Permite seleccionar región, país, ubicación y escribir el nombre del almacén.
public class LaminaEjer3 extends JPanel {

    private JComboBox<Region> comboRegiones; // Combo para seleccionar una región.
    private JComboBox<Country> comboPaises; // Combo para seleccionar un país (dependiente de la región).
    private JComboBox<Location> comboUbicaciones; // Combo para seleccionar una ubicación (dependiente del país).
    private Ejer3Controller controlador; // Controlador que gestiona la lógica del ejercicio 3.
    private JFrame ventana; // Referencia a la ventana padre para mostrar mensajes emergentes.
    private JTextField txtNombre; // Campo de texto para introducir el nombre del almacén.
    private JButton btnCrear; // Botón para crear el nuevo almacén.

    // Constructor de la lámina. Recibe la ventana padre y el controlador.
    public LaminaEjer3(JFrame ventanaPadre, Ejer3Controller controlador) {

        this.ventana = ventanaPadre; // Guarda la ventana para mostrar mensajes emergentes.
        this.controlador = controlador; // Guarda el controlador para cargar datos y crear almacenes.

        // Establece un color de fondo suave.
        setBackground(new Color(220, 220, 220));

        // Usa GridBagLayout para colocar los componentes con flexibilidad.
        setLayout(new GridBagLayout());

        // Objeto que define la posición y comportamiento de cada componente.
        GridBagConstraints gbc = new GridBagConstraints();

        // Márgenes entre componentes.
        gbc.insets = new Insets(10, 10, 10, 10);

        // Etiqueta para indicar la selección de región.
        JLabel lblRegiones = new JLabel("Regiones:");
        gbc.gridx = 0; // Posición de la etiqueta: columna 0, fila 0.
        gbc.gridy = 0;
        add(lblRegiones, gbc); // Añade la etiqueta a la lámina.

        // Combo donde se cargarán las regiones disponibles.
        comboRegiones = new JComboBox<>();
        gbc.gridx = 1;
        add(comboRegiones, gbc); // Añade el combo de regiones.

        // Etiqueta para indicar la selección de país.
        JLabel lblPaises = new JLabel("Países:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPaises, gbc); // Añade la etiqueta.

        // Combo donde se cargarán los países según la región seleccionada.
        comboPaises = new JComboBox<>();
        comboPaises.setEnabled(false); // Deshabilitado inicialmente hasta que se seleccione una región.
        gbc.gridx = 1; // Posición del combo: columna 1.
        add(comboPaises, gbc); // Añade el combo de países.

        // Etiqueta para indicar la selección de ubicación.
        JLabel lblUbicaciones = new JLabel("Ubicaciones:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblUbicaciones, gbc); // Añade la etiqueta.

        // Combo donde se cargarán las ubicaciones según el país seleccionado.
        comboUbicaciones = new JComboBox<>();
        comboUbicaciones.setEnabled(false); // Deshabilitado inicialmente hasta que se seleccione un país.
        gbc.gridx = 1;
        add(comboUbicaciones, gbc); // Añade el combo de ubicaciones.

        // Etiqueta para indicar el nombre del almacén.
        JLabel lblNombre = new JLabel("Nombre almacén:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblNombre, gbc); // Añade la etiqueta.

        // Campo donde el usuario escribe el nombre del almacén.
        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        add(txtNombre, gbc); // Añade el campo de texto.

        // Botón para confirmar la creación del almacén.
        btnCrear = new JButton("Crear nuevo almacén");
        btnCrear.setBackground(new Color(245, 245, 245)); // Color claro para el botón.
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // El botón ocupa las dos columnas.
        add(btnCrear, gbc); // Añade el botón a la lámina.

        // Listener que carga países cuando se selecciona una región.
        comboRegiones.addActionListener(new ManejadorSeleccionRegion());

        // Listener que carga ubicaciones cuando se selecciona un país.
        comboPaises.addActionListener(new ManejadorSeleccionPais());

        // Listener que crea el almacén cuando se pulsa el botón.
        btnCrear.addActionListener(new ManejadorCrearAlmacen());
    }

    // Getters para acceder a los combos y campos desde la ventana.
    public JComboBox<Region> getComboRegiones() {
        return comboRegiones;
    }

    public JComboBox<Country> getComboPaises() {
        return comboPaises;
    }

    public JComboBox<Location> getComboUbicaciones() {
        return comboUbicaciones;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    // ==========================================================
    // Evento: Selección de región
    // ==========================================================
    private class ManejadorSeleccionRegion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Limpia los combos dependientes.
            comboPaises.removeAllItems();
            comboUbicaciones.removeAllItems();

            // Obtiene la región seleccionada.
            Region region = (Region) comboRegiones.getSelectedItem();

            // Si hay región seleccionada, carga los países asociados.
            if (region != null) {
                controlador.cargarPaises(region);
            }
        }
    }

    // ==========================================================
    // Evento: Selección de país
    // ==========================================================
    private class ManejadorSeleccionPais implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Limpia las ubicaciones anteriores.
            comboUbicaciones.removeAllItems();

            // Obtiene el país seleccionado.
            Country country = (Country) comboPaises.getSelectedItem();

            // Si hay país seleccionado, carga las ubicaciones asociadas.
            if (country != null) {
                controlador.cargarUbicaciones(country);
            }
        }
    }

    // ==========================================================
    // Evento: Crear almacén
    // ==========================================================
    private class ManejadorCrearAlmacen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Obtiene el nombre introducido por el usuario.
            String nombre = txtNombre.getText().trim();

            // Obtiene la región seleccionada.
            Region region = (Region) comboRegiones.getSelectedItem();

            // Obtiene el país seleccionado.
            Country country = (Country) comboPaises.getSelectedItem();

            // Obtiene la ubicación seleccionada.
            Location location = (Location) comboUbicaciones.getSelectedItem();

            // Validación que ningún campo pueda estar vacío.
            if (nombre.isEmpty()
                    || region == null
                    || country == null
                    || location == null) {

                // Muestra mensaje indicando que falta algún dato.
                UtilidadesVista.mostrarVacioMensaje(ventana, "No puede haber un campo vacío");
                return;
            }

            // Llama al controlador para crear el almacén.
            controlador.crearAlmacen(nombre, location);
        }
    }
}
