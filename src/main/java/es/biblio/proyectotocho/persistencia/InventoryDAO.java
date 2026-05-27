package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.Excepciones.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Inventory. Implementa las operaciones CRUD sobre la tabla
 * inventories. Clave primaria compuesta: (product_id, warehouse_id).
 */
public class InventoryDAO {

    private static final String SQL_INSERT
            = "INSERT INTO inventories (product_id, warehouse_id, quantity) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE inventories SET quantity = ? WHERE product_id = ? AND warehouse_id = ?";

    private static final String SQL_DELETE
            = "DELETE FROM inventories WHERE product_id = ? AND warehouse_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT product_id, warehouse_id, quantity "
            + "FROM inventories WHERE product_id = ? AND warehouse_id = ?";

    private static final String SQL_FIND_ALL
            = "SELECT product_id, warehouse_id, quantity FROM inventories";

    private static final String SQL_GET_INVENTORY
            = "SELECT i.product_id, i.warehouse_id, i.quantity, p.product_name "
            + "FROM inventories i "
            + "JOIN products p ON i.product_id = p.product_id "
            + "WHERE i.warehouse_id = ? "
            + "ORDER BY i.product_id";

    public void insert(Inventory inventory) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, inventory.getProductId());
            ps.setInt(2, inventory.getWarehouseId());
            ps.setInt(3, inventory.getQuantity());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar el inventario.", e);
        }
    }

    public void update(Inventory inventory) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, inventory.getQuantity());
            ps.setInt(2, inventory.getProductId());
            ps.setInt(3, inventory.getWarehouseId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar el inventario.", e);
        }
    }

    public void delete(int productId, int warehouseId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, productId);
            ps.setInt(2, warehouseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar el inventario.", e);
        }
    }

    public Inventory findById(int productId, int warehouseId) throws DAOException {
        Inventory inventory = null;

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, productId);
            ps.setInt(2, warehouseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inventory = new Inventory();
                    inventory.setProductId(rs.getInt("product_id"));
                    inventory.setWarehouseId(rs.getInt("warehouse_id"));
                    inventory.setQuantity(rs.getInt("quantity"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar el inventario por ID.", e);
        }

        return inventory;
    }

    public List<Inventory> findAll() throws DAOException {
        List<Inventory> inventories = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setProductId(rs.getInt("product_id"));
                inventory.setWarehouseId(rs.getInt("warehouse_id"));
                inventory.setQuantity(rs.getInt("quantity"));
                inventories.add(inventory);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todos los inventarios.", e);
        }

        return inventories;
    }

    public List<Inventory> getInventoryByWarehouse(int warehouseId)
        throws DAOException {

    List<Inventory> inventoryList = new ArrayList<>();

    try (Connection con = ConexionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(SQL_GET_INVENTORY)) {

        ps.setInt(1, warehouseId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Inventory inventory = new Inventory();

            inventory.setProductId(rs.getInt("product_id"));
            inventory.setWarehouseId(rs.getInt("warehouse_id"));
            inventory.setQuantity(rs.getInt("quantity"));
            inventory.setProductName(rs.getString("product_name")); // ← AQUÍ SE RELLENA

            inventoryList.add(inventory);
        }

    } catch (SQLException e) {
        throw new DAOException("Error getting inventory", e);
    }

    return inventoryList;
}

}
