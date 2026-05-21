package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.ConexionBD;
import es.biblio.proyectotocho.persistencia.Inventory;
import es.biblio.proyectotocho.persistencia.InventoryDAO;
import es.biblio.proyectotocho.persistencia.Warehouse;
import es.biblio.proyectotocho.persistencia.WarehouseDAO;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio6.VentanaEjer6;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.*;

public class Ejer6Controller {

    public VentanaEjer6 ventana;
    private WarehouseDAO wareDAO;
    private InventoryDAO invenDAO;
    private Warehouse almacen;

    public Ejer6Controller() {
        wareDAO = new WarehouseDAO();
        invenDAO = new InventoryDAO();
        ventana = new VentanaEjer6();

        ventana.getLamina()
                .getBtnCerrarTraspasar()
                .addActionListener(new EventoTxt());

    }

    private class EventoTxt implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String wareId1
                        = ventana.lamina.getTxtCerrado()
                                .getText().trim();
                String wareId2
                        = ventana.lamina.getTxtTraspaso()
                                .getText().trim();
                if (wareId1.isEmpty() || wareId2.isEmpty()) {
                    UtilidadesVista.mostrarVacio(ventana);
                    return;
                }

                if (!isNumber(wareId2) || !isNumber(wareId1)) {
                    UtilidadesVista.mostrarError(ventana, "El ID tiene que ser un numero");
                    return;
                }

                ArrayList<Integer> almacenes = new ArrayList<>();

                for (int i = 0; i < almacenes.size(); i++) {
                    almacenes.add(almacen.getWarehouseId());
                }

                if ((!almacenes.contains(wareId1) || !almacenes.contains(wareId2)) && wareId1 != wareId2) {
                    UtilidadesVista.mostrarError(ventana, "No se encuentra almacén");
                    return;
                }

                int respuesta = JOptionPane.showConfirmDialog(
                        ventana,
                        "Esta operación no se podrá deshacer, ¿estás seguro?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                );

                if (respuesta == JOptionPane.NO_OPTION) {
                    return;
                } else {
                    realizarTraspaso(wareId1, wareId2);

                    JOptionPane.showMessageDialog(
                            ventana,
                            "Cambios realizados correctamente"
                    );

                }

            } catch (DAOException e) {
                JOptionPane.showConfirmDialog(
                        ventana,
                        "Error al ");
            }
        }

    }

    private void realizarTraspaso(String idOrigen, String idDestino)
            throws Exception {

        Connection con = null;

        try {

            con = ConexionBD.getConnection();

            con.setAutoCommit(false);

            List<Inventory> inventarioOrigen
                    = invenDAO.getInventoryByWarehouse(idOrigen);

            for (Inventory invOrigen : inventarioOrigen) {

                Inventory invDestino
                        = invenDAO.findById(
                                invOrigen.getProductId(),
                                idDestino
                        );

                // SI NO EXISTE EN DESTINO
                if (invDestino == null) {

                    Inventory nuevo = new Inventory();

                    nuevo.setProductId(invOrigen.getProductId());
                    nuevo.setWarehouseId(idDestino);
                    nuevo.setQuantity(0);

                    invenDAO.insert(nuevo);

                    invDestino = nuevo;
                }

                // SUMAR CANTIDADES
                invDestino.setQuantity(
                        invDestino.getQuantity()
                        + invOrigen.getQuantity()
                );

                invenDAO.update(invDestino);
            }

            // BORRAR INVENTARIO ORIGEN
            for (Inventory invOrigen : inventarioOrigen) {

                invenDAO.delete(
                        invOrigen.getProductId(),
                        idOrigen
                );
            }

            // BORRAR ALMACÉN
            wareDAO.delete(idOrigen);

            con.commit();

        } catch (DAOException e) {

            if (con != null) {
                con.rollback();
            }

            throw e;

        } finally {

            if (con != null) {
                con.close();
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
