// Paquete de la capa de negocio (lógica) del proyecto.
package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.*;
import es.biblio.proyectotocho.presentacion.ejercicio6.VentanaEjer6;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// Controlador del ejercicio 6, encargado de coordinar la lógica entre la vista (VentanaEjer6)
// y los DAOs (WarehouseDAO e InventoryDAO) que acceden a la base de datos.
public class Ejer6Controller {

    private VentanaEjer6 ventana; // Propiedad referente a la ventana gráfica asociada a este ejercicio.
    private WarehouseDAO warehouseDAO; // DAO para trabajar con los almacenes.
    private InventoryDAO inventoryDAO; // DAO para trabajar con el inventario de los almacenes.
    
    // Constructor del controlador del ejercicio 6:
    public Ejer6Controller() {
        // Crea la ventana del ejercicio 6 y le pasa este controlador,
        // para que la vista pueda llamar a sus métodos.
        ventana = new VentanaEjer6(this);
        
        // Inicializa los DAOs necesarios para acceder a la base de datos.
        warehouseDAO = new WarehouseDAO();
        inventoryDAO = new InventoryDAO();
    }

    // Método de negocio que se encarga de cerrar un almacén y traspasar todo su inventario a otro almacén.
    // Recibe los IDs del almacén origen y destino.
    public void cerrarYTraspasar(int origen, int destino) throws Exception {
        try {
            // Obtiene los almacenes seleccionados.
            Warehouse almacenOrigen = warehouseDAO.findById(origen);
            Warehouse almacenDestino = warehouseDAO.findById(destino);
            
            // Validación: comprobar que ambos almacenes existen o no sean nulos, si no sale del método sin hacer nada.
            if (almacenOrigen == null || almacenDestino == null) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "Algún almacén no existe"
                );
                return;
            }

            // Mensaje de confirmación, ya que la operación no se puede deshacer.
            int opcion = JOptionPane.showConfirmDialog(
                    ventana,
                    "Esta operación no se podrá deshacer, ¿estás seguro?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            // Si la opción es NO o cancela, se detiene la operación y no hace nada.
            if (opcion != JOptionPane.YES_OPTION) {
                return;
            }

            // LLamada al método privado de esta misma clase.
            // Se realiza la transacción completa: se mueve inventario al almacén de destino y se elimina el almacén origen.
            realizarTransaccion(origen, destino);

            // Mensaje de éxito.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Operación realizada correctamente"
            );

        } catch (DAOException e) {
            // Si ocurre un error en la operación, se informa al usuario.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error en la operación"
            );
            
            // Se imprime el error en consola para facilitar la depuración.
            System.out.println(e.getMessage());
        }
    }

    // Método privado que realiza la transacción completa:
    // - Mover todo el inventario del almacén origen al almacén destino.
    // - Eliminar el almacén origen.
    // Todo ello dentro de una transacción para garantizar la integridad de los datos.
    private void realizarTransaccion(int origen, int destino) throws Exception {
        // Establecemos la conexión en un principio a null
        Connection con = null;
        
        try {
            // Obtiene la conexión y desactiva el AutoCommit para iniciar la transacción manualmente.
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);

            // Obtiene todos los productos del almacén origen.
            List<Inventory> inventarioOrigen = inventoryDAO.getInventoryByWarehouse(origen);

            // Recorre cada producto del almacén origen.
            for (Inventory invOrigen : inventarioOrigen) {
                
                // Busca si ese mismo producto ya existe en el almacén destino.
                Inventory invDestino = inventoryDAO.findById(invOrigen.getProductId(), destino);

                // Si el producto no existe en el almacén destino, se crea un nuevo registro.
                if (invDestino == null) {
                    Inventory nuevoProducto = new Inventory();
                    nuevoProducto.setProductId(invOrigen.getProductId());
                    nuevoProducto.setWarehouseId(destino);
                    nuevoProducto.setQuantity(0);
                    
                    // Inserta el nuevo registro en la base de datos en la tabla correspondiente.
                    inventoryDAO.insert(nuevoProducto);
                    
                    // Finalmente y ya si, existe un registro en la base de datos para ese producto en el almacén de destino.
                    invDestino = nuevoProducto;
                }
                
                // Suma las cantidades del producto origen al destino.
                invDestino.setQuantity(invDestino.getQuantity() + invOrigen.getQuantity());
                
                // Actualiza el inventario del almacén destino.
                inventoryDAO.update(invDestino);
                
                // Elimina el producto del almacén origen.
                inventoryDAO.delete(invOrigen.getProductId(), origen);
            }

            // Una vez movido todo el inventario, se elimina el almacén origen.
            warehouseDAO.delete(origen);
            
            // Se confirma la transacción. 
            con.commit();

        } catch (DAOException e) {
            // Si ocurre un error, se revierte toda la transacción para evitar inconsistencias.
            if (con != null) {
                con.rollback();
            }
            // Se vuelve a lanzar la excepción para que sea gestionada por el método llamador en este caso el de cerrarYTraspasar
            throw e;

        } finally {
            // En cualquier caso, se restaura el AutoCommit a true y se cierra la conexión.
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    // Si ocurre algún error a la hora de restaurar el AutoCommit o cerrar la conexión, saltaría esta excepción.
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
