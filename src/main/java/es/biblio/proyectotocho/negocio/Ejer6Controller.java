package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.*;
import es.biblio.proyectotocho.presentacion.ejercicio6.VentanaEjer6;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Ejer6Controller {

    private VentanaEjer6 ventana;

    private WarehouseDAO warehouseDAO;

    private InventoryDAO inventoryDAO;

    public Ejer6Controller() {

        warehouseDAO = new WarehouseDAO();
        inventoryDAO = new InventoryDAO();
        ventana = new VentanaEjer6(this);
    }

    public void cerrarYTraspasar(int origen, int destino) throws Exception {
        try {
            Warehouse almacenOrigen =
                    warehouseDAO.findById(origen);
            Warehouse almacenDestino =
                    warehouseDAO.findById(destino);
            if (almacenOrigen == null
                    || almacenDestino == null) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Algún almacén no existe"
                );
                return;
            }

            int opcion =
                    JOptionPane.showConfirmDialog(
                            ventana,
                            "Esta operación no se podrá deshacer, ¿estás seguro?",
                            "Confirmación",
                            JOptionPane.YES_NO_OPTION
                    );

            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }

            realizarTransaccion(origen, destino);

            JOptionPane.showMessageDialog(
                    ventana,
                    "Operación realizada correctamente"
            );

        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error en la operación"
            );

            System.out.println(e.getMessage());
        }
    }

    private void realizarTransaccion(int origen, int destino) throws Exception {
        Connection con = null;
        try {

            con = ConexionBD.getConnection();
            con.setAutoCommit(false);

            List<Inventory> inventarioOrigen =
                    inventoryDAO.getInventoryByWarehouse(origen);

            for (Inventory invOrigen : inventarioOrigen) {
                Inventory invDestino =
                        inventoryDAO.findById(invOrigen.getProductId(),destino);

                if (invDestino == null) {
                    Inventory nuevo = new Inventory();
                    nuevo.setProductId(invOrigen.getProductId());
                    nuevo.setWarehouseId(destino);
                    nuevo.setQuantity(0);
                    inventoryDAO.insert(nuevo);
                    invDestino = nuevo;
                }

                invDestino.setQuantity(
                        invDestino.getQuantity()
                                + invOrigen.getQuantity()
                );

                inventoryDAO.update(invDestino);
                inventoryDAO.delete(
                        invOrigen.getProductId(),
                        origen
                );
            }

            warehouseDAO.delete(origen);
            con.commit();

        } catch (DAOException e) {
            if (con != null) {
                con.rollback();
            }
            throw e;

        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(
                            ex.getMessage()
                    );
                }
            }
        }
    }
}