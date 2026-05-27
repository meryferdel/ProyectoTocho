package es.biblio.proyectotocho.presentacion.ejercicio4;

import es.biblio.proyectotocho.negocio.Ejer4Controller;
import es.biblio.proyectotocho.persistencia.Warehouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaminaEjer4 extends JPanel {

    private JComboBox<Warehouse> comboAlmacenes;

    private JButton btnBuscar;

    private JTable tabla;

    private DefaultTableModel modelo;

    private JFrame ventana;

    private Ejer4Controller controlador;

    public LaminaEjer4(
            JFrame ventanaPadre,
            Ejer4Controller controlador
    ) {

        this.ventana = ventanaPadre;

        this.controlador = controlador;

        setBackground(new Color(220, 220, 220));

        setLayout(new BorderLayout());

        // ==========================
        // PANEL SUPERIOR
        // ==========================
        JPanel panelSuperior = new JPanel();

        panelSuperior.setBackground(
                new Color(220, 220, 220)
        );

        comboAlmacenes = new JComboBox<>();

        btnBuscar = new JButton("Buscar");

        btnBuscar.setBackground(
                new Color(245, 245, 245)
        );

        panelSuperior.add(
                new JLabel("Almacén:")
        );

        panelSuperior.add(comboAlmacenes);

        panelSuperior.add(btnBuscar);

        add(panelSuperior, BorderLayout.NORTH);

        // ==========================
        // TABLA
        // ==========================
        modelo = new DefaultTableModel();

        modelo.addColumn("PRODUCT_ID");
        modelo.addColumn("PRODUCT_NAME");
        modelo.addColumn("QUANTITY");

        tabla = new JTable(modelo);

        JScrollPane scroll
                = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        // ==========================
        // LISTENER
        // ==========================
        btnBuscar.addActionListener(
                new ManejadorBuscar()
        );
    }

    // ==========================
    // GETTERS
    // ==========================
    public JComboBox<Warehouse> getComboAlmacenes() {
        return comboAlmacenes;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    // ==========================
    // EVENTO BUSCAR
    // ==========================
    private class ManejadorBuscar
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Warehouse warehouse
                    = (Warehouse) comboAlmacenes
                            .getSelectedItem();

            if (warehouse == null) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Debes seleccionar un almacén"
                );

                return;
            }

            controlador.buscarInventario(
                    warehouse.getWarehouseId()
            );
        }
    }
}
