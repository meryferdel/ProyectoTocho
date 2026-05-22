// ==========================
// LÁMINA EJERCICIO 6
// ==========================

package es.biblio.proyectotocho.presentacion.ejercicio6;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.negocio.Ejer6Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaminaEjer6 extends JPanel {

    private JTextField txtCerrado;
    private JTextField txtTraspaso;
    private JButton btnCerrarTraspasar;
    private JFrame ventana;
    private Ejer6Controller controlador;

    public LaminaEjer6(
            JFrame ventanaPadre,
            Ejer6Controller controlador
    ) {

        this.ventana = ventanaPadre;

        this.controlador = controlador;

        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        // ALMACEN DE ORIGEN
        JLabel lblCierre =
                new JLabel(
                        "ID almacén a cerrar:"
                );

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(lblCierre, gbc);

        txtCerrado = new JTextField(15);

        gbc.gridx = 1;

        add(txtCerrado, gbc);

        // ALMACEN DE DESTINO
        JLabel lblTraspaso =
                new JLabel(
                        "ID almacén destino:"
                );

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(lblTraspaso, gbc);

        txtTraspaso = new JTextField(15);

        gbc.gridx = 1;

        add(txtTraspaso, gbc);

        // BOTON DE CIERRE Y TRASPASO
        btnCerrarTraspasar =
                new JButton(
                        "Cerrar y traspasar inventario"
                );

        btnCerrarTraspasar.setBackground(
                new Color(245, 245, 245)
        );

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        add(btnCerrarTraspasar, gbc);

        btnCerrarTraspasar.addActionListener(
                new ManejadorTraspaso()
        );
    }

    public JTextField getTxtCerrado() {
        return txtCerrado;
    }

    public JTextField getTxtTraspaso() {
        return txtTraspaso;
    }

    public JButton getBtnCerrarTraspasar() {
        return btnCerrarTraspasar;
    }

    private class ManejadorTraspaso
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String origen =
                    txtCerrado.getText().trim();

            String destino =
                    txtTraspaso.getText().trim();

            if (origen.isEmpty()
                    || destino.isEmpty()) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Debes rellenar ambos campos"
                );

                return;
            }

            if (!isNumber(origen)
                    || !isNumber(destino)) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Los IDs deben ser numéricos"
                );

                return;
            }

            if (origen.equals(destino)) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Los almacenes deben ser distintos"
                );

                return;
            }

            try {
                controlador.cerrarYTraspasar(
                        Integer.parseInt(origen),
                        Integer.parseInt(destino)
                );
            } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error en la operación"
            );

            System.out.println(ex.getMessage());
        }
        }
    }
    
    private boolean isNumber(String cadena) {

        try {

            Integer.parseInt(cadena);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }
}
