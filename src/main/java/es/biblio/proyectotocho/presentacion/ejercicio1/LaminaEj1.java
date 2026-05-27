package es.biblio.proyectotocho.presentacion.ejercicio1;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.negocio.Ejer1Controller;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LaminaEj1 extends JPanel {

    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton btnCrear;
    private JFrame ventana;
    private Ejer1Controller controlador;

    public LaminaEj1(JFrame ventanaPadre, Ejer1Controller controlador) {
        this.controlador = controlador;
        
        ventana = ventanaPadre;
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
        
        btnCrear.addActionListener(new ManejadorBotonCrear());
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }
    
    private class ManejadorBotonCrear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String nombreCategoria =
                    txtNombre.getText().trim();

            if (nombreCategoria.isEmpty()) {

                UtilidadesVista.mostrarVacio(ventana);

                return;
            }
            controlador.crearCategoriaProducto(nombreCategoria);
            
        }
    
    }
}