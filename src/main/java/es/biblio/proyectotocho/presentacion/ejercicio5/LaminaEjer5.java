package es.biblio.proyectotocho.presentacion.ejercicio5;

import es.biblio.proyectotocho.negocio.Ejer5Controller;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaminaEjer5 extends JPanel {

    private JComboBox<ProductCategory> comboCategorias;
    private JTextField txtDescuento;
    private JButton btnAplicar;
    private JFrame ventana;
    private Ejer5Controller controlador; // Objeto que gestiona la lógica con el Modelo Vista Controlador (MVC).

    public LaminaEjer5(JFrame ventanaPadre, Ejer5Controller controlador) {
        this.controlador = controlador;
        this.ventana = ventanaPadre;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Mostramos en un JComboBox las Categorías para poder elegir una en concreto:
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Categoría:"), gbc);

        comboCategorias = new JComboBox<>();
        gbc.gridx = 1;
        add(comboCategorias, gbc);

        // Mostramos en un JtextField para que el usuario pueda escribir qué Descuento 
        // se le van a aplicar a los productos de la categoría anteriormente elegida:
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Descuento (%):"), gbc);

        txtDescuento = new JTextField(10);
        gbc.gridx = 1;
        add(txtDescuento, gbc);

        // Botón para que podamos clicar y ejecutar la acción y se aplique el descuento:
        btnAplicar = new JButton("Aplicar descuento");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(btnAplicar, gbc);
    }

    public JComboBox<ProductCategory> getComboCategorias() {
        return comboCategorias;
    }

    public JTextField getTxtDescuento() {
        return txtDescuento;
    }

    public JButton getBtnAplicar() {
        return btnAplicar;
    }

    // Se declara una clase interna privada que implementa de ActionListener:
    private class ManejadorActionEvent implements ActionListener {

        // Método que se ejecuta cuando se dispara una acción (clic en el botón de Aplicar Descuento):
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == getBtnAplicar()) {
                controlador.aplicarDescuentoCategoria();
            }
        }
    }
}
