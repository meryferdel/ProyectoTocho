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

public class LaminaEjer3 extends JPanel {

    private JComboBox<Region> comboRegiones;
    private JComboBox<Country> comboPaises;
    private JComboBox<Location> comboUbicaciones;
    private Ejer3Controller controlador;
    private JFrame ventana;

    private JTextField txtNombre;

    private JButton btnCrear;

    public LaminaEjer3(JFrame ventanaPadre, Ejer3Controller controlador) {
        this.ventana = ventanaPadre;
        this.controlador = controlador;

        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        // REGIONES
        JLabel lblRegiones = new JLabel("Regiones:");

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(lblRegiones, gbc);

        comboRegiones = new JComboBox<>();

        gbc.gridx = 1;

        add(comboRegiones, gbc);

        // PAÍSES
        JLabel lblPaises = new JLabel("Países:");

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(lblPaises, gbc);

        comboPaises = new JComboBox<>();

        comboPaises.setEnabled(false);

        gbc.gridx = 1;

        add(comboPaises, gbc);

        // UBICACIONES
        JLabel lblUbicaciones = new JLabel("Ubicaciones:");

        gbc.gridx = 0;
        gbc.gridy = 2;

        add(lblUbicaciones, gbc);

        comboUbicaciones = new JComboBox<>();

        comboUbicaciones.setEnabled(false);

        gbc.gridx = 1;

        add(comboUbicaciones, gbc);

        // NOMBRE ALMACÉN
        JLabel lblNombre = new JLabel("Nombre almacén:");

        gbc.gridx = 0;
        gbc.gridy = 3;

        add(lblNombre, gbc);

        txtNombre = new JTextField(20);

        gbc.gridx = 1;

        add(txtNombre, gbc);

        // BOTÓN
        btnCrear = new JButton("Crear nuevo almacén");

        btnCrear.setBackground(new Color(245, 245, 245));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        add(btnCrear, gbc);
        
        comboRegiones.addActionListener(new ManejadorSeleccionRegion());
        comboPaises.addActionListener(new ManejadorSeleccionPais());
        btnCrear.addActionListener(new ManejadorCrearAlmacen());

    }

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

    public void establecerManejadoresEventos() {
        
    }

    // ==========================
    // EVENTO REGIONES
    // ==========================
    private class ManejadorSeleccionRegion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            comboPaises.removeAllItems();
            comboUbicaciones.removeAllItems();

            Region region = (Region) comboRegiones.getSelectedItem();

            if (region != null) {
                controlador.cargarPaises(region);
            }

        }
    }

    // ==========================
    // EVENTO PAÍSES
    // ==========================
    private class ManejadorSeleccionPais implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            comboUbicaciones.removeAllItems();
            Country country = (Country) comboPaises.getSelectedItem();

            if (country != null) {
                controlador.cargarUbicaciones(country);
            }

        }
    }

    // ==========================
    // CREAR ALMACÉN
    // ==========================
    private class ManejadorCrearAlmacen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = txtNombre.getText().trim();

            Region region = (Region) comboRegiones.getSelectedItem();

            Country country = (Country) comboPaises.getSelectedItem();

            Location location = (Location) comboUbicaciones.getSelectedItem();

            // VALIDACIONES
            if (nombre.isEmpty()
                    || region == null
                    || country == null
                    || location == null) {
                
                UtilidadesVista.mostrarVacioMensaje(ventana, "No puede haber un campo vacío");

                return;
            }

            controlador.crearAlmacen(nombre, location);
        }
    }
}
