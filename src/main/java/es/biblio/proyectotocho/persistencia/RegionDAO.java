package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Region.
 * Implementa las operaciones CRUD sobre la tabla regions.
 */
public class RegionDAO {

    private static final String SQL_INSERT =
            "INSERT INTO regions (region_name) VALUES (?)";

    private static final String SQL_UPDATE =
            "UPDATE regions SET region_name = ? WHERE region_id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM regions WHERE region_id = ?";

    private static final String SQL_FIND_BY_ID =
            "SELECT region_id, region_name FROM regions WHERE region_id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT region_id, region_name FROM regions";

    public void insert(Region region) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, region.getRegionName());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar la región.", e);
        }
    }

    public void update(Region region) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, region.getRegionName());
            ps.setInt(2, region.getRegionId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar la región.", e);
        }
    }

    public void delete(int regionId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, regionId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar la región.", e);
        }
    }

    public Region findById(int regionId) throws DAOException {
        Region region = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, regionId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    region = new Region(0, "");
                    region.setRegionId(rs.getInt("region_id"));
                    region.setRegionName(rs.getString("region_name"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar la región por ID.", e);
        }

        return region;
    }

    public List<Region> findAll() throws DAOException {
        List<Region> regiones = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Region region = new Region(0, "");
                region.setRegionId(rs.getInt("region_id"));
                region.setRegionName(rs.getString("region_name"));
                regiones.add(region);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todas las regiones.", e);
        }

        return regiones;
    }
}


