package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.Inventory;
import es.biblio.proyectotocho.persistencia.InventoryDAO;
import es.biblio.proyectotocho.persistencia.Warehouse;
import es.biblio.proyectotocho.persistencia.WarehouseDAO;

import es.biblio.proyectotocho.presentacion.ejercicio4.VentanaEjer4;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.util.List;

public class Ejer4Controller {

    private VentanaEjer4 ventana;
    private WarehouseDAO warehouseDAO;
    private InventoryDAO inventoryDAO;

    public Ejer4Controller() {
        warehouseDAO = new WarehouseDAO();
        inventoryDAO = new InventoryDAO();
        ventana = new VentanaEjer4(this);
        cargarAlmacenes();
    }

    private void cargarAlmacenes() {
        try {

            List<Warehouse> warehouses =
                    warehouseDAO.findAll();

            for (Warehouse warehouse
                    : warehouses) {

                ventana.getLamina()
                        .getComboAlmacenes()
                        .addItem(warehouse);
            }
        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar almacenes"
            );

            System.out.println(e.getMessage());
        }
    }

    public void buscarInventario(int warehouseId) {
        try {

            List<Inventory> inventario =
                    inventoryDAO
                            .getInventoryByWarehouse(
                                    warehouseId
                            );

            DefaultTableModel modelo =
                    ventana.getLamina()
                            .getModelo();

            // LIMPIAR TABLA
            modelo.setRowCount(0);

            // SIN RESULTADOS
            if (inventario.isEmpty()) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "No hay productos en el almacén"
                );
                return;
            }

            // RELLENAR TABLA
            for (Inventory inv : inventario) {
                Object[] fila = {
                        inv.getProductId(),
                        inv.getProductName(),
                        inv.getQuantity()
                };
                modelo.addRow(fila);
            }

            JOptionPane.showMessageDialog(
                    ventana,
                    "Inventario cargado correctamente"
            );

        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al buscar inventario"
            );

            System.out.println(e.getMessage());
        }
    }
}
