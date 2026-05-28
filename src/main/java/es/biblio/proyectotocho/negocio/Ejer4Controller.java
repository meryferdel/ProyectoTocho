// Paquete de la capa de negocio (lógica) del proyecto.
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

// Controlador del ejercicio 4, que se encarga de coordinar la lógica entre la vista (VentanaEjer4) y la capa de persistencia (WarehouseDAO e InventoryDAO).
public class Ejer4Controller {

    private VentanaEjer4 ventana; // Referencia a la ventana gráfica del ejercicio 4.
    private WarehouseDAO warehouseDAO; // DAO para acceder a los almacenes en la base de datos.
    private InventoryDAO inventoryDAO; // DAO para acceder al inventario de cada almacén.

    // Constructor del controlador del ejercicio 4:
    public Ejer4Controller() {
        // Crea la ventana del ejercicio 4 y le pasa este controlador,
        // para que la vista pueda llamar a métodos del controlador (por ejemplo, al pulsar un botón).
        ventana = new VentanaEjer4(this);

        // Inicializa los DAOs necesarios que se usarán en la base de datos.
        warehouseDAO = new WarehouseDAO();
        inventoryDAO = new InventoryDAO();

        // Carga los almacenes en el comboBox al iniciar la ventana.
        cargarAlmacenes();
    }

    // Método que carga todos los almacenes desde la base de datos y los añade al combo de selección de la interfaz gráfica.
    private void cargarAlmacenes() {
        try {

            // Obtiene todos los almacenes.
            List<Warehouse> almacenes = warehouseDAO.findAll();

            // Añade cada almacén al comboBox.
            for (Warehouse almacen : almacenes) {
                ventana.getLamina()
                        .getComboAlmacenes()
                        .addItem(almacen);
            }
            
        } catch (DAOException e) {
            // Si ocurre un error, se muestra un mensaje al usuario.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar almacenes"
            );

            // Se le imprime el error en consola para depuración.
            System.out.println(e.getMessage());
        }
    }

    // Método que busca el inventario de un almacén concreto y lo muestra en la tabla de la interfaz.
    public void buscarInventario(int warehouseId) {
        try {
            // Obtiene la lista de productos del almacén seleccionado.
            List<Inventory> inventario = inventoryDAO.getInventoryByWarehouse(warehouseId);

            // 
            DefaultTableModel modelo = ventana.getLamina().getModelo();

            // 
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
