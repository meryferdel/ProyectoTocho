package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.Excepciones.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Warehouse.
 * Implementa las operaciones CRUD sobre la tabla warehouses.
 */
public class WarehouseDAO {

    private static final String SQL_INSERT =
            "INSERT INTO warehouses (warehouse_name, location_id) VALUES (?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE warehouses SET warehouse_name = ?, location_id = ? WHERE warehouse_id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM warehouses WHERE warehouse_id = ?";

    private static final String SQL_FIND_BY_ID =
            "SELECT warehouse_id, warehouse_name, location_id FROM warehouses WHERE warehouse_id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT warehouse_id, warehouse_name, location_id FROM warehouses";

    public void insert(Warehouse warehouse) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, warehouse.getWarehouseName());
            ps.setInt(2, warehouse.getLocationId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar el almacén.", e);
        }
    }

    public void update(Warehouse warehouse) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, warehouse.getWarehouseName());
            ps.setInt(2, warehouse.getLocationId());
            ps.setInt(3, warehouse.getWarehouseId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar el almacén.", e);
        }
    }

    public void delete(int warehouseId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, warehouseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar el almacén.", e);
        }
    }

    public Warehouse findById(int warehouseId) throws DAOException {
        Warehouse warehouse = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, warehouseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    warehouse = new Warehouse();
                    warehouse.setWarehouseId(rs.getInt("warehouse_id"));
                    warehouse.setWarehouseName(rs.getString("warehouse_name"));
                    warehouse.setLocationId(rs.getInt("location_id"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar el almacén por ID.", e);
        }

        return warehouse;
    }

    public List<Warehouse> findAll() throws DAOException {
        List<Warehouse> warehouses = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouseId(rs.getInt("warehouse_id"));
                warehouse.setWarehouseName(rs.getString("warehouse_name"));
                warehouse.setLocationId(rs.getInt("location_id"));
                warehouses.add(warehouse);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todos los almacenes.", e);
        }

        return warehouses;
    }
}

