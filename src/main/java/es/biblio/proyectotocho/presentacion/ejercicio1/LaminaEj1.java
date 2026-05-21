package es.biblio.proyectotocho.presentacion.ejercicio1;

import javax.swing.*;
import java.awt.*;

public class LaminaEj1 extends JPanel {

    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton btnCrear;

    public LaminaEj1() {

        setBackground(new Color(220, 220, 220));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        lblNombre = new JLabel("Nombre categoría:");

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(lblNombre, gbc);

        // TEXTFIELD
        txtNombre = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridy = 0;

        add(txtNombre, gbc);

        // BOTÓN
        btnCrear = new JButton("Crear categoría");

        btnCrear.setBackground(new Color(245, 245, 245));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        add(btnCrear, gbc);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }
}