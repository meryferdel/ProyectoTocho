package es.biblio.proyectotocho.presentacion.ejercicio2;

import es.biblio.proyectotocho.persistencia.ProductCategory;

import javax.swing.*;
import java.awt.*;

public class LaminaEj2 extends JPanel {

    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblCoste;
    private JLabel lblPrecio;
    private JLabel lblCategoria;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtCoste;
    private JTextField txtPrecio;
    private JComboBox<ProductCategory> comboCategorias;
    private JButton btnCrear;

    public LaminaEj2() {

        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        // NOMBRE
        lblNombre = new JLabel("Nombre:");

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(lblNombre, gbc);

        txtNombre = new JTextField(20);

        gbc.gridx = 1;

        add(txtNombre, gbc);

        // DESCRIPCIÓN
        lblDescripcion = new JLabel("Descripción:");

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(lblDescripcion, gbc);

        txtDescripcion = new JTextField(20);

        gbc.gridx = 1;

        add(txtDescripcion, gbc);

        // COSTE
        lblCoste = new JLabel("Coste estándar:");

        gbc.gridx = 0;
        gbc.gridy = 2;

        add(lblCoste, gbc);

        txtCoste = new JTextField(20);

        gbc.gridx = 1;

        add(txtCoste, gbc);

        // PRECIO
        lblPrecio = new JLabel("Precio venta:");

        gbc.gridx = 0;
        gbc.gridy = 3;

        add(lblPrecio, gbc);

        txtPrecio = new JTextField(20);

        gbc.gridx = 1;

        add(txtPrecio, gbc);

        // CATEGORÍA
        lblCategoria = new JLabel("Categoría:");

        gbc.gridx = 0;
        gbc.gridy = 4;

        add(lblCategoria, gbc);

        comboCategorias = new JComboBox<>();

        gbc.gridx = 1;

        add(comboCategorias, gbc);

        // BOTÓN
        btnCrear = new JButton("Crear nuevo producto");

        btnCrear.setBackground(new Color(245, 245, 245));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        add(btnCrear, gbc);
    }

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